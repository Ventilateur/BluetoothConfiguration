package fileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ReadCmdFromFile {

	public static void readFileAndPrint(String fileName) {
		String line;
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, Charset.defaultCharset());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close(); 
		} catch (IOException e) {
			System.out.println("Error: may be file does not exist?");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static List<String> readFile(String fileName) {
		String line;
		List<String> ret = new ArrayList<String>();
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, Charset.defaultCharset());
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				ret.add(line);
			}
			br.close(); 
		} catch (IOException e) {
			System.out.println("Error: may be file does not exist?");
			e.printStackTrace();
			System.exit(0);
		}
		return ret;
	}
}
