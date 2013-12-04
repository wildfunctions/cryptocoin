package bitcoin;
import org.json.JSONArray;

public class Block {

	public String hash;
	public String ver;
	public String prev_block;
	public String mrkl_root;
	public String time;
	public String bits;
	public String nonce;
	public String n_tx;
	public String size;
	
	public JSONArray tx;
	
	public JSONArray mrkl_tree;
	
}
