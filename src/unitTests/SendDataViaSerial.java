package unitTests;

import processing.serial.*;
import processing.core.*;
import org.junit.Test;

public class SendDataViaSerial extends PApplet {
	
	@Test
	public void test() {
		for (int i = 0; i < Serial.list().length; i++) {
			System.out.println(Serial.list()[i]);
		}
		Serial serialPort;
		serialPort = new Serial(this, "COM4", 9600);
		delay(2000);
		System.out.println("Sending command...");
		serialPort.write("AT+NAMETounsi");
		while (true);
	}

}
