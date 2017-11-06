/*1.for reportNg dependency jar should be added and it will generate velocity.log file. in testng listeners tag is  reportng
2.for  log4j dependency jar should be needed and import Apache log4j not testing  log4j
3.for log4j log4j.properties file mandatory and it will generate automation.log
4.regression and sanity should be depend upon both grouping and testdata Exicute_flag "Y"

5.for utility class which  is available in base class activity.properties file is mandatory for call app & package Activity 
6. for build.xml create a build.xml standard code.check lib and testng.xml spelling are correct according to the project.make sure lib folder is available or not
7. 1st run the testng.xml according to the grouping. 
8.then run build.xml should be run.it will provide build folder.all the src/test/java folder will be available in build folder

*/

/*log4j--->automation.log(in the form of logger File(4))
 * reportng(listnertag)--->velocitylog(test-output>>>>html>>>>index.html(3))
 *activity.properties--->for utility class
 *pom--->all dependency jar
 *testng.xml--->testng suite(test-output>>>>>>emailable-report.html(1),index.html(2))**/


package senario_driver_components;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import generic_components.Base_class;
import pageobject_components.Page_ObjectSearch;

public class SenarioSearch_Components extends Base_class
 {
	
	public static Logger log=Logger.getLogger(SenarioSearch_Components.class);
	//dataprovider packageName and corresponding ClassName
    @Test(dataProvider="dp_invalidtest",dataProviderClass=dataprovider_components.DataProvider_TestData.class,groups={"sanity"})
	public void invalidSearch_Test(Map<String,String> search) throws IOException, InterruptedException
	{
    	SoftAssert sAssert= new SoftAssert();
    	//get all colname which is not available in dataprovider class
    	String TC_ID = search.get("TC_ID");
    	String Order_Set = search.get("Order_Set").replace(".0","");
    	String SearchItems = search.get("SearchItems");
    	String ExpectedResult = search.get("ExpectedResult");
    	
    	//extenttest=extentreport.startTest(TC_ID);	

    	serverStart();
       	log.info("Exicuting the TestCase "+TC_ID+" Order Set is "+Order_Set);
      	LaunchApp();
		//extenttest.log(LogStatus.PASS, "Executing the Testcase " +TC_ID+ " Order set is  "+Order_Set);

		
    	Page_ObjectSearch pob=new Page_ObjectSearch(driver);
    	pob.EnterSearch_items(SearchItems);
    	
    		
    	
    	Explicit_wait(pob.invalid_searchmesg,25);
    	String act_res = pob.getinvalidmesg();
	
	  if(act_res.equals(ExpectedResult))
	  {
		  log.info("Pass as actual test_result is "+act_res+"Expected_Result is "+ExpectedResult);
		 //extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));		        
	  }
	  else
		{
			log.info("Failed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult);
			//extenttest.log(LogStatus.FAIL, "Failed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
			capture_screenShot(TC_ID, Order_Set);
			sAssert.fail("Failed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult);
		}
	
	  	stopServer();
		sAssert.assertAll();
	}	

@Test(dataProvider="dp_validtest",dataProviderClass=dataprovider_components.DataProvider_TestData.class,groups={"regresion"})
public void validSearch(Map<String,String> search) throws IOException, InterruptedException
 {
	SoftAssert sAssert= new SoftAssert();
	
	String TC_ID = search.get("TC_ID");
	String Order_Set = search.get("Order_Set").replace(".0","");
	String SearchItems = search.get("SearchItems");
	String ExpectedResult = search.get("ExpectedResult").replace(".0","");
	
			
	//extenttest=extentreport.startTest(TC_ID);
	serverStart();
	log.info("Exicuting the Testcase "+TC_ID+" Order Set "+Order_Set);
	LaunchApp();
	//extenttest.log(LogStatus.PASS, "Executing the Testcase " +TC_ID+ " Order set is  "+Order_Set);

	
	Page_ObjectSearch pob=new Page_ObjectSearch(driver);
	pob.EnterSearch_items(SearchItems);
	
	
    Explicit_wait(pob.valid_searpdtcount, 35);
   
    String output = pob.getvalidmesg();
    String actual_res = output.replace(" products", "");
      
	if(actual_res.equals(ExpectedResult))
	{
		log.info("pass as Actual result is "+actual_res+" Expected result is "+ExpectedResult);
		//extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +actual_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
	    capture_screenShot(TC_ID, Order_Set);        
	}
	else
	{
		log.info("fail as testresult is "+actual_res+" Expected result is "+ExpectedResult);
	  //  extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +actual_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
		capture_screenShot(TC_ID, Order_Set);
		sAssert.fail("fail as testresult is "+actual_res+" Expected result is "+ExpectedResult);
	}
	
	stopServer();
	sAssert.assertAll();
	
 }
}












