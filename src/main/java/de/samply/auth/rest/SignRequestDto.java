package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Use this class if you want to authenticate using your private key. Sign the code using the
 * algorithm {@link #algorithm}.
 */
@XmlRootElement
public class SignRequestDto implements Serializable {

  private static final long serialVersionUID = 5996695206929984895L;

  /** The code that you should sign using your private key. */
  private String code;

  /** The expiration date of this sign request. After this date, this sign request is invalid. */
  private long expirationDate;

  /** The algorithm that you need to used to sign the code. */
  private String algorithm;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @XmlElement(name = "exp")
  public long getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(long expirationDate) {
    this.expirationDate = expirationDate;
  }

  public String getAlgorithm() {
    return algorithm;
  }

  public void setAlgorithm(String algorithm) {
    this.algorithm = algorithm;
  }
}
