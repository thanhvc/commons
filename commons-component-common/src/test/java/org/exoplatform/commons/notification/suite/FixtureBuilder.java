/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.commons.notification.suite;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.exoplatform.commons.api.notification.service.storage.NotificationDataStorage;
import org.exoplatform.commons.api.notification.service.storage.NotificationService;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.configuration.ConfigurationManager;
import org.exoplatform.services.jcr.RepositoryService;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 26, 2014  
 */
public class FixtureBuilder {

  /** */
  protected NotificationService       notificationService;
  /** */
  protected NotificationDataStorage   notificationDataStorage;
  
  protected static final String         REPO_NAME      = "repository";

  protected static final String         WORKSPACE_NAME = "portal-test";

  protected static PortalContainer      container;

  protected static RepositoryService    repositoryService;

  protected static ConfigurationManager configurationManager;

  protected static Session              session;

  protected static Node                 root;
  
  public static void startFixture() throws Exception {
    container = PortalContainer.getInstance();
    repositoryService = CommonsUtils.getService(RepositoryService.class);
    session = repositoryService.getRepository(REPO_NAME).getSystemSession(WORKSPACE_NAME);
    root = session.getRootNode();
    System.setProperty("gatein.email.domain.url", "http://localhost:8080");
    FixtureContext.hasFixture = true;
  }
  
  public static void stopFixture() throws Exception {
    if (root != null && container != null && session.isLive()) {
      NodeIterator iter = root.getNodes();
      while (iter.hasNext()) {
        Node node = iter.nextNode();
        node.remove();
      }
      session.save();
      session.logout();
      FixtureContext.hasFixture = false;
      //container.stop();
      //root = null;
      //container = null;
      System.gc();
    }
  }
}
