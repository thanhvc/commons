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
package org.exoplatform.commons.notification.testcases;

import org.exoplatform.commons.notification.BaseNotificationTest;
import org.exoplatform.commons.notification.suite.FixtureContext;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.junit.Test;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 26, 2014  
 */
public class FreshFixtureNotificationServiceTest extends BaseNotificationTest {

  static {
    FixtureContext.setFreshFixture(true);
  }
  private static final Log LOG = ExoLogger.getLogger(FreshFixtureNotificationServiceTest.class);
  
  @Test
  public void testMehtod1() throws Exception {
    LOG.info("@FreshFixtureNotificationServiceTest::testMehtod1()");
  }
  
  @Test
  public void testMehtod2() throws Exception {
    LOG.info("@FreshFixtureNotificationServiceTest::testMehtod2()");
  }
  
}
