package lib;

public class Utils {

	public static String decimalToHex(int dec) {
		final int sizeOfIntInHalfBytes = 8;
		final int numberOfBitsInAHalfByte = 4;
		final int halfByte = 0x0F;
		final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };

		StringBuilder hexBuilder = new StringBuilder(sizeOfIntInHalfBytes);
		hexBuilder.setLength(sizeOfIntInHalfBytes);
		for (int i = sizeOfIntInHalfBytes - 1; i >= 0; --i) {
			int j = dec & halfByte;
			hexBuilder.setCharAt(i, hexDigits[j]);
			dec >>= numberOfBitsInAHalfByte;
		}
		return hexBuilder.toString();
	}
	
	public static String bytesToHex(byte [] raw) {
		final String HEXES = "0123456789abcdef";
	    if ( raw == null ) {
	      return null;
	    }
	    final StringBuilder hex = new StringBuilder(2 * raw.length);
	    for ( final byte b : raw ) {
	      hex.append(HEXES.charAt((b & 0xF0) >> 4))
	         .append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	}
	
	public static byte[] hexToBytes(String hex) {
		return hexToBytes(hex.toCharArray());
	}

	public static byte[] hexToBytes(char[] hex) {
		int length = hex.length / 2;
		byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
		    int high = Character.digit(hex[i * 2], 16);
		    int low = Character.digit(hex[i * 2 + 1], 16);
		    int value = (high << 4) | low;
		    if (value > 127)
		    value -= 256;
		    raw[i] = (byte) value;
		}
		return raw;
	}

	public static String endianFlip(String s) {
		return Utils.bytesToHex((Utils.endianFlip(Utils.hexToBytes(s))));
	}
	
	public static byte[] endianFlip(byte[] arr) {
		byte[] reversedArray = new byte[arr.length];
		int j = 0;
		for (int i = arr.length -1; i >= 0; i--){
		    reversedArray[j++] = arr[i];
		}
		return reversedArray;
	}
	
	public static byte[] concat(byte[] a, byte[] b) {
		   int aLen = a.length;
		   int bLen = b.length;
		   byte[] c = new byte[aLen+bLen];
		   System.arraycopy(a, 0, c, 0, aLen);
		   System.arraycopy(b, 0, c, aLen, bLen);
		   return c;
	}
	
}
