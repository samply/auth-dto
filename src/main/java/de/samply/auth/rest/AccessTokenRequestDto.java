package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * An access token request data transfer object. Use this to exchange your code and secret, a
 * signature or a refresh token for a new access token.
 */
@XmlRootElement
public class AccessTokenRequestDto implements Serializable {

  private static final long serialVersionUID = 6461263556638430198L;

  /** The code your application got from the redirect. */
  private String code;

  /**
   * Your client ID, that has been assigned to you by the identity provider. Mandatory, if you want
   * to use the code or the refresh token.
   */
  private String clientId;

  /**
   * Your client secret, that has been assigned to you by the identity provider. Mandatory, if you
   * want to use the code or the refresh token.
   */
  private String clientSecret;

  /**
   * The base64 encoded signature of {@link #code}. Generated with your private key. Mandatory, if
   * you want to use your private key to authenticate.
   */
  private String signature;

  /** The refresh token you want to use in order to get a new access token. */
  private String refreshToken;

  /**
   * The extend token. If you don't want to get an extended access token, leave this field empty
   * (null). You can get an extended token with a signature only.
   */
  private String extended;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @XmlElement(name = "client_id")
  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  @XmlElement(name = "client_secret")
  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  @XmlElement(name = "refresh_token")
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getExtended() {
    return extended;
  }

  public void setExtended(String extended) {
    this.extended = extended;
  }
}
