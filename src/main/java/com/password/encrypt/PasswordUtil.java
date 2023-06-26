package com.password.encrypt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class PasswordUtil {
  public static   String  hashPassword(String password) {
      try {
          MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
          byte[] hash = messageDigest.digest(password.getBytes());
          return Base64.getEncoder().encodeToString(hash);
      } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
          return null;
      }
  }}

//System.out.println(PasswordUtil.hashPassword("surya"));

