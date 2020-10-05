package de.samply.auth.client.jwt;

import com.nimbusds.jose.JOSEException;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

/**
 * Jwt Holder of Key Factory. This factory generates a new HokToken using your private key. Use this
 * token in addition to an access token.
 */
public class JwtHokFactory {

  /**
   * Generates a new HokToken using your private key. Use this token in addition to an access token.
   */
  public static String newHokToken(PrivateKey privateKey, PublicKey publicKey, int hours)
      throws JOSEException {
    Jwt jwt = new Jwt(JwtVocabulary.TokenType.HOK_TOKEN, 2);
    jwt.getBuilder().claim(JwtVocabulary.KEY, Base64.encodeBase64String(publicKey.getEncoded()));
    return jwt.sign(privateKey);
  }

  /**
   * Generates a new 256 bit strong random string.
   *
   * @return a {@link java.lang.String} object.
   */
  public static synchronized String newRandomString() {
    return newRandomString(32);
  }

  /**
   * Generates a new random string with the given length.
   *
   * @return a {@link java.lang.String} object.
   */
  public static synchronized String newRandomString(int length) {
    return new BigInteger(length * 8, new SecureRandom()).toString(32);
  }
}
