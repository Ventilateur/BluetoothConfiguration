package unitTests;

import dataManager.ManipCmdLine;

public class ManipulateString {
	
	public static void main(String[] args) {
		
		String s1 = "AT+BALH";
		String s2 = "@DELAY    		1000";
		String s3 = "@@Some irrelevant stupid shit";

		//for (String e : s1.split("\\s+")) System.out.println(e);
		ManipCmdLine.ReturnType ret = new ManipCmdLine.ReturnType(); 
		ret = ManipCmdLine.checkStringLine(s3);
		System.out.println(ret.getMode());
		System.out.println(ret.getData());
		//System.out.println(Integer.parseInt(ret.getData()));
	}

}
