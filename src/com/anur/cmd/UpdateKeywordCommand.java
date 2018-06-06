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
		// TODO Auto-generated method stub
		return false;
	}

}
