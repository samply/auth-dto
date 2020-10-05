# Data Transfer Objects

This `auth-dto` library defines POJOs that you can be use for the Samply.Auth REST interface.
The Samply.Auth identity provider is an OAuth2 implementation that extends the
OAuth2 specification by Samply specific requirements, see [RFC
6749](https://tools.ietf.org/html/rfc6749).

Also this library offers Jwt classes that can be used to verify signed access
tokens, ID tokens or refresh tokens. There are three ways to get an access
token. It depends on the applications use case which way should be used, see the getting started
documentation for more information on this subject.


## Classes

There are several classes, that ease the use of Jwt in your java application.
Each class offers besides methods for validation, methods to access the Jwt
specific

- `JwtAccessToken`: The implementation of the Jwt access token
- `JwtIdToken`: The implementation of the Jwt ID token
- `JwtRefreshToken`: The implementation of the Jwt refresh token


## Data Transfer Objects

Besides the Jwt implementations this library offers data transfer objects, that
you can use for the REST interface:

- `AccessTokenDto` (result) and `AccessTokenRequestDto` (request) for the `/token` resource
- `RegistrationDto` (result) and `RegistrationRequestDto` (request) for the `/registration` resource
- `SignRequestDto` (result) and `KeyIdentificationDto` (request) for the `/sign_request` resource

and so on.


## Build

Use maven to build the jar:

```
mvn clean package
```

Use it as a dependency:

```xml
<dependency>
    <groupId>de.samply</groupId>
    <artifactId>auth-dto</artifactId>
    <version>VERSION</version>
</dependency>
```

## License

Copyright 2020 The Samply Development Community

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
