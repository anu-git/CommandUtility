package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class RegisterKeywordCommand extends Command{
	RegisterKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.REGISTER_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
