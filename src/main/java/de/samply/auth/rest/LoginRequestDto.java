package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/** Requests a code for another OAuth2 application. */
@XmlRootElement
public class LoginRequestDto implements Serializable {

  private static final long serialVersionUID = 6573453816051173442L;
  /** The client ID. */
  private String clientId;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }
}
