package Utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class FileReaderHandle {

	
	public Properties PropertiesHandler(String configFile) throws Exception{
		  FileReader reader;
		  reader = new FileReader("./resources/"+configFile+".properties");
		  Properties p=new Properties();  
		  p.load(reader); 
		  return p;
	
}
}