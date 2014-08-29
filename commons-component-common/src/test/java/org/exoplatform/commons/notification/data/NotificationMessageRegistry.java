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
package org.exoplatform.commons.notification.data;

import java.util.Collection;
import java.util.List;

import org.exoplatform.commons.api.notification.NotificationContext;
import org.exoplatform.commons.api.notification.model.ArgumentLiteral;
import org.exoplatform.commons.api.notification.model.MessageInfo;
import org.exoplatform.commons.api.notification.model.NotificationInfo;
import org.exoplatform.commons.api.notification.model.NotificationKey;
import org.exoplatform.commons.api.notification.plugin.AbstractNotificationPlugin;
import org.exoplatform.commons.api.notification.service.setting.PluginContainer;
import org.exoplatform.commons.notification.impl.NotificationContextImpl;
import org.exoplatform.commons.notification.plugin.LikePlugin;
import org.exoplatform.commons.utils.CommonsUtils;
import org.gatein.common.util.CollectionMap;
import org.gatein.common.util.ListMap;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          exo@exoplatform.com
 * Aug 26, 2014  
 */
public class NotificationMessageRegistry {
  
  public final static ArgumentLiteral<String> PLUGIN_ID = new ArgumentLiteral<String>(String.class, "pluginId");
  public final static ArgumentLiteral<String> FROM = new ArgumentLiteral<String>(String.class, "from");
  public final static ArgumentLiteral<String> TO = new ArgumentLiteral<String>(String.class, "to");
  public final static ArgumentLiteral<String> SUBJECT = new ArgumentLiteral<String>(String.class, "subject");
  public final static ArgumentLiteral<String> SEND_TO_DAILY = new ArgumentLiteral<String>(String.class, "sendToDaily");
  public final static ArgumentLiteral<String> SEND_TO_WEEKLY = new ArgumentLiteral<String>(String.class, "sendToWeekly");
  /** Keeps the notification information */
  private static CollectionMap<String, NotificationInfo> notificationRepository = new ListMap<String, NotificationInfo>();
  /** Keeps the message information */
  private static CollectionMap<String, MessageInfo> messageRepository = new ListMap<String, MessageInfo>();
  /**
   * Return total of notification message size
   * @return
   */
  public static int sizeAll() {
    return notificationRepository.keySet().size();
  }
  /**
   * Registry the list of NotificationInfo to Register
   * 
   * @param key
   * @param list
   */
  public static void registry(NotificationKey key, List<NotificationInfo> list) {
    for (NotificationInfo notif : list) {
      notificationRepository.put(key.getId(), notif);
    }
  }
  
  /**
   * Registry the list of NotificationInfo to Register
   * @param key
   * @param list
   */
  public static void registry(NotificationKey key, NotificationInfo ...notificationInfo) {
    for (NotificationInfo notif : notificationInfo) {
      notificationRepository.put(key.getId(), notif);
    }
  }
  /**
   * Returns the list of notification by NotificationKey
   * @param key
   * @return
   */
  public Collection<NotificationInfo> get(NotificationKey key) {
    return notificationRepository.get(key.getId());
  }
  
  /**
   * Reset all of things what it held
   */
  public static void clear() {
    notificationRepository = new ListMap<String, NotificationInfo>();
    messageRepository = new ListMap<String, MessageInfo>();
  }
  /**
   * Gets the plugin by key
   * @param pluginId
   * @return
   */
  public static AbstractNotificationPlugin getPlugin(String pluginId) {
    PluginContainer container = CommonsUtils.getService(PluginContainer.class);
    return container.getPlugin(NotificationKey.key(pluginId));
  }
  
  public static NotificationBuilder inject(String pluginId) {
    return new NotificationBuilder(pluginId);
  }
  
  static void create(NotificationBuilder builder) {
    NotificationContext ctx = NotificationContextImpl.cloneInstance();
    ctx.append(PLUGIN_ID, builder.pluginId)
       .append(FROM, builder.from)
       .append(TO, builder.to)
       .append(SUBJECT, builder.subject)
       .append(SEND_TO_DAILY, builder.sendToDaily)
       .append(SEND_TO_WEEKLY, builder.sendToWeekly);

    ctx.getNotificationExecutor()
       .with(ctx.makeCommand(NotificationKey.key(LikePlugin.ID)))
       .execute(ctx);
    
  }
  
  public static class NotificationBuilder {
    public String pluginId;
    public String from;
    public String to;
    public String[] sendToDaily;
    public String[] sendToWeekly;
    public String subject;
    
    public NotificationBuilder(String pluginId) {
      this.pluginId = pluginId;
    }
    
    public NotificationBuilder from(String from) {
      this.from = from;
      return this;
    }
    
    public NotificationBuilder to(String to) {
      this.to = to;
      return this;
    }
    
    public NotificationBuilder subject(String subject) {
      this.subject = subject;
      return this;
    }
    
    public NotificationBuilder daily(String ...sendToDaily) {
      this.sendToDaily = sendToDaily;
      return this;
    }
    
    public NotificationBuilder weekly(String ...sendToWeekly) {
      this.sendToWeekly = sendToWeekly;
      return this;
    }
    
    public void inject(int number) {
      for (int i = 0; i< number; i++) {
        NotificationMessageRegistry.create(this);
      }
    }
  }
}
