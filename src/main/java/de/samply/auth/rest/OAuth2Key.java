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
