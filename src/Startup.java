import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Startup {
	static Console con = null;
	public static String configFilepath = "C:/Users/anur/workspaceAdapter/AdapterUtility2.0/resource/RegisteredPaths.properties";
	static Properties prop = new Properties();
	static String ISProfileBinPath1 = "\\profiles\\IS_default\\bin\\";
	static String ISProfileBinPath2 = "\\profiles\\IS\\bin\\";
	static String ISUpdateManagerPath = "\\UpdateManager\\bin\\UpdateManagerGUI.bat";
	static String ISInstancePath1 = "\\IntegrationServer\\instances\\default\\";
	static String ISInstancePath2 = "\\IntegrationServer\\";
	static List<String> pathList;
	static List<String> programList;
	static String[] serverStatesIC = {"Integration Server - starting", "CTP Server - waiting for IS to be up"};
	
	public static void main(String[] args){
        
        //Obtaining a reference to the console.
        con = System.console();
                 // Checking If there is no console available, then exit.
        if(con == null) 
        {
            System.out.print("No console available");
            return;
        }
        con.printf("\n\n\n\n");
        // List menu of possible actions available        
        con.printf("<*****>_________Welcome to Adapter Utility 2.0_____________<********>\n");
        con.printf("\n\n");
        con.printf("* register <IS/Program/Path Keyword> <Folder Path/exe path>\n");
        con.printf("* list - to list all registered IS/Path/Programs\n");
        con.printf("* update <old keyword> <new keyword>\n");
        con.printf("* echo <keyword> - to get path\n\n");
        
        con.printf("* start <IS Keyword> or start <IS Keyword> -d\n");
        con.printf("* status <IS keyword>\n");
        con.printf("* stop <IS keyword>\n");         
        con.printf("* sum <IS keyword> -s <server name> -r <RepoName> -showAll\n\n");
        
        con.printf("* <Path/Program keyword> - open program/path\n\n");
        
        con.printf("* <IS keyword> - open install path\n");
        con.printf("* <IS keyword> packages\n");
        con.printf("* <IS keyword> jdbc\n");
        con.printf("* <IS keyword> sap\n");
        con.printf("* <IS keyword> <package name>\n");
        
        con.printf("* start ic\n");
        con.printf("* status ic\n");
        
        con.printf("* exit\n");
        con.printf("\n\n");

        String input=null;
        
        //to load all IS keywords
    	initialize();
    	
        while(true){
        	input = con.readLine("Adapter Utility 2.0>");
        	input = input.trim();
        	if(input.equalsIgnoreCase("start ic")){
        		
        		startIC();
        		continue;
        	}else if(input.equalsIgnoreCase("status ic")){
        		con.printf(serverStatesIC[0]+"\n"+serverStatesIC[1]+"\n");
        		continue;
        	}
            try{
            	if(!input.equalsIgnoreCase("")){
            		//splitting the input in command and parameter string
            		int idx = input.indexOf(' ');
            		String command = "";
            		String paramStr = "";
            		//if no parameter is given
            		if(idx == -1){
            			command = input.trim();
            		}else{
            			command = input.substring(0, idx);
            			paramStr = input.substring(idx+1).trim(); 
            		}        		                	
                	
                	if(command.equalsIgnoreCase("exit"))
                		break;       	

                	handleCommand(command, paramStr);
            	}     	 
            		
            }catch(NumberFormatException ex){
            	con.printf("Bad Input. Try Again\n");
            }
            
            //For easy readability
            con.printf("\n");
        }
   

	}
	
	/*public static void main(String[] args){
		initialize();
		launchPathOrProgram("IS912", "jdbc");
	}*/
	
	static void initialize(){
		FileInputStream fis = null;
    	try{
    		File configFile = new File(configFilepath);
    		fis = new FileInputStream(configFile);

        	prop.load(fis);         	
    	}catch(IOException e){
    		e.printStackTrace();
    		con.printf("Something is wrong with config file.\n");
    	}finally {
    		if(fis != null){
    			try {    				
					fis.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
    		}
		}
		
	}
	
	static void handleCommand(String command, String paramStr){
		switch(command){
        case "register":
        	register(paramStr);
        	break;
        case "list":
        	listRegisteredKeywords();
        	break;
        case "update":
        	updateKeyword(paramStr);
        	break;
        case "echo":
        	echoKeyword(paramStr);
        	break;
        case "start":
        	startIS(paramStr);
        	break;
        case "status":
        	statusIS(paramStr);
        	break;
        case "stop":
        	stopIS(paramStr);
        	break;        
        case "sum":
        	launchSum(paramStr);
        	break;
        default:  
        	//check for prefix possibility
        	List<String> matches = findMatchingKeyword(command);
        	if(matches.isEmpty()){
        		con.printf("Keyword-"+command+" is not registered.\n");
        	}else if(matches.size() == 1){
        		//command not recognized, command must be an IS/Program Keyword
            	launchPathOrProgram(matches.get(0), paramStr);
            	break;
        	}
        	else if(matches.size()>1){
        		//print possible registered keywords
        		for(String keyword: matches){
        			con.printf(keyword+"\n");
        		}
        	}
        }
	}
	
	static void register(String paramStr){
		//check if Path is enclosed with double quotes
		String Path="";
		String Keyword="";
		int idx = paramStr.indexOf('"');
		String[] param = paramStr.split(" ");
		if(idx != -1){
			//System.out.println("Hi");
			int endIdx = paramStr.lastIndexOf('"');
			Path = paramStr.substring(idx+1, endIdx);
			Keyword = param[0].trim();
		}else{
			if(param.length == 2){
				Keyword = param[0].trim();
				Path = param[1].trim();  	
			}else{
				con.printf("Bad Input. Try Again\n");
			}
		}		
		
		//check if keyword is not registered already
		if(prop.getProperty(Keyword) != null){
			con.printf("Keyword-"+Keyword+" is already registered.\n");
			return;
		}
		
		//Path verification
		if(!validatePath(Path, false))
			return;
		
    	FileOutputStream fos = null;
    	try{
    		File configFile = new File(configFilepath);
    		fos = new FileOutputStream(configFile);
        	
        	prop.setProperty(Keyword, Path);
        	prop.store(fos, null);      	
        	con.printf("Keyword-"+Keyword+" is registered successfully.\n");
    	}catch(IOException e){
    		con.printf("Bad Input or Check config file. Try Again\n");
    	}finally {
    		if(fos != null){
    			try {    				
					fos.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
    		}
		}
		
		
	}
	
	static void listRegisteredKeywords(){
		pathList = new ArrayList<String>();
		programList = new ArrayList<String>();
		Set keywords = prop.keySet();
		
		String path = "";
		for(Object key: keywords){
			path = prop.getProperty(key.toString());
			if(!path.toString().endsWith(".exe")){
				pathList.add(key.toString());
			}else{
				programList.add(key.toString());
			}
		}
		Collections.sort(pathList);
		Collections.sort(programList);
		
		con.printf("______________________\n");
		con.printf("Registered IS/Path Keywords\n");
		con.printf("______________________\n");
		for(String key:pathList){
			con.printf(key+"\n");
		}
		con.printf("___________________________\n");
		con.printf("Registered Program Keywords\n");
		con.printf("___________________________\n");
		for(String key:programList){
			con.printf(key+"\n");
		}
		con.printf("___________________________\n");
	}
	
	static void updateKeyword(String paramStr){
		String[] param = paramStr.split(" ");
		
		if(param.length != 2){
			con.printf("Bad Input. Try Again\n");
			return;
		}
		String oldKey = param[0].trim();
		String newKey = param[1].trim();
		
		if(prop.getProperty(oldKey) == null){
			con.printf("Keyword-"+oldKey+" is not registered.\n");
			return;
		}
		
		if(prop.getProperty(newKey) != null){
			con.printf("Keyword-"+newKey+" is already registered.\n");
			return;
		}
		String value = prop.getProperty(oldKey);
		
		FileOutputStream fos = null;
    	try{
    		File configFile = new File(configFilepath);
    		fos = new FileOutputStream(configFile);
        	
    		prop.remove(oldKey);
    		prop.setProperty(newKey, value);
        	prop.store(fos, null);      	
        	con.printf("Keyword-"+newKey+" is registered.\n");
    	}catch(IOException e){
    		con.printf("Bad Input or Check config file. Try Again\n");
    	}finally {
    		if(fos != null){
    			try {    				
					fos.close();
				} catch (IOException e) {						
					e.printStackTrace();
				}
    		}
		}		
		
	}
	
	static void echoKeyword(String paramStr){
		String Path=prop.getProperty(paramStr.trim());
		if(Path != null){
			con.printf(paramStr+" = "+Path+"\n");
		}else{
			con.printf("Keyword-"+paramStr+" is not registered.\n");
		}
	}
	
	static synchronized void startIS(String paramStr){
		String[] param = paramStr.split(" ");
		String batchFilePath="";
		boolean debug = false;
		if(param.length==1)
			debug = false;
		else if(param.length==2){
			if(param[1].trim().equalsIgnoreCase("-d"))
				debug = true;
		}else{
			con.printf("Bad Input. Try Again\n");
			return;
		}
		
		//verify the keyword
		String ISPath=prop.getProperty(param[0].trim());
		if(ISPath == null){
			con.printf("Keyword-"+param[0]+" is not registered.\n");
			return;
		}
		
		if(ISPath.endsWith(".exe")){
			con.printf("Keyword-"+param[0]+" is not for IS operations.\n");
			return;
		}
		
		batchFilePath = ISPath + ISProfileBinPath1;
		if(!validatePath(batchFilePath, false)){
			batchFilePath = ISPath + ISProfileBinPath2;
			if(!validatePath(batchFilePath, false)){
				con.printf("Check if IS is installed properly.\n");
				return;
			}
		}
		
		//check if server is running/stopped
		if(validatePath(batchFilePath+".lock", true)){
			con.printf("Integration Server is already running.\n");
			return;
		}
				
		if(debug){
			runProgram(batchFilePath+"startDebugMode.bat");
			con.printf("Integration Server will be started in debug mode soon.\n");
		}else{
			runProgram(batchFilePath+"startup.bat");
			con.printf("Integration Server will be started soon.\n");
		}
		
	}
		
	static void statusIS(String paramStr){
		String[] param = paramStr.split(" ");
		String lockFilePath="";
		if(param.length!=1){
			con.printf("Bad Input. Try Again\n");
			return;
		}
		
		//verify the keyword
		String ISPath=prop.getProperty(param[0].trim());
		if(ISPath == null){
			con.printf("Keyword-"+param[0]+" is not registered.\n");
			return;
		}
		if(ISPath.endsWith(".exe")){
			con.printf("Keyword-"+param[0]+" is not for IS operations.\n");
			return;
		}
		
		lockFilePath = ISPath + ISProfileBinPath1;
		if(!validatePath(lockFilePath, false)){
			lockFilePath = ISPath + ISProfileBinPath2;
			if(!validatePath(lockFilePath, false)){
				con.printf("Check if IS is installed properly.\n");
				return;
			}
		}
		
		if(validatePath(lockFilePath+".lock", true)){
			con.printf("Integration Server is running.\n");
		}else{
			con.printf("Integration Server is stopped.\n");
		}
		
		
	}
	
	static void stopIS(String paramStr){
		String[] param = paramStr.split(" ");
		String batchFilePath="";
		if(param.length!=1){
			con.printf("Bad Input. Try Again\n");
			return;
		}
		
		//verify the keyword
		String ISPath=prop.getProperty(param[0].trim());
		if(ISPath == null){
			con.printf("Keyword-"+param[0]+" is not registered.\n");
			return;
		}
		if(ISPath.endsWith(".exe")){
			con.printf("Keyword-"+param[0]+" is not for IS operations.\n");
			return;
		}
		
		batchFilePath = ISPath + ISProfileBinPath1;
		if(!validatePath(batchFilePath, false)){
			batchFilePath = ISPath + ISProfileBinPath2;
			if(!validatePath(batchFilePath, false)){
				con.printf("Check if IS is installed properly.\n");
				return;
			}
		}
		
		//check if server is running/stopped
		if(!validatePath(batchFilePath+".lock", true)){
			con.printf("Integration Server is already stopped.\n");
			return;
		}
		
		runProgram(batchFilePath+"shutdown.bat");
		con.printf("Integration Server will be stopped soon.\n");
	}	
	
	static void launchSum(String paramStr){
		String[] param = paramStr.split(" ");
		String batchFilePath="";
		String repoServer="aquarius-va.ame.ad.sag";
		String repoName="GA_Fix_Repo";
		boolean showAll=false;
		
		String keyword = param[0].trim();
		
		for(int i=1; i<param.length; i++){
			if(param[i].trim().equalsIgnoreCase("-s")){
				repoServer = param[i+1].trim();
				i++;
			}
			if(param[i].trim().equalsIgnoreCase("-r")){
				repoName = param[i+1].trim();
				i++;
			}
			if(param[i].trim().equalsIgnoreCase("showAll")){
				showAll = true;
			}
		}		
		
		//verify the keyword
		String ISPath=prop.getProperty(keyword);
		if(ISPath == null){
			con.printf("Keyword-"+param[0]+" is not registered.\n");
			return;
		}
		
		if(ISPath.endsWith(".exe")){
			con.printf("Keyword-"+param[0]+" is not for IS operations.\n");
			return;
		}
		
		batchFilePath = ISPath + ISUpdateManagerPath;
		if(!validatePath(batchFilePath, false)){
			con.printf("Check if Update Manager is installed for "+ keyword +".\n");
			return;
		}
		
		//check if server is running/stopped
		if(validatePath(ISPath+ISProfileBinPath1+".lock", true)){
			con.printf("Integration Server is running. Stop the server. \n");
		}
		//Sample- UpdateManagerGUI.bat -server aquarius-blr.eur.ad.sag:GA_Fix_Repo -showAll true
		String instructionStr = batchFilePath+ " -server "+repoServer+":"+repoName+ (showAll?" -showAll true":"");
		runProgram(instructionStr);

	}
	
	static void openPath(String folderPath){
		boolean pathExist = validatePath(folderPath, false);
		if(!pathExist)
			return;
    	Runtime runTime = Runtime.getRuntime();
		try {
			runTime.exec("explorer "+folderPath);
		} catch (IOException e) {
			con.printf("Something is wrong. Could not open the path "+folderPath+"\n");
		}
	}
	
	static void runProgram(String program){
		validatePath(program, false);
		boolean running = isRunningAlready(program);
		if(running){
			con.printf("Application is running already.\n");
		}else{
			Runtime runTime = Runtime.getRuntime();
			try {
				runTime.exec(program);
			} catch (IOException e) {
				con.printf("Can't run the program. Reason:"+e.getMessage()+"\n");
			}
		}
    	
    }
	
	static boolean isRunningAlready(String program){
		if(!program.contains(".exe"))
			return false;
		int idx = program.lastIndexOf('\\');
		String exeName = program.substring(idx+1);
		boolean running=false;
		
		Runtime runTime = Runtime.getRuntime();
		try {
			Process p = runTime.exec("tasklist.exe");
			BufferedReader input =  new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = input.readLine()) != null) {
			    if(line.contains(exeName)){
			    	running=true;
			    	break;
			    }
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return running;
	}
	
	static boolean validatePath(String path, boolean suppressWarning){  
		//if path has attribute
		if(path.indexOf(" -") != -1){
			path = path.substring(0, path.indexOf(" -"));
		}
		
		File f = new File(path);
    	if(!f.exists()){
    		if(!suppressWarning)
    			con.printf(path+" doesn't exist.\n");
    		return false;
		}
    	return true;
    }
	
	static synchronized void launchPathOrProgram(String keyword, String paramStr){
		
		String storedPath=prop.getProperty(keyword.trim());
		//System.out.println(keyword+" "+keyword.trim());
		//System.out.println(storedPath);
		if(storedPath == null){
			con.printf("Keyword-"+keyword+" is not registered.\n");
			return;
		}
		
		if(storedPath.endsWith(".exe")){
			runProgram(storedPath);
			return;
		}
		
		//user specified folder/package
		String packageName = "";
		if(!paramStr.equalsIgnoreCase("")){
			if(paramStr.equalsIgnoreCase("jdbc")){
				packageName = "WmJDBCAdapter";
			}else if(paramStr.equalsIgnoreCase("sap")){
				packageName = "WmSAP";
			}else{
				packageName = paramStr;
			}
		}
		
		if(paramStr.equalsIgnoreCase("")){
			openPath(storedPath);
			return;
		}
		
		//check if it is normal path
		String finalPath = storedPath + ISInstancePath1;
		if(!validatePath(finalPath, true)){
			finalPath = storedPath + ISInstancePath2;
			if(!validatePath(finalPath, true)){
				con.printf("Check if IS is installed properly.\n");
				return;
			}
		}

		
		if(validatePath(finalPath+"packages\\"+packageName+"\\", true)){
			openPath(finalPath+"packages\\"+packageName+"\\");
		}else{				
			openPath(finalPath+"packages\\");
		}
			
		
		
	}
	
	static void startIC(){
		//start memcache(if not running already)
		launchPathOrProgram("memcached", "");
		con.printf("memcached is started.\n");
		
		//start smtp(if not running already)
		launchPathOrProgram("smtp", "");
		con.printf("smtp is started.\n");
		
		con.printf(serverStatesIC[0]+"\n"+serverStatesIC[1]+"\n");
		//Thread servers = new Thread(new ServerState(serverStatesIC));		
		//servers.start();

	}
	
	static List<String> findMatchingKeyword(String prefix){
		List<String> matchList = new ArrayList<String>();
		Set keywords = prop.keySet();				
			String key = "";
			for(Object k: keywords){
				key = k.toString();
				if(key.indexOf(prefix) == 0){
					matchList.add(key);
				}
			}
		return matchList;	
	}

	/*static void openPathUsingKey(String key) throws IOException{
		if(keyMap == null){
			BufferedReader br = new BufferedReader(new FileReader("C:/Users/anur/workspaceAdapter/AdapterUtility2.0/bin/FolderPaths.txt"));					
			keyMap = new HashMap();
			String str = br.readLine();
			while(str != null){
				String[] pair = str.split("=");
				keyMap.put(pair[0], pair[1]);
				str= br.readLine();
			}
		}
		
		openPath((String)keyMap.get(key));
	}
	
	static void listSavedPaths() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("C:/Users/anur/workspaceAdapter/AdapterUtility2.0/bin/FolderPaths.txt"));		
		Map pathMap = new HashMap();
		keyMap = new HashMap();
		con.printf("Saved Paths\n");
		int count=1;
		String str = br.readLine();
		while(str != null){
			String[] pair = str.split("=");
			pathMap.put(String.valueOf(count), pair[0]);
			con.printf(count + ". "+pair[0]+" \n");
			keyMap.put(pair[0], pair[1]);
			count++;
			
			str= br.readLine();
		}
		con.printf("0. Go to previous menu\n");
		String choice;
		int choiceNumber=0;
	    while(true){
	    	choice = con.readLine("Your Choice: ");
	        try{
	        	choiceNumber = Integer.parseInt(choice);
	        	if(choiceNumber<0 || choiceNumber>=count){
	        		con.printf("Bad Input. Try Again\n");
	        		continue;
	        	}else if(choiceNumber==0){
	        		break;
	        	}else{
	        		//processing valid input
	        		String key = (String)pathMap.get(String.valueOf(choiceNumber));
	            	openPath((String)keyMap.get(key));
	            	continue;
	        	}
	        	
	        }catch(NumberFormatException ex){
	        	con.printf("Bad Input. Try Again\n");
	        }
	    }   
	}*/
	

}
