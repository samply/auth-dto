package de.samply.auth.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The access token data transfer object. Has three different tokens:
 *
 * <pre>
 * - access token (must be used to use the APIs from other modules)
 * - id token (must be used to get the users identity, e.g. his name)
 * - refresh token (must be used to get a new valid access token if necessary).
 * </pre>
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessTokenDto implements Serializable {

  private static final long serialVersionUID = -3470420766494134258L;
  /**
   * This token type must be "Bearer" because the implicit flows are not supported in Samply Auth.
   */
  private final String tokenType = "Bearer";
  /**
   * The OAuth2 access token. In Samply.Auth this is a Jwt, signed with the identity providers
   * private key. This access token contains information about the scopes this access token was
   * issued for, the user ID (called subject in OAuth2 terminology) and other fields.
   */
  private String accessToken;
  /**
   * The OAuth2 OpenID token. In Samply.Auth this is a Jwt, signed with the identity providers
   * private key. This token contains various informations about the user, like his name or his
   * email.
   */
  private String idToken;
  /**
   * The refresh token can be used by the OAuth2 client to get a new access token. It has no
   * expiration date.
   */
  private String refreshToken;
  /** The number of seconds that the access token is valid. */
  private int expiresIn = 0;

  /** The number of seconds that the refresh token is valid. */
  private int refreshExpiresIn = 0;

  @XmlElement(name = "access_token")
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @XmlElement(name = "id_token")
  public String getIdToken() {
    return idToken;
  }

  public void setIdToken(String idToken) {
    this.idToken = idToken;
  }

  /**
   * Returns the string that must be used in HTTP request headers.
   *
   * @return a {@link java.lang.String} object.
   */
  @XmlTransient
  public String getHeader() {
    return tokenType + " " + accessToken;
  }

  @XmlElement(name = "refresh_token")
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  @XmlElement(name = "token_type")
  public String getTokenType() {
    return tokenType;
  }

  @XmlElement(name = "expires_in")
  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  @XmlElement(name = "refresh_expires_in")
  public int getRefreshExpiresIn() {
    return refreshExpiresIn;
  }

  public void setRefreshExpiresIn(int refreshExpiresIn) {
    this.refreshExpiresIn = refreshExpiresIn;
  }
}
