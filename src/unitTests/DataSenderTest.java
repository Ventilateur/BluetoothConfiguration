package unitTests;

import dataManager.*;
import org.junit.Test;

public class DataSenderTest {

	@Test
	public void test() {
		DataSender mySerial = new DataSender();
		mySerial.openComm("COM4", 9600);
	}

}
