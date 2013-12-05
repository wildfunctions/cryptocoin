package bitcoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class BlockLoader {

	private static String DOC_DIR = "src/main/doc/";
	
	public static Block load(String fileName) {
		Block block = new Block();
		
		String json = BlockLoader.loadFile(fileName);
		try {
			JSONObject obj = new JSONObject(json);
			
			block.hash = obj.getString("hash");
			block.ver = obj.getString("ver");
			block.prev_block = obj.getString("prev_block");
			block.mrkl_root = obj.getString("mrkl_root");
			block.time = obj.getString("time");
			block.bits = obj.getString("bits");
			block.nonce = obj.getString("nonce");
			block.n_tx = obj.getString("n_tx");
			block.size = obj.getString("size");
			block.tx = obj.getJSONArray("tx");
			block.mrkl_tree = obj.getJSONArray("mrkl_tree");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return block;
	}
	
	/*
	 * Get our block info from json file
	 */
	public static String loadFile(String fileName) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(DOC_DIR + fileName));
			String currentLine;
			while((currentLine = br.readLine()) != null) {
				content += currentLine;
			}
			br.close();
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
