package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The login data transfer object must be used to generate a valid code for another application.
 * Redirect the user to the redirect url in this DTO.
 */
@XmlRootElement
public class LoginDto implements Serializable {

  private static final long serialVersionUID = 3759760373388897073L;

  /** The redirect url for the client you requested. */
  private String redirectUrl;

  /** The code the client needs. */
  private String code;

  @XmlElement(name = "redirect_url")
  public String getRedirectUrl() {
    return redirectUrl;
  }

  public void setRedirectUrl(String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
