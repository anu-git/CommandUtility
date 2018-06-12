package com.anur.unittest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args) {
		//Result result = JUnitCore.runClasses(ConfigFileHandlerTest.class);
	Result result = JUnitCore.runClasses(ConfigFileHandlerTest.class, ProgramHandlerTest.class, CustomCommandTest.class);
		
	  for (Failure failure : result.getFailures()) {
	     System.out.println(failure.toString());
	  }
		
	  System.out.println(result.wasSuccessful());

	}

}
