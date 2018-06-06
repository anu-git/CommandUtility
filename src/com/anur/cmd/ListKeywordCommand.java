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
		// TODO Auto-generated method stub
		return false;
	}

}
