package com.anur.cmd.program;

import com.anur.cmd.keyword.KeywordCommand;
import com.anur.config.ProgramHandler;

public abstract class ProgramKeywordCommand extends KeywordCommand{
	protected static ProgramHandler programHandler;
	
	public static void setProgramHandler(ProgramHandler program){
		programHandler = program;
	}

}
