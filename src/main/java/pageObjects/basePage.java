package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class basePage {

	@FindBy(xpath="//a[contains(text(),\"Home\")]")
	private WebElement homeBtn;
	
	@FindBy(xpath="//a[contains(text(),\"Cart\")]")
	private WebElement cartBtn;
	
	public void clickOnHomeBtn()
	{
		homeBtn.click();
	}
	public void clickOnCartBtn()
	{
		cartBtn.click();
	}
}
