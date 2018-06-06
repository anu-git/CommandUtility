package com.anur.config;

import java.io.IOException;

public class WindowsProgramHandler extends ProgramHandler {

	@Override
	public boolean runProgram(String path, String param) {
		Runtime runTime = Runtime.getRuntime();
		try {
			runTime.exec(path+ ((param!=null)?(" "+param):""));
		} catch (IOException e) {
			output.printOutput("Can't run the program. Reason:"+e.getMessage());
			return false;
		}
		return true;
	}

}
