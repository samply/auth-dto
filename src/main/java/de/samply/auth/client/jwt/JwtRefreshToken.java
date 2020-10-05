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
