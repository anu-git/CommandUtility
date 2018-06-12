package com.anur.config;

import java.io.File;

import com.anur.output.Output;

public abstract class ProgramHandler {
	protected static Output output;
	
	public static void setOutput(Output o){
		output = o;
	}
	public boolean validatePath(String path) {
		File f = new File(path);
    	if(!f.exists())
    		return false;
		
    	return true;
	}
	
	protected abstract boolean runProgram(String path, String param);
	
	public boolean run(String path) {
		if(validatePath(path) && runProgram(path, null))
			return true;
		return false;
	}
	
	public boolean run(String path, String param) {
		if(validatePath(path) && runProgram(path, param))
			return true;
		return false;
	}
}
