package de.samply.auth.client.jwt;

/**
 * Thrown when the Jwt can not be parsed (e.g. it does not consist of three parts separated by a
 * dot).
 */
public class JwtParseException extends JwtException {

  private static final long serialVersionUID = -6893070514018699091L;

  /**
   * Constructor for JwtParseException.
   *
   * @param e a {@link java.lang.Throwable} object.
   */
  public JwtParseException(Throwable e) {
    super(e);
  }
}
