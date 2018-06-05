package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class UpdateKeywordCommand extends Command{
	UpdateKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.UPDATE_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
