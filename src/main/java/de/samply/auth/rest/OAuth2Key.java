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

package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** A public key that can be used to verify signatures. */
@XmlRootElement
public class OAuth2Key implements Serializable {

  private static final long serialVersionUID = -8269166709056582722L;

  /** The key type, usually this is either RSA or EC. */
  private String keyType;

  /** The usage of this key. */
  private String use;

  /** The keys ID. */
  private String keyId;

  /** The RSA modulus. */
  private String modulus;

  /** The RSA public exponent. */
  private String exponent;

  /** The Base64URL encoded DER formatted public key. */
  private String derFormat;

  /** The Base64 encoded DER formatted public key. */
  private String base64DerFormat;

  @XmlElement(name = "kty")
  public String getKeyType() {
    return keyType;
  }

  public void setKeyType(String keyType) {
    this.keyType = keyType;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }

  @XmlElement(name = "kid")
  public String getKeyId() {
    return keyId;
  }

  public void setKeyId(String keyId) {
    this.keyId = keyId;
  }

  public String getModulus() {
    return modulus;
  }

  public void setModulus(String modulus) {
    this.modulus = modulus;
  }

  public String getExponent() {
    return exponent;
  }

  public void setExponent(String exponent) {
    this.exponent = exponent;
  }

  public String getDerFormat() {
    return derFormat;
  }

  public void setDerFormat(String derFormat) {
    this.derFormat = derFormat;
  }

  public String getBase64DerFormat() {
    return base64DerFormat;
  }

  public void setBase64DerFormat(String base64DerFormat) {
    this.base64DerFormat = base64DerFormat;
  }
}
