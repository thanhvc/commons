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
package org.exoplatform.commons.notification;

import javax.jcr.Node;
import javax.jcr.Session;

import junit.framework.TestCase;

import org.exoplatform.commons.api.notification.service.storage.NotificationDataStorage;
import org.exoplatform.commons.api.notification.service.storage.NotificationService;
import org.exoplatform.commons.notification.suite.FixtureBuilder;
import org.exoplatform.commons.notification.suite.FixtureContext;
import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.container.PortalContainer;
import org.exoplatform.container.configuration.ConfigurationManager;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.junit.After;
import org.junit.Before;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 22, 2014  
 */
public class BaseNotificationTest {
  private static final Log LOG = ExoLogger.getLogger(BaseNotificationTest.class);
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
  
  
  @Before
  public void setUp() throws Exception {
    LOG.info("@Before::setUp()");
    //if on fresh fixture, must be created the new one
    if (FixtureContext.isFreshFixture() || FixtureContext.hasFixture()) {
      LOG.info("@Before::setUp()::build up fixture.");
      FixtureBuilder.startFixture();
    }
    repositoryService = CommonsUtils.getService(RepositoryService.class);
    configurationManager = CommonsUtils.getService(ConfigurationManager.class);
    session = repositoryService.getRepository(REPO_NAME).getSystemSession(WORKSPACE_NAME);
    root = session.getRootNode();
    System.setProperty("gatein.email.domain.url", "http://localhost:8080");
    //setup decorate by its child
    setUpDecorate();
  }
  
  /**
   * It can be overridden by its child to decorate the child's fixture
   * @throws Exception
   */
  protected void setUpDecorate() throws Exception {
    
  }
  
  @After
  public void tearDown() throws Exception {
    LOG.info("@After::tearDown()");
    //if on fresh fixture, must be destroy the existing one.
    if (FixtureContext.isFreshFixture()) {
      LOG.info("@After::tearDown()::destroy fixture.");
      FixtureBuilder.stopFixture();
    }
    //tear down decorate by its child
    tearDownDecorate();
  }
  
  /**
   * It can be overridden by its child to decorate the child's fixture
   * @throws Exception
   */
  protected void tearDownDecorate() throws Exception {
    
  }
  
  
}
