/*
 * Copyright (C) 2003-2008 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.services.deployment.plugins;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import javax.jcr.ImportUUIDBehavior;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.exoplatform.container.configuration.ConfigurationManager;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.container.xml.ObjectParameter;
import org.exoplatform.services.deployment.DeploymentDescriptor;
import org.exoplatform.services.deployment.DeploymentPlugin;
import org.exoplatform.services.deployment.Utils;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

/**
 * Created by The eXo Platform SAS
 * Author : Hoa Pham
 * hoa.pham@exoplatform.com
 * Sep 6, 2008
 */
public class XMLDeploymentPlugin extends DeploymentPlugin {

  /** The init params. */
  private InitParams initParams;

  /** The configuration manager. */
  private ConfigurationManager configurationManager;

  /** The repository service. */
  private RepositoryService repositoryService;

  /** The log. */
  private static final Log LOG = ExoLogger.getLogger(XMLDeploymentPlugin.class);

  /**
   * Instantiates a new xML deployment plugin.
   *
   * @param initParams the init params
   * @param configurationManager the configuration manager
   * @param repositoryService the repository service
   * @param publicationService the publication service
   */
  public XMLDeploymentPlugin(InitParams initParams,
                             ConfigurationManager configurationManager,
                             RepositoryService repositoryService) {
    this.initParams = initParams;
    this.configurationManager = configurationManager;
    this.repositoryService = repositoryService;
  }

  /*
   * (non-Javadoc)
   * @see
   * org.exoplatform.services.deployment.DeploymentPlugin#deploy(org.exoplatform
   * .services.jcr.ext.common.SessionProvider)
   */
  @SuppressWarnings("unchecked")
  public void deploy(SessionProvider sessionProvider) throws Exception {
    Iterator iterator = initParams.getObjectParamIterator();
    while(iterator.hasNext()) {
      ObjectParameter objectParameter = (ObjectParameter)iterator.next();
      DeploymentDescriptor deploymentDescriptor = (DeploymentDescriptor)objectParameter.getObject();
      String sourcePath = deploymentDescriptor.getSourcePath();
      // sourcePath should start with: war:/, jar:/, classpath:/, file:/
      String versionHistoryPath = deploymentDescriptor.getVersionHistoryPath();
      Boolean cleanupPublication = deploymentDescriptor.getCleanupPublication();

      InputStream inputStream = configurationManager.getInputStream(sourcePath);
      ManageableRepository repository = repositoryService.getCurrentRepository();
      Session session = sessionProvider.getSession(deploymentDescriptor.getTarget().getWorkspace(),
                                                   repository);
      session.importXML(deploymentDescriptor.getTarget().getNodePath(),
                        inputStream,
                        ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);

      if (cleanupPublication) {
        /**
         * This code allows to cleanup the publication lifecycle in the target
         * folder after importing the data. By using this, the publication live
         * revision property will be re-initialized and the content will be set
         * as published directly. Thus, the content will be visible in front
         * side.
         */
        QueryManager manager = session.getWorkspace().getQueryManager();
        String statement = "select * from nt:base where jcr:path LIKE '"
            + deploymentDescriptor.getTarget().getNodePath() + "/%'";
        Query query = manager.createQuery(statement.toString(), Query.SQL);
        NodeIterator iter = query.execute().getNodes();
        while (iter.hasNext()) {
          Node node = iter.nextNode();
          if (node.hasProperty("publication:liveRevision")
              && node.hasProperty("publication:currentState")) {
            LOG.info("\"" + node.getName() + "\" publication lifecycle has been cleaned up");
            node.setProperty("publication:liveRevision", "");
            node.setProperty("publication:currentState", "published");
          }

        }

      }
      
      if (versionHistoryPath != null && versionHistoryPath.length() > 0) {
        //process import version history
        Node currentNode = (Node)session.getItem(deploymentDescriptor.getTarget().getNodePath());
              
        Map<String, String> mapHistoryValue = Utils.getMapImportHistory(configurationManager.getInputStream(versionHistoryPath));
        Utils.processImportHistory(currentNode, configurationManager.getInputStream(versionHistoryPath), mapHistoryValue);
      }

      session.save();
      if (LOG.isInfoEnabled()) {
        LOG.info(deploymentDescriptor.getSourcePath() + " is deployed succesfully into "
            + deploymentDescriptor.getTarget().getNodePath());
      }
    }
  }
}
