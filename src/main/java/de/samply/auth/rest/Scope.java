package de.samply.auth.rest;

/** A scope is a permission that will be included in the access token. */
public enum Scope {
  OPENID("openid"),
  MDR("mdr"),
  LOGIN("login"),
  FORMREPOSITORY("formrepository");

  private final String identifier;

  Scope(String identifier) {
    this.identifier = identifier;
  }

  public String getIdentifier() {
    return identifier;
  }
}
