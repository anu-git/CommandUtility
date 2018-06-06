package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class ListKeywordCommand extends KeywordCommand{
	public ListKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.LIST_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	public boolean execute() {
		String[] entries = configFile.listEntries();
		for(String entry : entries){
			output.printOutput(entry);
		}
		return true;
	}
	@Override
	public boolean validate() {
		if(param.length == 0)
			return true;
		output.printOutput("No Parameters are required");
		return false;
	}

}
