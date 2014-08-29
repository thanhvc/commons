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

import org.exoplatform.commons.notification.testcases.FreshFixtureNotificationServiceTest;
import org.exoplatform.commons.notification.testcases.SharedFixtureNotificationServiceTest;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 26, 2014  
 */
@RunWith(Suite.class)
@SuiteClasses({SharedFixtureNotificationServiceTest.class,
  FreshFixtureNotificationServiceTest.class})
public class SimpleNotificationSuite {
  private static final Log LOG = ExoLogger.getLogger(SimpleNotificationSuite.class);
 

  @BeforeClass
  public static void setUpClass() throws Exception {
    LOG.info("@BeforeClass::setUpClass()");
    FixtureBuilder.startFixture();
    FixtureContext.setSharedFixture(true);
  }
  
  @AfterClass
  public static void tearDownClass() throws Exception {
    LOG.info("@AfterClass::tearDownClass()");
    FixtureBuilder.stopFixture();
    System.gc();
  }
}
