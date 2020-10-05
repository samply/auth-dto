package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/** Created by paul on 4/22/16. */
@XmlRootElement(name = "roles")
public class RoleListDto implements Serializable {

  private List<RoleDto> roles;

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }
}
