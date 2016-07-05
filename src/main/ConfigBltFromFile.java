package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Iterator;

import processing.core.PApplet;
import processing.serial.Serial;

import fileManager.*;
import dataManager.*;

public class ConfigBltFromFile extends PApplet {
	
	private static boolean useArg = true;
	private static DataReadWrite dataRxTx = new DataReadWrite();
	private static String portName = null;
	private static int baudRate = 9600;
	private static String fileName;
	private static List<String> cmdList = new ArrayList<String>();
	private static Scanner keyboard = new Scanner(System.in);
	private static boolean useTextFile = true;

	
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
	
	private static class SendCommands extends Thread {	
		public void run() {
			if (useTextFile) {	
				// Using text config file
				System.out.println("Processing command list...");
				Iterator<String> iter = cmdList.iterator();
				ManipCmdLine.DataType ret = new ManipCmdLine.DataType(); 
				while (iter.hasNext()) {
					ret = ManipCmdLine.checkStringLine(iter.next());
					switch (ret.getMode()) {
					case COMMAND:
						dataRxTx.sendData(portName, ret.getData());
						break;
					case DELAY:
						dataRxTx.delayMs(Integer.parseInt(ret.getData()));
						break;
					case COMMENT:
						break;
					default:
						break; 
					}
				}
			} else {			
				// Manual mode, type "quit" to exit
				System.out.println("\r\nEnter an AT commands or type \"quit\" to exit:");
				keyboard = new Scanner(System.in);
				String cmd = ""; 
				while (!cmd.equals("quit")) {
					cmd = keyboard.nextLine();
					dataRxTx.sendData(portName, cmd);
				}
			}
			// Finish
			System.out.println("\r\nAll commands have been sent, exiting program!");
			keyboard.close();
			System.exit(0);
		}

		public void start() {
			Thread t = new Thread(this, "Sender");
			t.start();
		}
	}
	
	
	public static void main(String[] args) {
		
		printPortsInfo();
		if (useArg == false) {
			chooseMode();
			if (useTextFile) chooseFile();
		} else {
			switch (args.length) {
			case (0):
				useTextFile = false;
				break;
			case (1):
				useTextFile = true;
				cmdList = ReadCmdFromFile.readFile(args[0]);
				break;
			case (2):
				useTextFile = true;
				cmdList = ReadCmdFromFile.readFile(args[0]);
				portName = args[1];
				break;
			default:
				System.out.println("Too many arguments! Maximum 3 required!");
				System.exit(0);
			}
		}
		if (portName == null) choosePort();

		dataRxTx.openComm(portName, baudRate);

		SendCommands sender = new SendCommands();
		sender.start();
	}

}
