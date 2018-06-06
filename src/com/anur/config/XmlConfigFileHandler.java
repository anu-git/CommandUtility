package com.anur.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class XmlConfigFileHandler extends ConfigFileHandler {

	@Override
	boolean loadEntries() {
		try (FileInputStream fis = new FileInputStream(new File(configFilePath))){
			props.loadFromXML(fis);			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	boolean storeEntries(Properties props) {
		try (FileOutputStream fos = new FileOutputStream(new File(configFilePath))){
			
			props.storeToXML(fos, "Entry Added/Updated/Deleted");
			
		} catch (IOException e) {
			e.printStackTrace();
			//log message to logger or console
			return false;				
		}
	return true;
	}

}
