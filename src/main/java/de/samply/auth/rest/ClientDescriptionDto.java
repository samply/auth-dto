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
import javax.xml.bind.annotation.XmlRootElement;

/** Represents a client description that is known to the identity provider. */
@XmlRootElement
public class ClientDescriptionDto implements Serializable {

  private static final long serialVersionUID = 7998465172990799186L;

  /** The clients name. */
  private String name;

  /** A more through description of this client. */
  private String description;

  /** The clients OAuth2 client ID. */
  private String clientId;

  /** The clients URL. */
  private String redirectUrl;

  /** The client type. */
  private ClientType type;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter for the field <code>type</code>.
   *
   * @return the type
   * @since 1.4.2
   */
  public ClientType getType() {
    return type;
  }

  /**
   * Setter for the field <code>type</code>.
   *
   * @param type the type to set
   * @since 1.4.2
   */
  public void setType(ClientType type) {
    this.type = type;
  }
}
