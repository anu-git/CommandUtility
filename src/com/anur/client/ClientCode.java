package com.anur.client;

import com.anur.cmd.keyword.*;
import com.anur.cmd.program.ProgramKeywordCommand;
import com.anur.cmd.program.StartISCommand;
import com.anur.config.*;
import com.anur.output.ConsoleOutput;

import java.io.Console;
public class ClientCode {
	static Console con = null;
	public static void main(String[] args){
		
		//Obtaining a reference to the console.
        con = System.console();
                 // Checking If there is no console available, then exit.
        if(con == null) 
        {
            System.out.print("No console available");
            return;
        }
        con.printf("\n\n\n\n");
        // List menu of possible actions available        
        con.printf("<*****>_________Welcome to Adapter Utility 2.0_____________<********>\n");
        con.printf("\n\n");
        con.printf("* register <IS/Program/Path Keyword> <Folder Path/exe path>\n");
        con.printf("* list - to list all registered IS/Path/Programs\n");
        con.printf("* update <old keyword> <new keyword>\n");
        con.printf("* echo <keyword> - to get path\n\n");
        
        con.printf("* start <IS Keyword> or start <IS Keyword> -d\n");
        con.printf("* status <IS keyword>\n");
        con.printf("* stop <IS keyword>\n");         
        con.printf("* sum <IS keyword> -s <server name> -r <RepoName> -showAll\n\n");
        
        con.printf("* <Path/Program keyword> - open program/path\n\n");
        
        con.printf("* <IS keyword> - open install path\n");
        con.printf("* <IS keyword> packages\n");
        con.printf("* <IS keyword> jdbc\n");
        con.printf("* <IS keyword> sap\n");
        con.printf("* <IS keyword> <package name>\n");
        
        con.printf("* start ic\n");
        con.printf("* status ic\n");
        
        con.printf("* exit\n");
        con.printf("\n\n");

        ConsoleOutput co = new ConsoleOutput(con);		
        ConfigFileHandler.setOutput(co);
        ProgramHandler.setOutput(co);
        KeywordCommand.setOutput(co);
        
		ConfigFileHandler handler = new TextConfigFileHandler("C:\\Users\\anurag\\Desktop\\configuration.properties");
		KeywordCommand.setConfigFileHandler(handler);
				
		String[] param = {"is911", "C:\\SoftwareAG\\"};
		KeywordCommand cmd = new RegisterKeywordCommand(param);
		System.out.println(cmd.execute());
		
		String[] param2 = {"is911"};
		KeywordCommand cmd2 = new EchoKeywordCommand(param2);
		System.out.println(cmd2.execute());
		
		ProgramHandler pHandler = new WindowsProgramHandler();
		ProgramKeywordCommand.setProgramHandler(pHandler);
		
		String[] param3 = {"is910"};
		ProgramKeywordCommand cmd3 = new StartISCommand(param3);
		//cmd3.execute();
		
		String param4 = "C:\\Users\\anurag\\Desktop\\dev\\eclipse\\eclipse.exe";
		pHandler.runProgram(param4, null);
		
	}
	
	
}
