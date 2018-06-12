package com.anur.cmd.keyword;

import com.anur.cmd.keyword.CommandConstant;;
public class RegisterKeywordCommand extends KeywordCommand{
	public RegisterKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.REGISTER_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	public boolean execute() {
		boolean isAdded = configFile.addEntry(param[0], param[1]);
		if(isAdded){
			output.printOutput("Key is registered successfully");
			return true;
		}else{
			return false;
		}
		
	}
	@Override
	public boolean validate() {
		if(param.length == 2)
			return true;
		output.printOutput("Invalid Parameters");
		return false;
	}
	@Override
	public void usage() {
		output.printOutput("* register <IS/Program/Path Keyword> <Folder Path/exe path>");	}

}
