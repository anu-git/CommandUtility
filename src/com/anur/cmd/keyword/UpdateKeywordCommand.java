package com.anur.cmd.keyword;

import com.anur.cmd.keyword.CommandConstant;;
public class UpdateKeywordCommand extends KeywordCommand{
	public UpdateKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.UPDATE_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	public boolean execute() {
		boolean isUpdated = configFile.updateEntry(param[0], param[1]);
		if(isUpdated){
			output.printOutput("Key is updated successfully");
			return true;
		}else{
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
	@Override
	public void usage() {
		output.printOutput("* update <old keyword> <new keyword>");
	}

}
