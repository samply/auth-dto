# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html) since v2.4.

## [5.0.0]
### Migration of MIG (Frankfurt) and HD-Verbis (Heidelberg)
* #### Changed
    - samply auth parent 3.0 (including samply parent POM 10.1 with Java 8 )
    - update maven dependency (samply.common.config)
* #### Added 
    - `family_name` and `given_name` attributes to ID Token
    - added a map for permissions in the ID token
* #### Removed
    - the `token_type` and `expires_in` attributes are now transmitted, they were already there
  but have been disabled through `@XmlTransient`
  
## [3.1.0] - 2020-05-14
### Added
- Added id_token_signing_alg_values_supported to OAuth2Discovery

## [3.0.2] - 2020-05-08
### Fixed
- Ignore unknown properties in OAuth2Discovery

## [3.0.1] - 2020-04-27
### Added
- list of supported signing algorithms added to OAuth2Discovery

## [3.0.0] - 2020-04-24
### Added
- support jwts that can't be validated due to their signing algorithm
### Changed
- apply google java code style
- renamed JwtKeyMissmatchException to JwtKeyMismatchException
### Fixed
- don't crash if no "nbt" (not valid before) claim is set in the token

## [2.4] - 2018-12-21
### Added
- add client permissions map to IDToken
### Changed
- Roles in the ID Token contain now more than just the identifier
- named the users of a role to members
- switched to SLF4J
### Removed
- Backwards compatibility
### Fixed
- fixed a bug when initializing a JWT ID token
- minor bug fixes
- fix bug in getScopes

## [2.2] - 2015-12-18
### Added
- locations
### Changed
- updated to the latest JWT library

## [2.1] - 2015-11-07
### Added
- Holder of Key JWT (HokToken) and the factory to create a new one
- state parameter in the OAuth2 authentication
- `token_type` and `expires_in` attributes in the access token DTO
- maven site documentation
### Removed
- Backwards compatibility

## [2.0] - 2015-08-03
### Added
- JWTVocabulary
- Scope enum

## [1.8.1] - 2015-07-22
### Added
- `UserInfoDTO` class, used in the `/userinfo` resource

## [1.8.0] - 2015-07-21
### Added
- `usertype` attribute in the ID token
- `roles` attribute in the ID token, which is a list
    of role names that user is member of
- `permission` attribute in the Access token, which is
    a list of permissions

## [1.7.0] - 2015-06-10
### Added
- `type` attribute in all JWTs

## [1.6.1] - 2015-03-06
### Added
- Initial release
