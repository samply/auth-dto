package de.samply.auth.rest;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/** this locality information DTO contains informations about a locality. */
@XmlRootElement(name = "location")
public class LocationDto implements Serializable {

  private static final long serialVersionUID = 2383909728035266461L;

  /** The identifier for this locality, e.g. "FRANKFURT". */
  private String id;

  /** The human readable name of this locality, e.g. "Frankfurt". */
  private String name;

  /** The human readable description of this locality, e.g. "Der DKTK Standort in Frankfurt". */
  private String description;

  /** The contact information for this location. */
  private String contact;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }
}
