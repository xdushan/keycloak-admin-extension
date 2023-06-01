/*******************************************************************************
 *    Copyright 2023  the original author.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.zyntaxmind.keycloak.admin.extension.resource;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.services.resources.admin.AdminEventBuilder;
import org.keycloak.services.resources.admin.permissions.AdminPermissionEvaluator;

/**
 * @author dush
 *
 */

public class AdminRealmExtensionResource {
  
  private final KeycloakSession session;
  
  private final AdminPermissionEvaluator auth;
  
  private final RealmModel realm;
  
  private final AdminEventBuilder adminEvent;
  
  public AdminRealmExtensionResource(KeycloakSession session, RealmModel realm, AdminPermissionEvaluator auth, AdminEventBuilder adminEvent) {
    this.session = session;
    this.auth = auth;
    this.realm = realm;
    this.adminEvent = adminEvent;
  }

  @Path("users/{userId}/addresses")
  public UserAddressesResource getUserAddresses(@PathParam("userId") String userId) {
    return new UserAddressesResource(session, auth, userId);
  }
  
  
  
}
