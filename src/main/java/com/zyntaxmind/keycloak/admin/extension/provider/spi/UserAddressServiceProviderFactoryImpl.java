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
package com.zyntaxmind.keycloak.admin.extension.provider.spi;

import org.keycloak.Config.Scope;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import com.zyntaxmind.keycloak.admin.extension.service.UserAddressService;
import com.zyntaxmind.keycloak.admin.extension.service.UserAddressServiceImpl;

/**
 * @author dush
 *
 */
public class UserAddressServiceProviderFactoryImpl implements UserAddressServiceProviderFactory {

  @Override
  public UserAddressService create(KeycloakSession session) {
    return new UserAddressServiceImpl(session);
  }

  @Override
  public void init(Scope config) {
    
  }

  @Override
  public void postInit(KeycloakSessionFactory factory) {
    
  }

  @Override
  public void close() {
    
  }

  @Override
  public String getId() {
    return "userAddressServiceImpl";
  }

}
