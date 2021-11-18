package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CP {
	
	Properties property;
	
	public Properties getObjectRepository() {
				
		Properties appProps = new Properties();
		
		Path resourceDirectory = Paths.get("src","test","resources");
		String absolutePath = resourceDirectory.toFile().getAbsolutePath();
		try {
			appProps.load(new FileInputStream(absolutePath + "\\serenities.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appProps;
	}
	
	

}
