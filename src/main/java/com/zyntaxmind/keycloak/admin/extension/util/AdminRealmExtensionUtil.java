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
package com.zyntaxmind.keycloak.admin.extension.util;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.UserModel;
import org.keycloak.services.resources.admin.permissions.AdminPermissionEvaluator;

/**
 * @author dush
 *
 */
public final class AdminRealmExtensionUtil {
  
  private AdminRealmExtensionUtil() {
    
  }
  
  /***
   * validate user id against realm. if user not found validate if request has query access.
   * 
   * @param session
   * @param auth
   * @param userId
   * 
   * @throws NotFoundException
   * @throws ForbiddenException
   */
  public static void validateQueryAccesss(KeycloakSession session, AdminPermissionEvaluator auth, String userId) {
    UserModel user = session.users().getUserById(session.getContext().getRealm(), userId);   
      if (user == null) {
        if (auth.users().canQuery()) {
          throw new NotFoundException("User not found");
        }
        else {
          throw new ForbiddenException();
        }
      }
  }
  
  /***
   * validate request has create access and validate user id against realm.
   * 
   * @param session
   * @param auth
   * @param userId
   * 
   * @throws NotFoundException
   * @throws ForbiddenException
   */
  public static void validateCreateAccess(KeycloakSession session, AdminPermissionEvaluator auth, String userId) {
    validateUser(session, auth, userId);
    auth.users().requireManage();
  }
  
  /***
   * validate request has manage access and validate user id against realm.
   * 
   * @param session
   * @param auth
   * @param userId
   */
  public static void validateManageAccess(KeycloakSession session, AdminPermissionEvaluator auth, String userId) {
    auth.users().requireManage(validateUser(session, auth, userId));
  }
  
  private static UserModel validateUser(KeycloakSession session, AdminPermissionEvaluator auth, String userId) {
    UserModel user = session.users().getUserById(session.getContext().getRealm(), userId);   
    
    if (user == null) {
      throw new NotFoundException("User not found");
    }
    
    return user;
  }
}
