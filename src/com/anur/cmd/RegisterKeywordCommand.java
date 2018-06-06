package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class RegisterKeywordCommand extends KeywordCommand{
	public RegisterKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.REGISTER_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	public boolean execute() {		
		return configFile.addEntry(param[0], param[1]);
	}

}
