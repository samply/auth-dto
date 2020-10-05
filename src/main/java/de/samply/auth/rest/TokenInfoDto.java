package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This Token information DTO represents all necessary informations about an access token. Lets the
 * identity provider check the access tokens signature.
 */
@XmlRootElement
public class TokenInfoDto implements Serializable {

  private static final long serialVersionUID = -7757410950511881892L;

  /** The tokens expiration date. The token is invalid, if the expiration is in the past. */
  private long expirationDate;

  /** The user ID. This is usually a URL of the issuer and an integer */
  private String subject;

  /** The list of scopes this access token may be used for. */
  private List<String> scope;

  /** A random string. Ignore this. */
  private String jti;

  /** The date before which the access token is not valid. */
  private long notBefore;

  /** The date at which the access token was issued. */
  private long issuedAt;

  /** The URL of the issuer. */
  private String issuer;

  public long getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(long expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public List<String> getScope() {
    return scope;
  }

  public void setScope(List<String> scope) {
    this.scope = scope;
  }

  public String getJti() {
    return jti;
  }

  public void setJti(String nonce) {
    this.jti = nonce;
  }

  public long getNotBefore() {
    return notBefore;
  }

  public void setNotBefore(long notBefore) {
    this.notBefore = notBefore;
  }

  public long getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(long issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }
}
