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
package org.exoplatform.commons.notification.plugin;

import org.exoplatform.commons.api.notification.model.ArgumentLiteral;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 22, 2014  
 */
public class NotificationUtils {
  public final static ArgumentLiteral<String> ACTIVITY_ID = new ArgumentLiteral<String>(String.class, "activityId");
  public final static ArgumentLiteral<String> POSTER = new ArgumentLiteral<String>(String.class, "poster");
  public final static ArgumentLiteral<String> SENDER = new ArgumentLiteral<String>(String.class, "sender");
  public final static ArgumentLiteral<String> REMOTE_ID = new ArgumentLiteral<String>(String.class, "remoteId");
  public final static ArgumentLiteral<String> SPACE_ID = new ArgumentLiteral<String>(String.class, "spaceId");
  public final static ArgumentLiteral<String> PRETTY_NAME = new ArgumentLiteral<String>(String.class, "prettyName");
  public final static ArgumentLiteral<String> RELATIONSHIP_ID = new ArgumentLiteral<String>(String.class, "relationshipId");
  public static final String EMPTY_STR = "";
  public static final String SPACE_STR     = " ";
  public static final String DOT_STRING       = ".";
  public static final String A_HREF_TAG_REGEX = "</?a[^>]*>";
  
}
