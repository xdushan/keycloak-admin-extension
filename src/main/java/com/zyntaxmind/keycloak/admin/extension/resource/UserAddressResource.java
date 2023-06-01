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

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.cache.NoCache;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resources.admin.permissions.AdminPermissionEvaluator;
import com.zyntaxmind.keycloak.admin.extension.model.representation.UserAddressRepresentation;
import com.zyntaxmind.keycloak.admin.extension.service.UserAddressService;
import com.zyntaxmind.keycloak.admin.extension.util.AdminRealmExtensionUtil;

/**
 * @author dush
 *
 */
public class UserAddressResource {

  private final KeycloakSession session;
  
  private final AdminPermissionEvaluator auth;
  
  private final String id;
  
  private final String userId;
  
  public UserAddressResource(KeycloakSession session, AdminPermissionEvaluator auth, String id, String userId) {
    this.session = session;
    this.userId = userId;
    this.auth = auth;
    this.id = id;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public UserAddressRepresentation getUserAddress() {
    AdminRealmExtensionUtil.validateQueryAccesss(session, auth, userId);
    return session.getProvider(UserAddressService.class).getUserAddress(this.id, this.userId);
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUserAddress(UserAddressRepresentation rep) {
    AdminRealmExtensionUtil.validateManageAccess(session, auth, userId);
    session.getProvider(UserAddressService.class).updateUserAddress(rep, this.id, this.userId);
    return Response.noContent().build();
  }
  
  @DELETE
  @NoCache
  public Response deleteUserAddress() {
    AdminRealmExtensionUtil.validateManageAccess(session, auth, userId);
    session.getProvider(UserAddressService.class).deleteUserAddress(this.id, this.userId);
    return Response.noContent().build();
  }
}
