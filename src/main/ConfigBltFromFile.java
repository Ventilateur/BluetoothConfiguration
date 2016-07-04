package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Iterator;

import processing.core.*;
import processing.serial.*;

import fileManager.*;
import dataManager.*;

public class ConfigBltFromFile extends PApplet {
	
	private static DataSender dataSender = new DataSender();
	private static String portName;
	private static int baudRate = 9600;
	private static String fileName;
	private static List<String> cmdList = new ArrayList<String>();
	private static Scanner keyboard = new Scanner(System.in);
	private static boolean useTextFile = true;
	
	public void serialEvent(Serial p) {
		System.out.println(p.readString());
	} 

	public static void printPortsInfo() {
		// Print out all available port
		System.out.println("Here come all available ports:");
		for (int i = 0; i < Serial.list().length; i++) {
			System.out.println(Serial.list()[i]);
		}
	}
	
	public static void chooseFile() {
		// Enter command file name
		System.out.println("Enter file name you want to load:");
		keyboard = new Scanner(System.in);
		fileName = keyboard.nextLine();
		cmdList = ReadCmdFromFile.readFile(fileName);
	}
	
	public static void choosePort() {
		// Enter port name
		System.out.println("Enter your port here:");
		keyboard = new Scanner(System.in);
		portName = keyboard.nextLine();
		while (!Arrays.asList(Serial.list()).contains(portName)) {
			System.out.println("Please re-enter a valid port name:");
			portName = keyboard.nextLine();
		}
	}
	
	public static void chooseMode() {
		// Enter Y for using text file, N for manual mode
		System.out.println("Would you like to use a config text file? (Y/N)");
		keyboard = new Scanner(System.in);
		String in = keyboard.nextLine();
		while (!(in.equals("Y") || in.equals("N"))) {
			System.out.println("Would you like to use a config text file? (Y/N)");
			in = keyboard.nextLine();
		}
		if (in.equals("Y")) useTextFile = true;
		else useTextFile = false;
	}
	
	public static void main(String[] args) {
		
		printPortsInfo();
		chooseMode();
		if (useTextFile) chooseFile();
		choosePort();

		dataSender.openComm(portName, baudRate);
		
		if (useTextFile) {	
			// Using text config file
			System.out.println("Processing command list...");
			Iterator<String> iter = cmdList.iterator();
			ManipCmdLine.ReturnType ret = new ManipCmdLine.ReturnType(); 
			while (iter.hasNext()) {
				ret = ManipCmdLine.checkStringLine(iter.next());
				switch (ret.getMode()) {
				case COMMAND:
					dataSender.sendData(portName, ret.getData());
					break;
				case DELAY:
					dataSender.delayMs(Integer.parseInt(ret.getData()));
					break;
				case COMMENT:
					break;
				default:
					break; 
				}
			}
		} else {			
			// Manual mode, type "quit" to exit
			System.out.println("Enter an AT command:");
			keyboard = new Scanner(System.in);
			String cmd = ""; 
			while (!cmd.equals("quit")) {
				cmd = keyboard.nextLine();
				dataSender.sendData(portName, cmd);
			}
		}
		
		// Finish
		System.out.println("All commands have been sent");
		keyboard.close();
	}

}
