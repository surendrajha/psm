/**
 * 
 */
package com.epayroll.utils;

import java.security.SecureRandom;

import org.springframework.security.crypto.codec.Base64;

/**
 * @author Surendra Jha
 */
public class RandomNumberUtils {

	private static SecureRandom random = new SecureRandom();
	private static final String ALPHA_NUM = "0123456789876543210ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getUserName() {
		return "VJS" + get6digitUniqueNumber();
	}

	// Generate temporary password
	public static String getTempPassword() {
		return getAlphaNumeric(8);
	}

	// get AlfaNumeric Code
	public static String getAlphaNumeric(int len) {
		StringBuffer sb = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			int ndx = (random.nextInt(ALPHA_NUM.length()));
			sb.append(ALPHA_NUM.charAt(ndx));
		}
		return sb.toString();
	}

	// Generate 6 digit OTP number
	public static Integer get6digitUniqueNumber() {
		return random.nextInt(900000) + 100000;
	}

	public static String encode(String message) {
		return new String(Base64.encode(message.getBytes()));
	}

	public static String decode(String message) {
		return new String(Base64.decode(message.getBytes()));
	}

	public static void main(String[] args) {
		System.out.println(decode("MDAz"));
	}
}
