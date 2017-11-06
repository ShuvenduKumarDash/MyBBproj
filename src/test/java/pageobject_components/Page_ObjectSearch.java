package pageobject_components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;

public class Page_ObjectSearch 
 {
	//2nd section
 	 @FindBy(id="com.bigbasket.mobileapp:id/homePageSearchBox")
	 public WebElement searchProduct_textbox;
 	
 	 @FindBy(id="com.bigbasket.mobileapp:id/searchView")
	 public WebElement searchbar_textbox;
 	 
 	 @FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
 	 public WebElement invalid_searchmesg;
 	 
 	 @FindBy(id="com.bigbasket.mobileapp:id/txtProductCount")
 	 public WebElement valid_searpdtcount;
 	 
 	 //1st section to fetch the AppiumDriver through constructor
 	 
 	 public Page_ObjectSearch(AppiumDriver driver)
 	 {	
 		 PageFactory.initElements(driver,this);
     }
	
 	 //3rd section reusable methods(all actions ~ keywordDriven)
 	 
	public void EnterSearch_items(String Search_items)
	{
		searchProduct_textbox.click();
		searchbar_textbox.sendKeys(Search_items+"\n");
	}
	
	public String getinvalidmesg()
	{
		return invalid_searchmesg.getText();
	}
	
	public String getvalidmesg()
	{
		return valid_searpdtcount.getText();
	}
	
	
	
	
 }
