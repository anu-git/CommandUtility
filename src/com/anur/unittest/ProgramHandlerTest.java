package com.anur.unittest;

import com.anur.config.ProgramHandler;
import com.anur.config.WindowsProgramHandler;
import com.anur.output.SystemOutput;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramHandlerTest {
	ProgramHandler handler = null;
	Process p = null;
	@Before
	public void init() throws IOException{
		ProgramHandler.setOutput(new SystemOutput());
		handler = new WindowsProgramHandler();
	}
	
	@Test
	public void test(){
		handler.run("C:\\Windows\\system32\\notepad.exe");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//check if program is running
		assertTrue("Is program running?", isRunningAlready("C:\\Windows\\system32\\notepad.exe"));
	}
	
	@After
	public void destroy(){
		//kill the program
		
	}
	
	boolean isRunningAlready(String program){
		if(!program.contains(".exe"))
			return false;
		int idx = program.lastIndexOf('\\');
		String exeName = program.substring(idx+1);
		boolean running=false;
		
		Runtime runTime = Runtime.getRuntime();
		try {
			Process p = runTime.exec("tasklist.exe");
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
			    if(line.contains(exeName)){
			    	running=true;
			    	break;
			    }
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return running;
	}
}
