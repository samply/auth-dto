package de.samply.auth.client.jwt;

import java.security.PublicKey;

/**
 * The Jwt access token returned by ADFS. Since the attributes depend on the ADFS configuration this
 * class does not provide convenience methods like "getEmail".
 */
public class JwtAdfsAccessToken extends AbstractJwt {

  private static final long serialVersionUID = -2222224959776178695L;

  public JwtAdfsAccessToken(PublicKey publicKey, String serialized) throws JwtException {
    super(publicKey, serialized);
  }

  @Override
  protected String getTokenType() {
    return null;
  }
}
