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
      JWSSigner signer = new RSASSASigner((RSAPrivateKey) privateKey);
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
