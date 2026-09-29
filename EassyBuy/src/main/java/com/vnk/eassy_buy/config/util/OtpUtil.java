package com.vnk.eassy_buy.config.util;

import java.security.SecureRandom;

public class OtpUtil {
	private static final SecureRandom RANDOM = new SecureRandom();
   private OtpUtil() {
	   throw new UnsupportedOperationException("Utility class cannot be instantiated");
   }
   
   public static String genarateOtp() {
	   int otp = RANDOM.nextInt(1000000);
	   return String.format("%06d", otp);
   }
   
}