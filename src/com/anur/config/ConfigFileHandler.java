package com.anur.config;

import java.util.Map;

public abstract class ConfigFileHandler {
	protected String configFilePath;
	protected Map<String, String> keywordMap;
		
	abstract Map<String, String> loadEntries();
	
	abstract boolean listEntries();
	
	abstract boolean addEntry(String key, String value);
	
	abstract boolean updateEntry(String key, String newValue);
	
	abstract boolean deleteEntry(String key);
	
	String echoEntry(String key) {
		return keywordMap.get(key);
	}
}
