package com.anur.config;

import java.io.*;
import java.util.*;

public class TextConfigFileHandler extends ConfigFileHandler {

	public TextConfigFileHandler(String filePath){
		configFilePath = filePath;
		loadEntries();
	}
	
	@Override
	boolean loadEntries() {
		try (FileInputStream fis = new FileInputStream(new File(configFilePath))){
			props.load(fis);			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
		
	@Override
	boolean storeEntries(Properties props){
		
		try (FileOutputStream fos = new FileOutputStream(new File(configFilePath))){
				
				props.store(fos, "Entry Added/Updated/Deleted");
				
			} catch (IOException e) {
				e.printStackTrace();
				//log message to logger or console
				return false;				
			}
		return true;
	}
	
}
