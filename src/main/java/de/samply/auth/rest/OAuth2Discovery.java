package de.samply.auth.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This OAuth2Discovery class describes the identity provider as in this <a
 * href="http://openid.net/specs/openid-connect-discovery-1_0.html">OpenID connect draft</a>.
 */
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class OAuth2Discovery implements Serializable {

  private static final long serialVersionUID = 450779992698169184L;

  /** The application name of the identity provider. */
  private String application;

  /** The version of the identity provider. */
  private String version;

  /** The issuer. This is usually the URL of this identity provider. */
  private String issuer;

  /** The user should be redirected to this URL. */
  private String authorizationEndpoint;

  /** The token endpoint is used to get an access token. */
  private String tokenEndpoint;

  /** A list of all supported scopes by this identity provider. */
  private List<String> scopesSupported;

  /**
   * The URL of the used keys by this identity provider. The keys are used to sign access tokens and
   * OpenID tokens.
   */
  private String jwksUri;

  /**
   * The list of supported token signing algorithms.
   */
  private List<String> supportedSigningAlgs;

  /**
   * The list of supported ID token signing algorithms.
   */
  private List<String> supportedIdTokenSigningAlgs;

  /** The response types that are supported by this identity provider. */
  private List<String> responseTypesSupported;

  /**
   * The supported mechanisms of the token endpoint that are used to verify the
   * identity of the client.
   */
  private List<String> tokenEndpointAuthMethodsSupported;

  public String getIssuer() {
    return issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }

  @XmlElement(name = "authorization_endpoint")
  public String getAuthorizationEndpoint() {
    return authorizationEndpoint;
  }

  public void setAuthorizationEndpoint(String authorizationEndpoint) {
    this.authorizationEndpoint = authorizationEndpoint;
  }

  @XmlElement(name = "token_endpoint")
  public String getTokenEndpoint() {
    return tokenEndpoint;
  }

  public void setTokenEndpoint(String tokenEndpoint) {
    this.tokenEndpoint = tokenEndpoint;
  }

  @XmlElement(name = "scopes_supported")
  public List<String> getScopesSupported() {
    return scopesSupported;
  }

  public void setScopesSupported(List<String> scopesSupported) {
    this.scopesSupported = scopesSupported;
  }

  @XmlElement(name = "jwks_uri")
  public String getJwksUri() {
    return jwksUri;
  }

  public void setJwksUri(String jwksUri) {
    this.jwksUri = jwksUri;
  }

  @XmlElement(name = "request_object_signing_alg_values_supported")
  public List<String> getSupportedSigningAlgs() {
    return supportedSigningAlgs;
  }

  public void setSupportedSigningAlgs(List<String> supportedSigningAlgs) {
    this.supportedSigningAlgs = supportedSigningAlgs;
  }

  @XmlElement(name = "id_token_signing_alg_values_supported")
  public List<String> getSupportedIdTokenSigningAlgs() {
    return supportedIdTokenSigningAlgs;
  }

  public void setSupportedIdTokenSigningAlgs(List<String> supportedIdTokenSigningAlgs) {
    this.supportedIdTokenSigningAlgs = supportedIdTokenSigningAlgs;
  }

  @XmlElement(name = "response_types_supported")
  public List<String> getResponseTypesSupported() {
    return responseTypesSupported;
  }

  public void setResponseTypesSupported(List<String> responseTypesSupported) {
    this.responseTypesSupported = responseTypesSupported;
  }

  public String getApplication() {
    return application;
  }

  public void setApplication(String application) {
    this.application = application;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @XmlElement(name = "token_endpoint_auth_methods_supported")
  public List<String> getTokenEndpointAuthMethodsSupported() {
    return tokenEndpointAuthMethodsSupported;
  }

  public void setTokenEndpointAuthMethodsSupported(List<String> tokenEndpointAuthMethodsSupported) {
    this.tokenEndpointAuthMethodsSupported = tokenEndpointAuthMethodsSupported;
  }
}
