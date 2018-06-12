package com.anur.unittest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.anur.cmd.keyword.KeywordCommand;
import com.anur.cmd.keyword.RegisterKeywordCommand;
import com.anur.cmd.program.CustomProgramCommand;
import com.anur.cmd.program.ProgramKeywordCommand;
import com.anur.config.ConfigFileHandler;
import com.anur.config.ProgramHandler;
import com.anur.config.TextConfigFileHandler;
import com.anur.config.WindowsProgramHandler;
import com.anur.output.SystemOutput;

public class CustomCommandTest {
	ProgramKeywordCommand cmd;
	KeywordCommand kcmd;
	String sampleFile = "C:\\Users\\anurag\\Desktop\\testing1.properties";
	
	@Before
	public void init() throws IOException{
		File f = new File(sampleFile);
		f.createNewFile();

		ConfigFileHandler handler = new TextConfigFileHandler(sampleFile);
		KeywordCommand.setConfigFileHandler(handler);
		
		ProgramHandler pHandler = new WindowsProgramHandler();
		ProgramKeywordCommand.setProgramHandler(pHandler);
		
		ProgramHandler.setOutput(new SystemOutput());
		KeywordCommand.setOutput(new SystemOutput());	
	}
	
	@Test
	public void test() {
		String[] param = {"notepad", "C:\\Windows\\system32\\notepad.exe"};
		kcmd = new RegisterKeywordCommand(param);
		assertTrue(kcmd.execute());
		
		String[] param1 = {"user", "C:\\Users\\Public"};
		kcmd = new RegisterKeywordCommand(param1);
		assertTrue(kcmd.execute());
		
		cmd = new CustomProgramCommand("notepad", null);
		assertTrue(cmd.execute());
		
		String[] param2 = {"roa"};
		cmd = new CustomProgramCommand("user", param2);
		assertTrue(cmd.execute());
	}
	
	@After
	public void destroy(){
		File f = new File(sampleFile);
		f.delete();
	}
}
