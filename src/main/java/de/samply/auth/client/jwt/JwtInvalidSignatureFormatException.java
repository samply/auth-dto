package de.samply.auth.client.jwt;

/** Thrown when the signature has an invalid format. */
public class JwtInvalidSignatureFormatException extends JwtException {

  private static final long serialVersionUID = -2336411296658224883L;

  /** Constructor for JwtInvalidSignatureFormatException. */
  public JwtInvalidSignatureFormatException() {
    super("The signature format is invalid!");
  }
}
