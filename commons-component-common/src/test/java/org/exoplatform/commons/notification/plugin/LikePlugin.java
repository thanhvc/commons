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
import org.exoplatform.commons.api.notification.service.template.TemplateContext;
import org.exoplatform.commons.notification.data.NotificationMessageRegistry;
import org.exoplatform.commons.notification.template.TemplateUtils;
import org.exoplatform.container.xml.InitParams;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 22, 2014  
 */
public class LikePlugin extends AbstractNotificationPlugin {
  
  public static final String ID = "LikePlugin";
  
  public LikePlugin(InitParams initParams) {
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
    return NotificationInfo.instance()
                               .to(ctx.value(NotificationMessageRegistry.TO))
                               .with("likersId", ctx.value(NotificationMessageRegistry.FROM))
                               .setFrom(ctx.value(NotificationMessageRegistry.FROM))
                               .setSendToDaily(ctx.value(NotificationMessageRegistry.SEND_TO_DAILY))
                               .setSendToWeekly(ctx.value(NotificationMessageRegistry.SEND_TO_WEEKLY))
                               .key(getId()).end();
  }

  @Override
  protected MessageInfo makeMessage(NotificationContext ctx) {
    NotificationInfo notification = ctx.getNotificationInfo();
    String language = getLanguage(notification);
    TemplateContext templateContext = new TemplateContext(notification.getKey().getId(), language);
    templateContext.put("USER", notification.getTo());
    templateContext.put("SUBJECT", "Test plugetToetin notification");
    String subject = TemplateUtils.processSubject(templateContext);
    return new MessageInfo().from(notification.getFrom()).to(notification.getTo()).subject(subject).body(TemplateUtils.processGroovy(templateContext)).end();
  }

  @Override
  protected boolean makeDigest(NotificationContext ctx, Writer writer) {
    return false;
  }

}
