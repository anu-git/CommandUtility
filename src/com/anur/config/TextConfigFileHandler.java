package com.anur.config;

import java.io.*;
import java.util.*;

public class TextConfigFileHandler extends ConfigFileHandler {
	
	public TextConfigFileHandler(String filePath){
		configFilePath = filePath;
		keywordMap = loadEntries();
	}
	
	@Override
	Map<String, String> loadEntries() {
		Map<String, String> map = new HashMap<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(configFilePath));
			String line="";
			while((line=br.readLine()) != null) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	boolean listEntries() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean addEntry(String key, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean updateEntry(String key, String newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean deleteEntry(String key) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
