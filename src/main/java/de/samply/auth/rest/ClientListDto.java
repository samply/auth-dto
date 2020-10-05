package de.samply.auth.rest;

import java.io.Serializable;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/** A collection of client descriptions. */
@XmlRootElement
public class ClientListDto implements Serializable {

  private static final long serialVersionUID = -7633768652982283505L;

  /** The collection of clients known to this Auth component. */
  private Collection<ClientDescriptionDto> clients;

  /** Constructor for ClientListDto. */
  public ClientListDto() {}

  /**
   * Constructor for ClientListDto.
   *
   * @param clients a {@link java.util.Collection} object.
   */
  public ClientListDto(Collection<ClientDescriptionDto> clients) {
    this.clients = clients;
  }

  /**
   * Getter for the field <code>clients</code>.
   *
   * @return the clients
   */
  public Collection<ClientDescriptionDto> getClients() {
    return clients;
  }

  /**
   * Setter for the field <code>clients</code>.
   *
   * @param clients the clients to set
   */
  public void setClients(Collection<ClientDescriptionDto> clients) {
    this.clients = clients;
  }
}
