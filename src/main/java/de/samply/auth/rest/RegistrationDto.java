package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The registration confirmation. The key ID can be used to request a code to sign in order to
 * authenticate with your private key.
 */
@XmlRootElement
public class RegistrationDto implements Serializable {

  private static final long serialVersionUID = -2867964125820652211L;

  /** Your user ID. */
  private int userId;

  /** Your key ID. */
  private int keyId;

  @XmlElement(name = "user_id")
  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @XmlElement(name = "key_id")
  public int getKeyId() {
    return keyId;
  }

  public void setKeyId(int keyId) {
    this.keyId = keyId;
  }
}
