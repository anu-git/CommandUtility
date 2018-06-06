package com.anur.config;

import java.util.Map;
import java.util.Properties;

public abstract class ConfigFileHandler {
	protected String configFilePath;
	protected Properties props = new Properties();
		
	abstract boolean loadEntries();
	
	abstract boolean storeEntries(Properties props);
	
	public String[] listEntries(){
		return props.keySet().toArray(new String[0]);
	}
	
	public boolean addEntry(String key, String value){
		if(echoEntry(key) != null){
			//log message to logger or console
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
			//log message to logger or console
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
			//log message to logger or console
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
