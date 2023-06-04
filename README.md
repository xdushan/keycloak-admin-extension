# keycloak-admin-extension

This project is a keycloak extension to enahance the `/admin` API with custom rest resources and entities.

### Implementation
The interfaces listed below required to implemented in order to extend custom REST resource with `/admin` endpoint.
+ [AdminRealmResourceProvider.java](https://github.com/keycloak/keycloak/blob/b63fccb062eeded3310439242c5693dd4e120d90/services/src/main/java/org/keycloak/services/resources/admin/ext/AdminRealmResourceProvider.java)
+ [AdminRealmResourceProviderFactory](https://github.com/keycloak/keycloak/blob/b63fccb062eeded3310439242c5693dd4e120d90/services/src/main/java/org/keycloak/services/resources/admin/ext/AdminRealmResourceProviderFactory.java)

The following Keycloak extensions includes in the project.
- Custom entity extension- [UserAddressEntity](src/main/java/com/zyntaxmind/keycloak/admin/extension/entity/UserAddressEntity.java) to extend users address with the keycloak.
- Custom REST resources extension- [AdminRealmExtensionResource](src/main/java/com/zyntaxmind/keycloak/admin/extension/resource/AdminRealmExtensionResource.java) to extend resource path for user address.
- Coustom SPI - [UserAddressSpi](src/main/java/com/zyntaxmind/keycloak/admin/extension/spi/UserAddressSpi.java) to provide custom SPI for user address.

### Build
- `./gradlew clean build`
- Copy keycloak-admin-extension-1.0.0.jar inside `build/libs` folder into `/opt/keycloak/providers` in Keycloak server.
- `./gradlew clean build` [keycloak-admin-extension-model](https://github.com/xdushan/keycloak-admin-extension-model) project.
- Copy keycloak-admin-extension-model-1.0.0.jar inside `build/libs` folder into `/opt/keycloak/providers` in Keycloak server.


### RUN
- Restart the keycloak server.

### TEST

Below APIs are available in this service to test. Run below curl command to test the extended APIs.

GET - find all the addresses for specific user.
```
'curl --location '${keycloak_host}/admin/realms/${realm}/ext/users/${user_id}/addresses' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ${access_token_with_admin_access}'
```

POST - create new address for the user.
```
curl --location '${keycloak_host}/admin/realms/${realm}/ext/users/${user_id}/addresses' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ${access_token_with_admin_access}' \
--data '{
    "address1": "No, 5",
    "address2": "street 1",
    "cityId": "26",
    "postalCode": "10050",
    "state": "State1",
    "countryId": "2",
    "longitude": null,
    "latitude": null
}'
```

GET - find specific address for the user.
```
curl --location '${keycloak_host}/admin/realms/${realm}/ext/users/${user_id}/addresses/${address_id}' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ${access_token_with_admin_access}'
```


PUT - update specific address for the user.
```
curl --location '${keycloak_host}/admin/realms/${realm}/ext/users/${user_id}/addresses' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ${access_token_with_admin_access}' \
--data '{
    "address1": "No, 65",
    "address2": "street 4",
    "cityId": "26",
    "postalCode": "10050",
    "state": "State1",
    "countryId": "2",
    "longitude": 35.22,
    "latitude": 14.33
}'
```

DELETE - delete specific address for the user.
```
curl --location --request DELETE '${keycloak_host}/admin/realms/${realm}/ext/users/${user_id}/addresses/${address_id}' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ${access_token_with_admin_access}'
```

