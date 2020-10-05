package de.samply.auth.client.jwt;

/**
 * Thrown when the providers key can not be used to verify a signature (e.g. when the Jwt is signed
 * using RSA and SHAx, but the key is an elliptic curve key).
 */
public class JwtKeyMismatchException extends JwtException {

  private static final long serialVersionUID = 4099411283509121729L;

  /** Constructor for JwtKeyMismatchException. */
  public JwtKeyMismatchException() {
    super("The key can not be used to verify this kind of signature!");
  }
}
