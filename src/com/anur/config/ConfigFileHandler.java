package com.anur.config;

import java.util.Map;
import java.util.Properties;

import com.anur.output.Output;

public abstract class ConfigFileHandler {
	protected Output output;
	protected String configFilePath;
	protected Properties props = new Properties();
		
	abstract boolean loadEntries();
	
	abstract boolean storeEntries(Properties props);
	
	public void setOutput(Output o){
		output = o;
	}
	
	public String[] listEntries(){
		return props.keySet().toArray(new String[0]);
	}
	
	public boolean addEntry(String key, String value){
		if(echoEntry(key) != null){
			output.printOutput(key+" already exists.");
			return false;
		}
		props.put(key, value);
		boolean isStored = storeEntries(props);
		if(isStored){
			return true;
		}else{
			props.remove(key);
			return false;
		}

	}
	
	public boolean updateEntry(String key, String newValue){		
		if(echoEntry(key) == null){
			output.printOutput(key+" doesn't exist.");
			return false;
		}
		String oldValue = (String) props.get(key);
		props.put(key, newValue);
		boolean isStored = storeEntries(props);
		if(isStored){
			return true;
		}else{
			props.put(key, oldValue);
			return false;
		}
	}
	
	public boolean deleteEntry(String key){
		if(echoEntry(key) == null){
			output.printOutput(key+" doesn't exist.");
			return false;
		}
		String value = (String) props.get(key);
		props.remove(key);
		boolean isStored = storeEntries(props);
		if(isStored){
			return true;
		}else{
			props.put(key, value);
			return false;
		}
	}
	
	public String echoEntry(String key) {
		return (String) props.get(key);
	}
}
