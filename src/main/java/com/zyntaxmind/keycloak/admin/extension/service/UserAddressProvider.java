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
package com.zyntaxmind.keycloak.admin.extension.service;

import java.util.List;
import org.keycloak.provider.Provider;
import com.zyntaxmind.keycloak.admin.extension.model.representation.UserAddressRepresentation;

/***
 * 
 * @author dush
 *
 */
public interface UserAddressProvider extends Provider {

  /***
   * 
   * @param userId
   * @return List of UserAddressRepresentation
   */
  List<UserAddressRepresentation> getUserAddress(String userId);

  /***
   * 
   * @param id
   * @param userId
   * @return UserAddressRepresentation
   */
  UserAddressRepresentation getUserAddress(String id, String userId);

  /***
   * 
   * @param addressRepresentation
   * @param id
   * @param userId
   * @return String: new resource id
   */
  String saveUserAddress(UserAddressRepresentation addressRepresentation, String userId);

  /***
   * 
   * @param addressRepresentation
   * @param userId
   */
  void updateUserAddress(UserAddressRepresentation addressRepresentation, String id, String userId);

  /***
   * 
   * @param id
   * @param userId
   */
  void deleteUserAddress(String id, String userId);

}
