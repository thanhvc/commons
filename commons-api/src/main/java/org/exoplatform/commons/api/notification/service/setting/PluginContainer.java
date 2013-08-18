/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
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
package org.exoplatform.commons.api.notification.service.setting;

import org.exoplatform.commons.api.notification.model.NotificationKey;
import org.exoplatform.commons.api.notification.plugin.AbstractNotificationPlugin;

/**
 * Created by The eXo Platform SAS
 * Author : eXoPlatform
 *          thanhvc@exoplatform.com
 * Aug 18, 2013  
 */
public interface PluginContainer {

  /**
   * Gets plugin by NotificationKey
   * @param key
   * @return
   */
  AbstractNotificationPlugin getPlugin(NotificationKey key);

  /**
   * Register plugin in Container
   * @param plugin
   */
  void add(AbstractNotificationPlugin plugin);

  /**
   * Removes plugin in Container
   * @param key
   * @return
   */
  boolean remove(NotificationKey key);
}
