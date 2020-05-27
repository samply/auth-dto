# Data Transfer Objects

This `auth-dto` library defines POJOs that you can be use for the Samply.Auth REST interface.
The Samply.Auth identity provider is an OAuth2 implementation that extends the
OAuth2 specification by Samply specific requirements, see [RFC
6749](https://tools.ietf.org/html/rfc6749).

Also this library offers JWT classes that can be used to verify signed access
tokens, ID tokens or refresh tokens. There are three ways to get an access
token. It depends on the applications use case which way should be used, see the getting started
documentation for more information on this subject.


## Classes

There are several classes, that ease the use of JWT in your java application.
Each class offers besides methods for validation, methods to access the JWT
specific

- `JWTAccessToken`: The implementation of the JWT access token
- `JWTIDToken`: The implementation of the JWT ID token
- `JWTRefreshToken`: The implementation of the JWT refresh token


## Data Transfer Objects

Besides the JWT implementations this library offers data transfer objects, that
you can use for the REST interface:

- `AccessTokenDTO` (result) and `AccessTokenRequestDTO` (request) for the `/token` resource
- `RegistrationDTO` (result) and `RegistrationRequestDTO` (request) for the `/registration` resource
- `SignRequestDTO` (result) and `KeyIdentificationDTO` (request) for the `/sign_request` resource

and so on.


## Build

Use maven to build the jar:

```
mvn clean package
```

Use it as a dependency:

```xml
<dependency>
    <groupId>de.mig.samply</groupId>
    <artifactId>auth-dto</artifactId>
    <version>VERSION</version>
</dependency>
```