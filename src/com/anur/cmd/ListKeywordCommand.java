package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class ListKeywordCommand extends Command{
	ListKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.LIST_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
