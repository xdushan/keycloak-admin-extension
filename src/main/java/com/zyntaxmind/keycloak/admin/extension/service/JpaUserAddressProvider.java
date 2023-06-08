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
package com.zyntaxmind.keycloak.admin.extension.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response.Status;
import org.keycloak.connections.jpa.JpaConnectionProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.utils.KeycloakModelUtils;
import org.keycloak.services.ErrorResponse;
import com.zyntaxmind.keycloak.admin.extension.entity.UserAddressEntity;
import com.zyntaxmind.keycloak.admin.extension.model.representation.UserAddressRepresentation;

/**
 * @author dush
 *
 */
public class JpaUserAddressProvider implements UserAddressProvider {

  private final KeycloakSession session;

  public JpaUserAddressProvider(KeycloakSession session) {
    this.session = session;
  }

  /***
   * 
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    return session.getProvider(JpaConnectionProvider.class).getEntityManager();
  }

  /***
   * {@inheritDoc}
   */
  @Override
  public List<UserAddressRepresentation> getUserAddress(String userId) {
    return getEntityManager()
        .createNamedQuery("findByUserId", UserAddressEntity.class)
        .setParameter("userId", userId)
        .getResultStream()
        .map(this::toUserAddressRepresentation)
        .collect(Collectors.toList());
  }

  /***
   * {@inheritDoc}
   */
  @Override
  public UserAddressRepresentation getUserAddress(String id, String userId) {
    return this.toUserAddressRepresentation(findUserAddressByIdAndUserId(id, userId));
  }
  
  /***
   * {@inheritDoc}
   */
  @Override
  public String saveUserAddress(UserAddressRepresentation addressRepresentation, String userId) {
    final String id = KeycloakModelUtils.generateId();
    saveUserAddressEntity(new UserAddressEntity(),addressRepresentation, userId, id, true);
    return id;
  }
  
  /***
   * {@inheritDoc}
   */
  @Override
  public void updateUserAddress(UserAddressRepresentation addressRepresentation, String id, String userId) {
    UserAddressEntity userAddressEntity = findUserAddressByIdAndUserId(id, userId);
    saveUserAddressEntity(userAddressEntity, addressRepresentation, userId, id, false);
  }
  
  /***
   * {@inheritDoc}
   */
  @Override
  public void deleteUserAddress(String id, String userId) {
    UserAddressEntity userAddressEntity = findUserAddressByIdAndUserId(id, userId);
    getEntityManager().remove(userAddressEntity);
  }

  /***
   * {@inheritDoc}
   */
    @Override
    public void close() {}
    
    private UserAddressEntity findUserAddressByIdAndUserId(String id, String userId) {
      try {
        return getEntityManager()
            .createNamedQuery("findByIdAndUserId", UserAddressEntity.class)
            .setParameter("id", id)
            .setParameter("userId", userId)
            .getSingleResult();
      }
      catch (NoResultException e) {
        throw new NotFoundException("User address not found");
      }
    }
    
  /**
   * @param addressRepresentation
   * @param userId
   * @param id
   */
  private void saveUserAddressEntity(UserAddressEntity userAddressEntity,  UserAddressRepresentation addressRepresentation, String userId, String id, boolean isInsert) {
    updateUserAddressEntity(userAddressEntity, addressRepresentation);
    
    if(isInsert) {
      userAddressEntity.setId(id);
      userAddressEntity.setUserId(userId);
      getEntityManager().persist(userAddressEntity);
    }
    else {
      getEntityManager().merge(userAddressEntity);
    }
    
    getEntityManager().flush();
  }
  
  /***
   * 
   * @param userAddressEntity
   * @param addressRepresentation
   * @return updated UserAddressEntity
   */
  private UserAddressEntity updateUserAddressEntity(UserAddressEntity userAddressEntity, UserAddressRepresentation addressRepresentation) {
    if(addressRepresentation.address1() == null || addressRepresentation.cityId() == null
        || addressRepresentation.postalCode() == null || addressRepresentation.countryId() == null) {
      throw ErrorResponse.error("Required fields should not be empty", Status.BAD_REQUEST);
    }
    
    if ((addressRepresentation.longitude() == null || addressRepresentation.latitude() == null) && 
        (addressRepresentation.longitude() != null || addressRepresentation.latitude() != null)) {
      throw ErrorResponse.error("'longitude' and 'latitude' both should be provided", Status.BAD_REQUEST);
    }
    
    userAddressEntity.setAddres1(addressRepresentation.address1());
    userAddressEntity.setAddress2(addressRepresentation.address2());
    userAddressEntity.setCityId(addressRepresentation.cityId());
    userAddressEntity.setPostalCode(addressRepresentation.postalCode());
    userAddressEntity.setState(addressRepresentation.state());
    userAddressEntity.setCountryId(addressRepresentation.countryId());
    userAddressEntity.setLongitude(addressRepresentation.longitude());
    userAddressEntity.setLatitude(addressRepresentation.latitude());
    
    return userAddressEntity;
  }
  
  private UserAddressRepresentation toUserAddressRepresentation(UserAddressEntity userAddressEntity) {
    return new UserAddressRepresentation(
        userAddressEntity.getId(),
        userAddressEntity.getUserId(),
        userAddressEntity.getAddres1(),
        userAddressEntity.getAddress2(),
        userAddressEntity.getCityId(),
        userAddressEntity.getPostalCode(),
        userAddressEntity.getState(),
        userAddressEntity.getCountryId(),
        userAddressEntity.getLongitude(),
        userAddressEntity.getLatitude());
  }
}
