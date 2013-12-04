package lib;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {

	public static byte[] sha256(byte[] arr) {
	    MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    md.update(arr);
	    return md.digest();
	}
	
	public static byte[] dsha256(byte[] arr) {
		return sha256(sha256(arr));
	}
	
}
