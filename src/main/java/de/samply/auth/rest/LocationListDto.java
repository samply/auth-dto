package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "locations")
public class LocationListDto implements Serializable {

  private static final long serialVersionUID = 1728692225257440782L;

  private List<LocationDto> locations;

  public List<LocationDto> getLocations() {
    return locations;
  }

  public void setLocations(List<LocationDto> locations) {
    this.locations = locations;
  }
}
