package senario_driver_components;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import generic_components.Base_class;
import pageobject_components.PageObject_Cart;
import pageobject_components.Page_ObjectSearch;

public class SenarioAddCart_component extends Base_class
{
	
public static Logger log=Logger.getLogger(SenarioAddCart_component.class);
@Test(dataProvider="dp_AddCart", dataProviderClass=dataprovider_components.DataProvider_TestData.class,groups={"sanity"})
public void atestaddCart(Map<String,String> cart) throws IOException, InterruptedException
 {
	SoftAssert sAssert=new SoftAssert();
	String TC_ID = cart.get("TC_ID");
	String Order_Set = cart.get("Order_Set");
	String SearchItems = cart.get("SearchItems");
	String ExpectedResult = cart.get("ExpectedResult");

	//extenttest=extentreport.startTest(TC_ID);

	serverStart();
	log.info("Exicute TestCase as "+"TC_ID "+"Order set is "+Order_Set);
	LaunchApp();
	//extenttest.log(LogStatus.PASS, "Executing the Testcase " +TC_ID+ " Order set is  "+Order_Set);

	
	Page_ObjectSearch pob=new Page_ObjectSearch(driver);
	pob.EnterSearch_items(SearchItems);
	

	
	PageObject_Cart poc=new PageObject_Cart(driver);
	
	Explicit_wait(poc.Add_btn,30);
	poc.click_addButton();
	
	Explicit_wait(poc.Cartimg_btn,30);
	poc.clickCartimg_btn();
	
	Explicit_wait(poc.Cartpdtadd_mesg,30);
	String actual_res = poc.getaddCartmesg();
	
	if(actual_res.equals(ExpectedResult))
	{	
		log.info("passed as ActualResult is "+actual_res+" ExpectedResult is "+ExpectedResult);
		//extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +actual_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
		capture_screenShot(TC_ID, Order_Set);
	}
	else
	{
		log.info("Failed as ActualResult is "+actual_res+" ExpectedResult is "+ExpectedResult);
		extenttest.log(LogStatus.FAIL, "Passed as Actual Result is  " +actual_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
		//capture_screenShot(TC_ID, Order_Set);
		sAssert.fail("Failed as ActualResult is "+actual_res+" ExpectedResult is "+ExpectedResult);
	}
	stopServer();
	sAssert.assertAll();
	
 }

@Test(dataProvider="dp_deletecart", dataProviderClass=dataprovider_components.DataProvider_TestData.class,groups={"regresion"})
public void deletecartTeat(Map<String,String> deletecart) throws IOException, InterruptedException
{
	SoftAssert sAssert=new SoftAssert();
	String TC_ID = deletecart.get("TC_ID");
	String Order_Set = deletecart.get("Order_Set");
	String SearchItems = deletecart.get("SearchItems");
	String ExpectedResult = deletecart.get("ExpectedResult");
	
   //extenttest=extentreport.startTest(TC_ID);	
   serverStart();
   log.info("Exicuting the testCase is "+TC_ID+" OrderrSet is"+ Order_Set);
   LaunchApp();
 //  extenttest.log(LogStatus.PASS, "Executing the Testcase " +TC_ID+ " Order set is  "+Order_Set);
   
   
   
 Page_ObjectSearch pob=new Page_ObjectSearch(driver);
 pob.EnterSearch_items(SearchItems);
 

 PageObject_Cart poc=new PageObject_Cart(driver);
 Explicit_wait(poc.Add_btn, 25);
 poc.click_addButton();
	
 Explicit_wait(poc.Cartimg_btn, 25);
 poc.clickCartimg_btn();
	
 Explicit_wait(poc.Delete_btn, 25);
 poc.clickDelete_btn();
 
 Explicit_wait(poc.Delete_mesg, 25);
 String act_res = poc.getDelete_mesg();
	
if(act_res.equals(ExpectedResult))
 {
	log.info("passed as ActualResult is "+act_res+" Expected result is "+ExpectedResult);
	//extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));
 	capture_screenShot(TC_ID, Order_Set);
 }	
 else
 {
	log.info("failed as ActualResult is "+act_res+" Expected result is "+ExpectedResult);
	//extenttest.log(LogStatus.PASS, "Passed as Actual Result is  " +act_res + "  Expected Result is  "+ExpectedResult,extenttest.addScreenCapture(capture_screenShot(TC_ID, Order_Set)));

	capture_screenShot(TC_ID, Order_Set);
	sAssert.fail("failed as ActualResult is "+act_res+" Expected result is "+ExpectedResult);
 }
stopServer();
sAssert.assertAll();
}
}
