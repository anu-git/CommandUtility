package com.anur.output;

import java.io.Console;

public class ConsoleOutput implements Output{
	Console con;
	
	public ConsoleOutput(Console con){
		this.con = con;
	}
	@Override
	public void printOutput(String msg) {
		con.printf(msg+"\n");
		
	}

}
