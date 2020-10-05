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
