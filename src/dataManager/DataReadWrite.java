package dataManager;

import processing.core.PApplet;
import processing.serial.Serial;

public class DataReadWrite extends PApplet {
	
	private Serial serialPort;
	
	public DataReadWrite() {}
	
	public void openComm(String portName, int baudRate) {
		try {
			serialPort = new Serial(this, portName, baudRate);
			delay(1000);
			System.out.println("Port " + portName + " is opened, waiting for commands...");
		} catch (RuntimeException e) {
			System.out.println("ERROR: Cannot open port");
			e.printStackTrace();
			System.exit(0);
		} 
	}
	
	public void sendData(String portName, String data) {
		System.out.print("\r\nSending " + data + " to port " + portName + "...  ");
		serialPort.write(data);
		System.out.println("Sent!");
	}

	private void readData() {
		while (serialPort.available() > 0) {
			char in = serialPort.readChar();
			System.out.print(in);
		}
	}

	public void delayMs(int ms) {
		System.out.println("Waiting " + ms + " milliseconds...");
		delay(ms);
	}
	
	public void serialEvent(Serial p) {
		readData();
	}
}
