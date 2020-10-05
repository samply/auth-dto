package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/** Created by paul on 4/22/16. */
@XmlRootElement(name = "role")
public class RoleDto implements Serializable {

  private String name;

  private String identifier;

  private String description;

  private List<UserDto> members;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<UserDto> getMembers() {
    return members;
  }

  public void setMembers(List<UserDto> members) {
    this.members = members;
  }
}
