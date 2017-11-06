package generic_components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility_class 
 {
  public static String Read_propertyutil(String skey) throws IOException
  {	
	  FileInputStream fis=new FileInputStream("activity.properties");
	  Properties pro=new Properties();
	  pro.load(fis);
	  return pro.getProperty(skey);
	  
	  
  }
 }
