package com.anur.cmd;

public abstract class Command {
	protected String cmdName;
	protected String[] param;
	
	abstract boolean execute();
}
