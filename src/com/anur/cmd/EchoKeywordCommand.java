package com.anur.cmd;

import com.anur.cmd.CommandConstant;
public class EchoKeywordCommand extends KeywordCommand{
	
	public EchoKeywordCommand(String[] param){
		super();
		super.cmdName = CommandConstant.ECHO_KEYWORD_CMD;
		super.param = param;
	}
	@Override
	public boolean execute() {
		String value = configFile.echoEntry(param[0]);
		return (value != null && !value.equalsIgnoreCase(""))?true:false;
	}

}
