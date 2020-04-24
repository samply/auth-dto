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

/**
 * If you want to register with an email, a name, a description and a public key, use this class.
 */
@XmlRootElement
public class RegistrationRequestDto implements Serializable {

  private static final long serialVersionUID = 1824954304739574105L;

  /** The email that may be used to verify this user manually. */
  private String email;

  /** The name of the user. May be the registrys name or a real name. */
  private String name;

  /** A description of the registry. */
  private String description;

  /** The base64 encoded public key that will be used to authenticate the application. */
  private String base64EncodedPublicKey;

  /** The contact data that is used for verification. */
  private String contactData;

  /** The usertype for the new user. */
  private Usertype usertype;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBase64EncodedPublicKey() {
    return base64EncodedPublicKey;
  }

  public void setBase64EncodedPublicKey(String base64EncodedPublicKey) {
    this.base64EncodedPublicKey = base64EncodedPublicKey;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Getter for the field <code>contactData</code>.
   *
   * @return the contactData
   */
  public String getContactData() {
    return contactData;
  }

  /**
   * Setter for the field <code>contactData</code>.
   *
   * @param contactData the contactData to set
   */
  public void setContactData(String contactData) {
    this.contactData = contactData;
  }

  public Usertype getUsertype() {
    return usertype;
  }

  public void setUsertype(Usertype usertype) {
    this.usertype = usertype;
  }
}
