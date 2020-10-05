package de.samply.auth.utils;

import de.samply.string.util.StringUtil;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/** This class offers some basic hash algorithms that are convenient to use. */
public class HashUtils {

  /**
   * Hashes the input string with the SHA-1 algorithm and returns the string hex representation.
   *
   * @param input the string that will be hashed
   * @return the string hex representation of the SHA-1 hash
   */
  public static String sha1(String input) {
    return sha1(input.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Hashes the byte array with the SHA-1 algorithm and returns the string hex representation.
   *
   * @param input the byte array that will be hashed
   * @return the string hex representation of the SHA-1 hash
   */
  public static String sha1(byte[] input) {
    try {
      return hash("SHA-1", input);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Hashes the input string with the SHA-512 algorithm and returns the string hex representation.
   *
   * @param input the string that will be hashed
   * @return the string hex representation of the SHA-512 hash
   */
  public static String sha512(String input) {
    return sha512(input.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Hashes the input byte array with the SHA-512 algorithm and returns the string hex
   * representation.
   *
   * @param input the byte array that will be hashed
   * @return the string hex representation of the SHA-512 hash
   */
  public static String sha512(byte[] input) {
    try {
      return hash("SHA-512", input);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Hashes the input byte array using the given algorithm and returns the string hex
   * representation.
   *
   * @param algorithm the hash algorithm (e.g. "SHA-512")
   * @param input the byte array that will be hashed
   * @return the string hex representation of the hash
   * @throws NoSuchAlgorithmException if the algorithm does not exist in this JVM.
   */
  public static String hash(String algorithm, byte[] input) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance(algorithm);
    digest.update(input);
    return Hex.encodeHexString(digest.digest());
  }

  /**
   * Returns the fingerprint of the given Base64 encoded key. The input string is not checked, so it
   * might be any valid Base64 encoded content.
   *
   * @param base64EncodedKey a base64 encoded key.
   * @return the fingerprint, e.g. "ab:ec:...."
   */
  public static String getFingerPrint(String base64EncodedKey) {
    return StringUtil.join(
        Objects.requireNonNull(HashUtils.sha1(Base64.decodeBase64(base64EncodedKey)))
                .split("(?<=\\G.{2})"), ":");
  }
}
