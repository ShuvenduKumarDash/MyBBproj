package generic_components;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Base_class 
{
	 public Process process;
	 public AppiumDriver driver;

	 public static ExtentReports extentreport;
	 public static ExtentTest extenttest;
	 
	 //@BeforeTest(groups={"sanity","regresion"})
	public void serverStart() throws IOException, InterruptedException
	{
		String str="D:\\IMPORTANT\\appium\\Appium\\node.exe  D:\\IMPORTANT\\appium\\Appium\\node_modules\\appium\\bin\\appium.js";
		process = Runtime.getRuntime().exec(str);
		if(process!=null)
		{	
			System.out.println("---Appium Server Start Successfully---");
		}
		else
		{	
			System.out.println("***Appium Server Started failure***");
		}
		Thread.sleep(25000);
	}
	//-------------------------------------------------------------------
	public void LaunchApp() throws InterruptedException, IOException
	{
		
	  DesiredCapabilities capability=new DesiredCapabilities();
	  capability.setCapability("deviceName","S");
      capability.setCapability("platformName","Android");
      capability.setCapability("platformVersion","4.4.2");       
      

      capability.setCapability("appPackage",Utility_class.Read_propertyutil("Package_name"));      
      capability.setCapability("appActivity",Utility_class.Read_propertyutil("Activity_name"));
		
      
      
      driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capability);
     
      
	  Thread.sleep(5000);
	  System.out.println("@@@Launch App Successfully@@@");
	
	
	}
	//---------------------------------------------------------------------
	public void Explicit_wait(WebElement ele,long t1)
	{	
		WebDriverWait wait= new WebDriverWait(driver, t1);
		wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
	}
	//-------------------------------------------------
	public String capture_screenShot(String TC_ID,String Order_Set) throws IOException
	{
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str=df.format(date)+".png";
		
		EventFiringWebDriver webDriver=new EventFiringWebDriver(driver);
		File srcFile = webDriver.getScreenshotAs(OutputType.FILE);
		File desc=new File("D:\\My_BB_Projecct_Data\\screenshot\\"+TC_ID+"-"+Order_Set+"-"+str);
		FileUtils.copyFile(srcFile, desc); 
		
		String path="D:\\My_BB_Projecct_Data\\screenshot\\"+TC_ID+"-"+Order_Set+"-"+str;
		 return path;
			
			/*Date date= new Date();
			SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
			
			String str=df.format(date)+".png";
			
			TakesScreenshot screenshot=(TakesScreenshot) driver;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotAs, new File("E:\\My_BB_Projecct_Data\\screenshot\\"+TC_ID+"-"+Order_Set+"-"+str));*/
			
		
		}
	
	//--------------------------------------------
	/*@BeforeSuite(groups={"sanity","regresion"})
	public static void extend_repo()
	{   
		System.out.println("hallow");
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String report = df.format(date);
	  
		extentreport=new ExtentReports("E:\\My_BB_Projecct_Data\\Report\\"+"BB_proj"+"-"+report+".html",false);
		
	}*/
	
	//------------------------------------------------------------
	
	 //@AfterTest(groups={"sanity","regresion"})
	public void stopServer() throws InterruptedException
	{	
	   if(process!=null)
	   {
		  process.destroy();
		  Thread.sleep(5000);
		  System.out.println("---Stop Server Successfully---");
	   }
	   /*extentreport.endTest(extenttest);
	   extentreport.flush();*/
	}   
}
