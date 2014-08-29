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

import java.io.Writer;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.plugin.AbstractNotificationPlugin;
import org.exoplatform.container.xml.InitParams;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 22, 2014  
 */
public class PostActivityPlugin extends AbstractNotificationPlugin {
  public static final String ID = "PostActivityPlugin";
  
  public PostActivityPlugin(InitParams initParams) {
    super(initParams);
  }
  
  @Override
  public String getId() {
    return ID;
  }

  @Override
  public boolean isValid(NotificationContext ctx) {
    return true;
  }

  @Override
  protected NotificationInfo makeNotification(NotificationContext ctx) {
    try {
      return NotificationInfo.instance()
          .to("mary")
          .with(NotificationUtils.POSTER.getKey(), "john")
          .with(NotificationUtils.ACTIVITY_ID.getKey(), "activityId_" + System.currentTimeMillis())
          .key(getId()).end();
      
    } catch (Exception e) {
      ctx.setException(e);
    }
    
    return null;
  }

  @Override
  protected MessageInfo makeMessage(NotificationContext ctx) {
    return null;
  }

  @Override
  protected boolean makeDigest(NotificationContext ctx, Writer writer) {
    return false;
  }

}
