/*******************************************************************************
 *    Copyright 2023 the original author.
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
package com.zyntaxmind.keycloak.admin.extension.provider.jpa;

import java.util.Collections;
import java.util.List;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;
import com.zyntaxmind.keycloak.admin.extension.entity.UserAddressEntity;

/**
 * @author dush
 *
 */
public class UserAddressJpaEntityProvider implements JpaEntityProvider {

  @Override
  public List<Class<?>> getEntities() {
    return Collections.<Class<?>>singletonList(UserAddressEntity.class);
  }

  @Override
  public String getChangelogLocation() {
    return "META-INF/user-address-changelog.xml";
  }

  @Override
  public String getFactoryId() {
    return UserAddressJpaEntityProviderFactory.ID;
  }
  
  @Override
  public void close() {    
  }

}
