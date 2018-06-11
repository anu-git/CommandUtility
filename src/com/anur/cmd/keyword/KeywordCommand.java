package com.anur.cmd.keyword;

import com.anur.config.ConfigFileHandler;
import com.anur.output.Output;

public abstract class KeywordCommand {
	protected static ConfigFileHandler configFile;
	protected static Output output;
	protected String cmdName;
	protected String[] param;
	
	public static void setConfigFileHandler(ConfigFileHandler config){
		configFile = config;
	}
	
	public static void setOutput(Output o){
		output = o;
	}
	
	public abstract boolean validate();
	public abstract boolean execute();
	
	public boolean validateAndExecute() {
		if(validate() && execute()) 
			return true;
		
		return false;
			
	}
}
