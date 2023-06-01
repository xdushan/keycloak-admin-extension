package com.zyntaxmind.keycloak.admin.extension.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/***
 * 
 * @author dush
 *
 */

@Entity
@Table(name = "USER_ADDRESS", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "ID", "USER_ID" })
})
@NamedQueries({
  @NamedQuery(name = "findByUserId", query = "select u from UserAddressEntity u where u.userId = :userId"),
  @NamedQuery(name = "findByIdAndUserId", query = "select u from UserAddressEntity u where u.id = :id and u.userId = :userId")
})
public class UserAddressEntity {

  @Id
  @Column(name = "ID")
  private String id;
  
  @Column(name = "USER_ID")
  protected String userId;

  @Column(name = "ADDRESS_1", nullable = false)
  private String addres1;

  @Column(name = "ADDRESS_2")
  private String address2;

  @Column(name = "CITY_ID", nullable = false)
  private String cityId;

  @Column(name = "POSTAL_CODE", nullable = false)
  private String postalCode;

  @Column(name = "STATE")
  private String state;

  @Column(name = "COUNTRY_ID", nullable = false)
  private String countryId;

  @Column(name = "LONGITUDE")
  private Double longitude;
  
  @Column(name = "LATITUDE")
  private Double latitude;
  
  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return the addres1
   */
  public String getAddres1() {
    return addres1;
  }

  /**
   * @param addres1 the addres1 to set
   */
  public void setAddres1(String addres1) {
    this.addres1 = addres1;
  }

  /**
   * @return the address2
   */
  public String getAddress2() {
    return address2;
  }

  /**
   * @param address2 the address2 to set
   */
  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  /**
   * @return the cityId
   */
  public String getCityId() {
    return cityId;
  }

  /**
   * @param cityId the cityId to set
   */
  public void setCityId(String cityId) {
    this.cityId = cityId;
  }

  /**
   * @return the postalCode
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * @param postalCode the postalCode to set
   */
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  /**
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(String state) {
    this.state = state;
  }

  /**
   * @return the countryId
   */
  public String getCountryId() {
    return countryId;
  }

  /**
   * @param countryId the countryId to set
   */
  public void setCountryId(String countryId) {
    this.countryId = countryId;
  }

  /**
   * @return the longitude
   */
  public Double getLongitude() {
    return longitude;
  }

  /**
   * @param longitude the longitude to set
   */
  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  /**
   * @return the latitude
   */
  public Double getLatitude() {
    return latitude;
  }

  /**
   * @param latitude the latitude to set
   */
  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null) return false;
      if (!(o instanceof UserAddressEntity)) return false;

      UserAddressEntity that = (UserAddressEntity) o;

      if (!id.equals(that.getId())) return false;

      return true;
  }

  @Override
  public int hashCode() {
      return id.hashCode();
  }

}
