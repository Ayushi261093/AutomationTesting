package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends basePage{

	
	@FindBy(xpath="//a[text()=\"Laptops\"]")
	private WebElement laptopNav;
	
	@FindBy(xpath="//a[contains(text(),\"Sony vaio i7\")]")
	private WebElement laptopName1Elem;
	
	@FindBy(xpath="//a[contains(text(),\"Dell i7 8gb\")]")
	private WebElement laptopName2Elem;
	
	public void clickOnLaptopNav()
	{
		laptopNav.click();
	}
	public void clickOnLaptopName1()
	{
		laptopName1Elem.click();
	}
	public void clickOnLaptopName2()
	{
		laptopName2Elem.click();
	}
}
