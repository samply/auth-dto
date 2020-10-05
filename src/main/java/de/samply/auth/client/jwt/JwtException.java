package de.samply.auth.client.jwt;

/** Thrown when an exception occurs while verifying a signature or deserializing a Jwt. */
public class JwtException extends Exception {

  private static final long serialVersionUID = -1040871078531393092L;

  /**
   * Constructor for JwtException.
   *
   * @param string a {@link java.lang.String} object.
   */
  public JwtException(String string) {
    super(string);
  }

  /**
   * Constructor for JwtException.
   *
   * @param e a {@link java.lang.Throwable} object.
   */
  public JwtException(Throwable e) {
    super(e);
  }
}
