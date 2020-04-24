/*
 * Copyright (C) 2015 Working Group on Joint Research, University Medical Center Mainz
 * Copyright (C) since 2016 The Samply Community
 *
 * <p>This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * <p>You should have received a copy of the GNU Affero General Public License along with this
 * program; if not, see http://www.gnu.org/licenses.
 *
 * <p>Additional permission under GNU GPL version 3 section 7:
 *
 * <p>If you modify this Program, or any covered work, by linking or combining it with Jersey
 * (https://jersey.java.net) (or a modified version of that library), containing parts covered by
 * the terms of the General Public License, version 2.0, the licensors of this Program grant you
 * additional permission to convey the resulting work.
 */

package de.samply.auth.client.jwt;

import de.samply.auth.client.jwt.JwtVocabulary.TokenType;
import de.samply.auth.rest.LocationDto;
import de.samply.auth.rest.RoleDto;
import de.samply.auth.rest.Usertype;
import de.samply.common.config.OAuth2Client;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONObject;

/**
 * The client side Jwt OpenID Token. Checks the signature and validity of a serialized OpenID ID
 * Token Jwt. Contains for example the users real name, his email address, his address, and other
 * fields.
 */
public class JwtIdToken extends AbstractJwt {

  private static final long serialVersionUID = -4323096832195533045L;

  /** The client ID used to get this ID token. It is used to verify the audience. */
  private final String clientId;

  /** The list of locations that the user belongs to. */
  private List<LocationDto> locations = new ArrayList<>();

  /** The list of roles that the user is a member of. */
  private List<RoleDto> roles = new ArrayList<>();

  /** The map for clientIDs and their permissions. */
  private Map<String, List<String>> permissions = new HashMap<>();

  /**
   * TODO: add javadoc.
   *
   * @param config the OAuth2 client side configuration. The public key is needed to check the
   *     signature.
   * @param serialized the serialized Jwt
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtIdToken(OAuth2Client config, String serialized) throws JwtException {
    this(config.getClientId(), KeyLoader.loadKey(config.getHostPublicKey()), serialized);
  }

  /**
   * TODO: add javadoc.
   *
   * @param config the OAuth2 client side configuration. The public key is needed to check the
   *     signature.
   * @param serialized the serialized Jwt
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtIdToken(OAuth2Client config, String serialized, boolean externalValidation)
      throws JwtException {
    this(config.getClientId(), KeyLoader.loadKey(config.getHostPublicKey()), serialized,
        externalValidation);
  }

  /**
   * TODO: add javadoc.
   *
   * @param clientId Your client ID
   * @param publicKey The IdP's public key
   * @param serialized the serialized ID token
   */
  public JwtIdToken(String clientId, PublicKey publicKey, String serialized) throws JwtException {
    this(clientId, publicKey, serialized, false);
  }

  /**
   * TODO: add javadoc.
   *
   * @param clientId Your client ID
   * @param publicKey The IdP's public key
   * @param serialized the serialized ID token
   * @param externalValidation if true, validation will not be handled internally. necessary for
   *     symmetric signatures.
   */
  public JwtIdToken(String clientId, PublicKey publicKey, String serialized,
      boolean externalValidation) throws JwtException {
    super(publicKey, serialized, externalValidation);
    this.clientId = clientId;

    Object locationsClaim = getClaimsSet().getClaim(JwtVocabulary.LOCATIONS);

    if (locationsClaim instanceof List<?>) {
      List<?> list = (List<?>) locationsClaim;

      for (Object o : list) {
        if (o instanceof JSONObject) {
          JSONObject json = (JSONObject) o;
          LocationDto location = new LocationDto();
          location.setContact((String) json.get(JwtVocabulary.LOCATION_CONTACT));
          location.setDescription((String) json.get(JwtVocabulary.LOCATION_DESCRIPTION));
          location.setId((String) json.get(JwtVocabulary.LOCATION_IDENTIFIER));
          locations.add(location);
        }
      }
    }

    Object rolesClaim = getClaimsSet().getClaim(JwtVocabulary.ROLES);

    if (rolesClaim instanceof List<?>) {
      List<?> list = (List<?>) rolesClaim;

      for (Object o : list) {
        if (o instanceof JSONObject) {
          JSONObject json = (JSONObject) o;
          RoleDto role = new RoleDto();
          role.setIdentifier((String) json.get(JwtVocabulary.ROLE_IDENTIFIER));
          role.setDescription((String) json.get(JwtVocabulary.ROLE_DESCRIPTION));
          role.setName((String) json.get(JwtVocabulary.ROLE_NAME));
          roles.add(role);
        }
      }
    }

    Object permissionsClaim = getClaimsSet().getClaim(JwtVocabulary.PERMISSIONS);
    if (permissionsClaim instanceof Map<?, ?>) {
      Map<String, List<String>> map = (Map<String, List<String>>) permissionsClaim;
      for (String key : map.keySet()) {
        permissions.put(key, map.get(key));
      }
    }
  }

  /**
   * Returns the users real email address.
   *
   * @return the users real email address
   */
  public String getEmail() {
    return (String) getClaimsSet().getClaim(JwtVocabulary.EMAIL);
  }

  /**
   * Returns the real name, or an empty string if this information is not available.
   *
   * @return the users real name
   */
  public String getName() {
    String name = (String) getClaimsSet().getClaim(JwtVocabulary.NAME);
    if (name != null && !name.equals("")) {
      return name;
    } else {
      return (String) getClaimsSet().getClaim("preferred_username");
    }
  }

  /**
   * Returns the language the user selected in Samply.Auth. Always "en" at this point.
   *
   * @return the language the user selected in the identity provider
   */
  public String getLanguage() {
    return (String) getClaimsSet().getClaim(JwtVocabulary.LANG);
  }

  /**
   * Returns the usertype, one of "NORMAL", "REGISTRY", "BRIDGEHEAD".
   */
  public Usertype getUsertype() {
    try {
      return Usertype.valueOf((String) getClaimsSet().getClaim(JwtVocabulary.USERTYPE));
    } catch (Exception e) {
      return Usertype.NORMAL;
    }
  }

  /**
   * Returns the label of the external identity provider, might be null.
   */
  public String getExternalLabel() {
    return (String) getClaimsSet().getClaim(JwtVocabulary.EXTERNAL_LABEL);
  }

  /**
   * Returns a list of roles.
   */
  public List<RoleDto> getRoles() {
    return roles;
  }

  /**
   * Returns the locations that this user belongs to.
   */
  public List<LocationDto> getLocations() {
    return locations;
  }

  @Override
  protected String getTokenType() {
    return TokenType.ID_TOKEN;
  }

  /**
   * Returns the map of clientIDs and their permissions.
   */
  public Map<String, List<String>> getPermissions() {
    return permissions;
  }
}
