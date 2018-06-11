package com.anur.cmd.program;

import com.anur.cmd.keyword.*;
public class StartISCommand extends ProgramKeywordCommand {
		
	public StartISCommand(String[] param){
		super();
		super.cmdName = CommandConstant.START_SERVER_CMD;
		super.param = param;
	}
	
	@Override
	public boolean execute() {
		//fetch path by given param
		String serverRoot = configFile.echoEntry(param[0]);
		if(serverRoot == null) {
			output.printOutput(param[0]+" is not registered.");
			return false;
		}
		
		String batchFilePath1 = serverRoot + ISRelativePathConstant.ISProfileBinPath1;
		String batchFilePath2 = serverRoot + ISRelativePathConstant.ISProfileBinPath1;
		
		if(programHandler.validatePath(batchFilePath1)) {
			return programHandler.run(batchFilePath1+"startup.bat");
		}else if(programHandler.validatePath(batchFilePath2)) {
			return programHandler.run(batchFilePath2+"startup.bat");
		}else {
			output.printOutput("Check the IS Root Path");
			return false;
		}
		
	}

	@Override
	public boolean validate() {
		if(param.length == 1)
			return true;
		output.printOutput("Invalid Parameters");
		return false;
	}

}
