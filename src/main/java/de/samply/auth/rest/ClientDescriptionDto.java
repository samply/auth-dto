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
