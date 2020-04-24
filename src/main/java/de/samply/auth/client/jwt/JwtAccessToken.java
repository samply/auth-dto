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
import de.samply.common.config.OAuth2Client;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

/**
 * The client side Jwt access token. Checks the signature and validity of a serialized Jwt. Contains
 * information about access control. Use this token in the Authorization Header in HTTP requests.
 */
public class JwtAccessToken extends AbstractJwt {

  private static final long serialVersionUID = 1399797953598510747L;

  /** Keycloak assigns roles to the access token, not the ID token. */
  private List<String> realmRoles = new ArrayList<>();

  private Map<String, List<String>> resourceRoles = new HashMap<>();

  /**
   * TODO: add javadoc.
   *
   * @param key The identity providers public key (needed to check the signature)
   * @param serialized the serialized Jwt
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtAccessToken(PublicKey key, String serialized) throws JwtException {
    super(key, serialized);
  }

  /**
   * TODO: add javadoc.
   *
   * @param key The identity providers public key (needed to check the signature)
   * @param serialized the serialized Jwt
   * @param externalValidation if true, validation will not be handled internally. necessary for
   *     symmetric signatures.
   * @throws JwtException if any error occurs during deserialization or signature verification
   */
  public JwtAccessToken(PublicKey key, String serialized, boolean externalValidation)
      throws JwtException {
    super(key, serialized, externalValidation);
    init();
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
  public JwtAccessToken(OAuth2Client config, String serialized) throws JwtException {
    super(config, serialized);
    init();
  }

  /**
   * TODO: add javadoc.
   *
   * @param config the OAuth2 client side configuration. The public key is needed to check the
   *     signature.
   * @param serialized the serialized Jwt
   * @param externalValidation if true, validation will not be handled internally. necessary for
   *     symmetric signatures.
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtAccessToken(OAuth2Client config, String serialized, boolean externalValidation)
      throws JwtException {
    super(config, serialized, externalValidation);
    init();
  }

  /**
   * Get all realm and resource claims from the access token.
   */
  private void init() throws JwtException {
    try {
      JSONObject realmAccessClaims =
          (JSONObject) getClaimsSet().getClaim(JwtVocabulary.REALM_ACCESS);
      JSONArray roleClaims = (JSONArray) realmAccessClaims.get(JwtVocabulary.ROLES);
      for (Object roleClaim : roleClaims) {
        realmRoles.add((String) roleClaim);
      }

      JSONObject resourceAccessClaims =
          (JSONObject) getClaimsSet().getClaim(JwtVocabulary.RESOURCE_ACCESS);

      for (Map.Entry<String, Object> entry : resourceAccessClaims.entrySet()) {
        List<String> roles = new ArrayList<>();
        JSONObject resourceAccessClaim = (JSONObject) entry.getValue();
        JSONArray resourceAccessRoleClaim =
            (JSONArray) resourceAccessClaim.get(JwtVocabulary.ROLES);

        for (Object roleClaim : resourceAccessRoleClaim) {
          roles.add((String) roleClaim);
        }
        resourceRoles.put(entry.getKey(), roles);
      }
    } catch (Exception e) {
      // Do nothing for now
    }
  }

  /**
   * Returns all scopes this access token was issued for.
   *
   * @return the list of scopes
   */
  @SuppressWarnings("unchecked")
  public List<String> getScopes() {
    try {
      return new ArrayList<>(
          Arrays.asList(((String) getClaimsSet().getClaim(JwtVocabulary.SCOPE)).split(" ")));
    } catch (NullPointerException npe) {
      System.err.println("NPE caught. Returning empty list");
      return new ArrayList<>();
    }
  }

  /**
   * Checks if there this is an extended access token.
   */
  public boolean isExtended() {
    return getClaimsSet().getClaim(JwtVocabulary.KEY) != null;
  }

  /**
   * Returns the included state from the request.
   */
  public String getState() {
    return (String) getClaimsSet().getClaim(JwtVocabulary.STATE);
  }

  /**
   * Returns the string that must be used in the "Authorization" header.
   */
  public String getHeader() {
    return "Bearer " + getSerialized();
  }

  /**
   * Returns a list of realm role names.
   */
  public List<String> getRealmRoles() {
    return realmRoles;
  }

  /**
   * Returns a list of resource role names.
   */
  public Map<String, List<String>> getResourceRoles() {
    return resourceRoles;
  }

  @Override
  protected String getTokenType() {
    return TokenType.ACCESS_TOKEN;
  }
}
