package com.anur.client;

import com.anur.config.*;
import com.anur.output.ConsoleOutput;

import java.io.Console;

import com.anur.cmd.*;
public class ClientCode {
	
	public static void main(String[] args){
		ConfigFileHandler handler = new TextConfigFileHandler("C:\\Users\\anur\\Desktop\\configuration.properties");
		KeywordCommand.setConfigFileHandler(handler);
		
		Console con = System.console();
		ConsoleOutput co = new ConsoleOutput(con);
		KeywordCommand.setOutput(co);
		
		String[] param = {"is911", "C:\\SoftwareAG\\"};
		KeywordCommand cmd = new RegisterKeywordCommand(param);
		System.out.println(cmd.execute());
		
		String[] param2 = {"is910"};
		KeywordCommand cmd2 = new EchoKeywordCommand(param2);
		System.out.println(cmd2.execute());
		
	}
	
	
}
