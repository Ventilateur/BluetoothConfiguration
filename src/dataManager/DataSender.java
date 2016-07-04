package dataManager;

import processing.core.*;
import processing.serial.*;

public class DataSender extends PApplet {
	
	private Serial serialPort;
	
	public DataSender() {}
	
	public void openComm(String portName, int baudRate) {
		try {
			serialPort = new Serial(this, portName, baudRate);
			delay(1000);
			serialPort.bufferUntil(10);
			System.out.println("Port " + portName + " is opened, waiting for commands...");
		} catch (RuntimeException e) {
			System.out.println("ERROR: Cannot open port");
			e.printStackTrace();
		} 
	}
	
	public void sendData(String portName, String data) {
		System.out.println("Sending " + data + " to port " + portName + "...");
		serialPort.write(data);
	}
	
	public void delayMs(int ms) {
		System.out.println("Waiting " + ms + " milliseconds...");
		delay(ms);
	}
	
}
