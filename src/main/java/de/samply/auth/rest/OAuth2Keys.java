package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/** A list of keys used by this identity provider. */
@XmlRootElement
public class OAuth2Keys implements Serializable {

  private static final long serialVersionUID = 1448456306235887664L;
  /** The list of keys used by this identity provider. */
  private List<OAuth2Key> keys;

  public List<OAuth2Key> getKeys() {
    return keys;
  }

  public void setKeys(List<OAuth2Key> keys) {
    this.keys = keys;
  }
}
