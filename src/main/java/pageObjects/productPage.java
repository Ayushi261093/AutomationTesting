package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class productPage extends basePage {

	@FindBy(xpath="//a[contains(text(),\"Add to cart\")]")
	private WebElement addToCartBtn;
	
	
	
	public void clickOnAddCartBtn()
	{
		addToCartBtn.click();
	}
	
}
