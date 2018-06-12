package com.anur.cmd.program;

import java.io.File;
import java.util.*;
import com.anur.cmd.keyword.CommandConstant;

public class CustomProgramCommand extends ProgramKeywordCommand {
	
	public CustomProgramCommand(String cmdName, String[] param){
		super();
		super.cmdName = cmdName;
		super.param = param;
	}
	
	@Override
	public boolean validate() {
		String pathOrFile = configFile.echoEntry(cmdName);
		File f = new File(pathOrFile);
		if(!f.isDirectory() && param != null){
			return false;
		}
		
		if(f.isDirectory() && param != null && param.length > 1){
			return false;
		}
		return true;
	}

	@Override
	public boolean execute() {
		String pathOrFile = configFile.echoEntry(cmdName);
		File f = new File(pathOrFile);
		if(!f.isDirectory()){
			return programHandler.run(pathOrFile);
		}
		
		if(f.isDirectory() && param != null && param.length == 0){
			return programHandler.run("explorer "+pathOrFile);
		}
		
		if(f.isDirectory() && param != null && param.length == 1){
			String[] list = f.list();
			for(String dir: list){
				if(dir.toLowerCase().indexOf(param[0].toLowerCase()) != -1){
					if(new File(pathOrFile+"\\"+dir).isDirectory())
						return programHandler.run("explorer "+pathOrFile+"\\"+dir);
				}
			}
		}	
		
		return false;
		
	}

}
