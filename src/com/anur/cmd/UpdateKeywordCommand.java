package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
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

}
