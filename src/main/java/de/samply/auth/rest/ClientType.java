package de.samply.auth.rest;

/**
 * The type describes the type of application that is running on the OAuth2 client. This may be a
 * metadata repository, a form repository or something else.
 *
 * @since 1.4.2
 */
public enum ClientType {
  MDR,
  FORMREPOSITORY,
  UNDEFINED
}
