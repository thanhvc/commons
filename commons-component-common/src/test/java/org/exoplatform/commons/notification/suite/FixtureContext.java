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

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 26, 2014  
 */
public class FixtureContext {

  private static boolean sharedFixture = false;
  
  private static boolean freshFixture = true;
  
  static boolean hasFixture = false;

  public static boolean isSharedFixture() {
    return sharedFixture;
  }
  
  public static boolean hasFixture() {
    return hasFixture;
  }

  public static void setSharedFixture(boolean shared) {
    sharedFixture = shared;
    if (shared) {
      freshFixture = false;
    }
  }

  public static boolean isFreshFixture() {
    return freshFixture;
  }

  public static void setFreshFixture(boolean fresh) {
    freshFixture = fresh;
    if (fresh) {
      sharedFixture = false;
    }
    
  }
  
  
}
