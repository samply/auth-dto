package de.samply.auth.client.jwt;

import com.nimbusds.jwt.JWTParser;
import com.nimbusds.jwt.SignedJWT;
import de.samply.auth.client.jwt.JwtVocabulary.TokenType;
import java.text.ParseException;

/** A HokJWT proves the possession of a private key. It is self signed using the private key. */
public class JwtHokToken extends AbstractJwt {

  private static final long serialVersionUID = 8606677211777174454L;

  /**
   * TODO: add javadoc.
   */
  public JwtHokToken(String serialized) throws JwtException, ParseException {
    super(
        KeyLoader.loadKey(
            (String)
                (new SignedJWT(
                        JWTParser.parse(serialized).getParsedParts()[0],
                        JWTParser.parse(serialized).getParsedParts()[1],
                        JWTParser.parse(serialized).getParsedParts()[2]))
                    .getJWTClaimsSet()
                    .getClaim(JwtVocabulary.KEY)),
        serialized);
  }

  /* (non-Javadoc)
   * @see de.samply.auth.client.jwt.AbstractJwt#getTokenType()
   */
  @Override
  protected String getTokenType() {
    return TokenType.HOK_TOKEN;
  }
}
