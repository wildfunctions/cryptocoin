package bitcoin;

import lib.Crypto;
import lib.Utils;

public class BlockHash {

	public static void main(String[] args) {
		BlockHash bh = new BlockHash();
		Block block = BlockLoader.load("block_example_1.json");
		
		bh.getBlockHash(block);
	}
	
	public void getBlockHash(Block block) {
		
		String version = Utils.decimalToHex(Integer.parseInt(block.ver));
		version = Utils.endianFlip(version);

		String previousBlock = Utils.endianFlip(block.prev_block);
		
		String mrklRoot = Utils.endianFlip(block.mrkl_root);
		
		String time = Utils.decimalToHex(Integer.parseInt(block.time));
		time = Utils.endianFlip(time);

		String bits = Utils.decimalToHex(Integer.parseInt(block.bits));
		bits = Utils.endianFlip(bits);

		//Nonce too long for int, so parse as Long first?
		String nonce = Utils.decimalToHex((int) Long.parseLong(block.nonce));
		nonce = Utils.endianFlip(nonce);
		
		String blockHash = version +
				previousBlock +
			    mrklRoot +
			    time +
			    bits +
			    nonce;
		
		byte[] hashResult = Crypto.dsha256(Utils.hexToBytes(blockHash));
		String hash = Utils.bytesToHex(Utils.endianFlip(hashResult));

		System.out.println("Block Hash: " + hash);
	}
	
}