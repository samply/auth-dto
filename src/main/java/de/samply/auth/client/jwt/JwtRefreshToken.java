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
import java.util.List;

/**
 * The client side Jwt refresh token. Checks the signature and validity of a serialized Jwt. Use
 * this token to get a new token.
 */
public class JwtRefreshToken extends AbstractJwt {

  private static final long serialVersionUID = 2549311746405805664L;

  /**
   * TODO: add javadoc.
   *
   * @param config the OAuth2 client side configuration. The public key is needed to check the
   *     signature.
   * @param serialized the serialized Jwt
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtRefreshToken(OAuth2Client config, String serialized) throws JwtException {
    super(config, serialized);
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
  public JwtRefreshToken(OAuth2Client config, String serialized, boolean externalValidation)
      throws JwtException {
    super(config, serialized, externalValidation);
  }

  /**
   * TODO: add javadoc.
   *
   * @param key The identity providers public key (needed to check the signature)
   * @param serialized the serialized Jwt
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtRefreshToken(PublicKey key, String serialized) throws JwtException {
    super(key, serialized);
  }

  /**
   * TODO: add javadoc.
   *
   * @param key The identity providers public key (needed to check the signature)
   * @param serialized the serialized Jwt
   * @param externalValidation if true, validation will not be handled internally. necessary for
   *     symmetric signatures.
   * @throws JwtException if any error occurs during deserialization or
   *     signature verification
   */
  public JwtRefreshToken(PublicKey key, String serialized, boolean externalValidation)
      throws JwtException {
    super(key, serialized, externalValidation);
  }

  /**
   * Returns the scopes this refresh token was issued for. The access token you get in exchange for
   * this refresh token will have the same scopes.
   *
   * @return a list of scopes
   */
  @SuppressWarnings("unchecked")
  public List<String> getScopes() {
    return (List<String>) getClaimsSet().getClaim(JwtVocabulary.SCOPE);
  }

  @Override
  protected String getTokenType() {
    return TokenType.REFRESH_TOKEN;
  }
}
