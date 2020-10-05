package de.samply.auth.client.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Calendar;
import java.util.UUID;

/**
 * A basic Jwt that contains an expiration date and a "not-before" date that describe the validity
 * of this Jwt. It also contains the type of this Jwt and a random UUID as Jwt-ID.
 */
public class Jwt {

  /** The JWTID of this Jwt. */
  private final String jwtid;
  protected JWTClaimsSet.Builder builder;

  /**
   * Initializes this Jwt with the given type and hours of validity from now on.
   */
  public Jwt(String type, int hours) {
    builder = new JWTClaimsSet.Builder();
    builder.claim(JwtVocabulary.TYPE, type);

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MINUTE, -15);

    builder.notBeforeTime(cal.getTime());

    cal = Calendar.getInstance();
    cal.add(Calendar.HOUR, hours);

    builder.expirationTime(cal.getTime());

    jwtid = UUID.randomUUID().toString();
    builder.jwtID(getJwtid());
  }

  protected String sign(PrivateKey privateKey) throws JOSEException {
    if (privateKey instanceof RSAPrivateKey) {
      SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS512), builder.build());
      JWSSigner signer = new RSASSASigner(privateKey);
      signedJwt.sign(signer);
      return signedJwt.serialize();
    } else if (privateKey instanceof ECPrivateKey) {
      SignedJWT signedJwt = new SignedJWT(new JWSHeader(JWSAlgorithm.ES512), builder.build());
      JWSSigner signer = new ECDSASigner((ECPrivateKey) privateKey);
      signedJwt.sign(signer);
      return signedJwt.serialize();
    } else {
      throw new UnsupportedOperationException(
          "Unknown key type: " + privateKey.getClass().getCanonicalName());
    }
  }

  public JWTClaimsSet.Builder getBuilder() {
    return builder;
  }

  public String getJwtid() {
    return jwtid;
  }
}
