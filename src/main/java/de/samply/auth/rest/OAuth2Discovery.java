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
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This OAuth2Discovery class describes the identity provider as in this <a
 * href="http://openid.net/specs/openid-connect-discovery-1_0.html">OpenID connect draft</a>.
 */
@XmlRootElement
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

  /** The response types that are supported by this identity provider. */
  private List<String> responseTypesSupported;

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
}
