package unitTests;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import fileManager.ReadCmdFromFile;


public class ReadCmdFromFileTest {

	@Test
	public void test() {
		ReadCmdFromFile.readFileAndPrint("cmd.txt");
		System.out.println("End test no.1");
	}
	
	@Test
	public void test0() {
		List<String> cmd = new ArrayList<String>();
		cmd = ReadCmdFromFile.readFile("cmd.txt");
		for (int i = 0; i < cmd.size(); i++) {
			System.out.println(cmd.get(i));
		}
		System.out.println("End test no.2");
	}
}
