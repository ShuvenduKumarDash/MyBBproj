package pageobject_components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;

public class PageObject_Cart 
 {
  
	
  @FindBy(id="com.bigbasket.mobileapp:id/btnAddToBasket")
  public WebElement Add_btn;
  
  
  @FindBy(id="com.bigbasket.mobileapp:id/basketimageview")
  public WebElement Cartimg_btn;
  
  @FindBy(id="com.bigbasket.mobileapp:id/txtProductDesc")
  public WebElement Cartpdtadd_mesg;
	
  @FindBy(id="com.bigbasket.mobileapp:id/imgRemove")
  public WebElement Delete_btn;	
	
  @FindBy(id="com.bigbasket.mobileapp:id/txtEmptyMsg1")
  public WebElement Delete_mesg;	

	
	
	public PageObject_Cart(AppiumDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	
	public void click_addButton()
	{
		Add_btn.click();
	}
	public void clickCartimg_btn()
	{
		Cartimg_btn.click();
	}
	public String getaddCartmesg()
	{	
		return Cartpdtadd_mesg.getText();	
	}
	public void clickDelete_btn()
	{	
		 Delete_btn.click();
	}
	public String getDelete_mesg()
	{
		return Delete_mesg.getText();
	}
 }
