package de.samply.auth.rest;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/** Represents a list of users known to Samply Auth. */
@XmlRootElement(name = "users")
public class UserListDto implements Serializable {

  private static final long serialVersionUID = 6633434958254790800L;

  private List<UserDto> users;

  public List<UserDto> getUsers() {
    return users;
  }

  public void setUsers(List<UserDto> users) {
    this.users = users;
  }
}
