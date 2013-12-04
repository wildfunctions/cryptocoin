package bitcoin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BlockLoader {

	private static String WORKSPACE = "C:/Users/Stacey/workspace/";
	
	private static String DOC_DIR = "cryptocoins/src/main/doc/";
	
	//to get our block info
	public static String load(String fileName) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(WORKSPACE + DOC_DIR + fileName));
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
