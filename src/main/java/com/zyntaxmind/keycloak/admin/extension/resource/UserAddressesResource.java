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

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.keycloak.models.KeycloakSession;
import org.keycloak.services.resources.admin.permissions.AdminPermissionEvaluator;
import com.zyntaxmind.keycloak.admin.extension.model.representation.UserAddressRepresentation;
import com.zyntaxmind.keycloak.admin.extension.service.UserAddressProvider;
import com.zyntaxmind.keycloak.admin.extension.util.AdminRealmExtensionUtil;

/**
 * @author dush
 *
 */
public class UserAddressesResource {
  
private final KeycloakSession session;
  
  private final String userId;
  
  private final AdminPermissionEvaluator auth;
  
  public UserAddressesResource(KeycloakSession session, AdminPermissionEvaluator auth, String userId) {
    this.session = session;
    this.userId = userId;
    this.auth = auth;
  }
  
  @GET
  @Path("")
  @Produces(MediaType.APPLICATION_JSON)
  public List<UserAddressRepresentation> getUserAddresses() {
    AdminRealmExtensionUtil.validateQueryAccesss(session, auth, userId);
    return session.getProvider(UserAddressProvider.class).getUserAddress(this.userId);
  }
  
  @POST
  @Path("")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createUserAddress(UserAddressRepresentation rep) {
    AdminRealmExtensionUtil.validateCreateAccess(session, auth, userId);
    final String id = session.getProvider(UserAddressProvider.class).saveUserAddress(rep, this.userId);
    return Response.created(session.getContext().getUri().getAbsolutePathBuilder().path(id).build()).build();
  }
  
  @Path("{id}")
  public UserAddressResource userAddress(@PathParam("id") final String id) {
    return new UserAddressResource(session, auth, id,  this.userId);
  }
}
