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

/** Represents a User registered in Samply Auth. */
@XmlRootElement(name = "user")
public class UserDto implements Serializable {

  private static final long serialVersionUID = 422567854147913922L;

  /** The users real name. */
  private String name;

  /** The users email. */
  private String email;

  /** His full ID in the identity provider. */
  private String id;

  /** If true, the users email is verified by Samply Auth. */
  private Boolean emailVerified;

  /** This is the external identity providers label. */
  private String externalLabel;

  /** The contact informations of this user. */
  private String contactInformation;

  /** The location of this user. */
  private List<LocationDto> locations;

  /** The roles that this user is a member of. */
  private List<RoleDto> roles;

  public String getName() {
    return name;
  }

  public void setName(String realName) {
    this.name = realName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @XmlElement(name = "email_verified")
  public Boolean getEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(Boolean verifiedEmail) {
    this.emailVerified = verifiedEmail;
  }

  @XmlElement(name = "external_label")
  public String getExternalLabel() {
    return externalLabel;
  }

  public void setExternalLabel(String externalLabel) {
    this.externalLabel = externalLabel;
  }

  @XmlElement(name = "contact_information")
  public String getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(String contactInformation) {
    this.contactInformation = contactInformation;
  }

  public List<LocationDto> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationDto> locations) {
    this.locations = locations;
  }

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }
}
