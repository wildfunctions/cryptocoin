package bitcoin;

import lib.Crypto;
import lib.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class MerkleTree {

	public static void main(String[] args) {
		MerkleTree mc = new MerkleTree();
		Block block = BlockLoader.load("block_example_1.json");
		
		mc.getMerkleRoot(block);
	}
	
	public void getMerkleRoot(Block block) {
		String[] tx = new String[block.tx.length()];
		for(int i = 0; i < block.tx.length(); i++) {
			try {
				tx[i] = ((JSONObject) block.tx.get(i)).getString("hash");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Merkle Root: " + getMrklRoot(tx));
	}
	
	/*
	 * Recursive method to get Merkle Root from String[]
	 */
	public static String getMrklRoot(String[] arr) {
		if(arr.length == 2) {
			return getMrklParent(arr[0], arr[1]);
		} else {
		    int txCount = 0;
		    if(arr.length % 2 == 0) {
		    	txCount = arr.length / 2;
		    } else {
		    	txCount = (arr.length + 1) / 2;
		    }
		    String[] parents = new String[txCount];
			for(int i = 0; i < txCount; i++) {
					if(i < txCount - 1 || (arr.length % 2 == 0)) {
						parents[i] = getMrklParent(arr[2 * i], arr[2 * i + 1]);
					} else {
						parents[i] = getMrklParent(arr[2 * i], arr[2 * i]);
					}
			}
			return getMrklRoot(parents);
		}
	}
	
	/*
	 * Calculates the parent of two children nodes in Merkle Tree
	 */
	public static String getMrklParent(String childA, String childB) {
		// convert string to array of byte values
		byte[] aBytes = Utils.hexToBytes(childA);
		// flip bytes to reverse endianness
		aBytes = Utils.endianFlip(aBytes);
		
		byte[] bBytes = Utils.hexToBytes(childB);
		bBytes = Utils.endianFlip(bBytes);

		// concat two byte arrays
		byte[] concat = Utils.concat(aBytes, bBytes);

		// double sha256 then flip bytes once more
	    return Utils.bytesToHex(Utils.endianFlip(Crypto.dsha256(concat)));
	}
}
