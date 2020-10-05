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

  /**
   * The users first name.
   */
  private String firstName;

  /**
   * The users last name.
   */
  private String lastName;

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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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
