package com.anur.cmd;

import com.anur.cmd.CommandConstant;;
public class EchoKeywordCommand extends Command{
	EchoKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.ECHO_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

}
