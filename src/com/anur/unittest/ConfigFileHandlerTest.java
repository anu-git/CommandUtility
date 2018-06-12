package com.anur.unittest;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.anur.config.ConfigFileHandler;
import com.anur.config.TextConfigFileHandler;
import com.anur.output.SystemOutput;

public class ConfigFileHandlerTest {
	
	String sampleFile = "C:\\Users\\anurag\\Desktop\\testing.properties";
		
	ConfigFileHandler handler  = null;
	
	//1st testing while calling constructor itself
	@Before
	public void init() throws IOException{
		ConfigFileHandler.setOutput(new SystemOutput());
		File f = new File(sampleFile);
		f.createNewFile();
		handler = 	new TextConfigFileHandler(sampleFile);
	}
	
	@Test
	public void test() throws IOException{
		assertTrue("key1 is added", handler.addEntry("key1", "val1"));
		assertEquals("key1 is verfied", handler.echoEntry("key1"), "val1");
		
		assertTrue("key1 is added", handler.updateEntry("key1", "val2"));
		assertEquals("key1 is verfied again", handler.echoEntry("key1"), "val2");
		
		assertTrue("key1 is deleted", handler.deleteEntry("key1"));
		assertEquals("key1 is not present after deletion", handler.echoEntry("key1"), null);
	}
	
	@After
	public void destroy(){
		File f = new File(sampleFile);
		f.delete();
	}
}
