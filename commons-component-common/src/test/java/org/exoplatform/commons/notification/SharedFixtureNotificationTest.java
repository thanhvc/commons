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

import org.exoplatform.commons.api.notification.service.storage.NotificationDataStorage;
import org.exoplatform.commons.api.notification.service.storage.NotificationService;
import org.exoplatform.commons.testing.BaseCommonsTestCase;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 22, 2014  
 */
public class SharedFixtureNotificationTest extends BaseCommonsTestCase {
  private static final Log LOG = ExoLogger.getLogger(SharedFixtureNotificationTest.class);
  /** */
  protected NotificationService       notificationService;
  /** */
  protected NotificationDataStorage   notificationDataStorage;
  /** reset and creates a new fixture before runs test method*/
  protected boolean freshFixture = false;
  /** keeps the existing fixture*/
  protected boolean sharedFixture = false;
  
  public void setUp() throws Exception {
    if (!sharedFixture) {
      super.setUp();
      LOG.info("@Before::sharedFixture::setUp()");
      sharedFixture = true;
    }
    
//    notificationService = getService(NotificationService.class);
//    notificationDataStorage = getService(NotificationDataStorage.class);
  }
  
  public void tearDown() throws Exception {
    if (freshFixture) {
      LOG.info("@After::sharedFixture::tearDown()");
      super.tearDown();
    }
  }
  public void testMehtod1() throws Exception {
    LOG.info("EXECUTES::testMehtod1()");
  }
  
  public void testMehtod2() throws Exception {
    LOG.info("EXECUTES::testMehtod2()");
  }
}
