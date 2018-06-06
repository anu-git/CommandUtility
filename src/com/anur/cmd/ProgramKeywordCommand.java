package com.anur.cmd;

import com.anur.config.ProgramHandler;

public abstract class ProgramKeywordCommand extends KeywordCommand{
	protected static ProgramHandler programHandler;
	
	public static void setProgramHandler(ProgramHandler program){
		programHandler = program;
	}

}
