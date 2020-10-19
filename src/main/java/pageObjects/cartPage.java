package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class cartPage extends basePage {

	@FindBy(xpath="//td[text()=\"Dell i7 8gb\"]//following-sibling::td//a[text()=\"Delete\"]")
	private WebElement deleteBtn;
	
	@FindBy(xpath="//button[text()=\"Place Order\"]")
	private WebElement placeOrderBtn;
	
	@FindBy(id="name")
	private WebElement nameField;
	@FindBy(id="country")
	private WebElement countryField;
	@FindBy(id="city")
	private WebElement cityField;
	@FindBy(id="card")
	private WebElement cardField;
	@FindBy(id="month")
	private WebElement monthField;
	@FindBy(id="year")
	private WebElement yearField;
	@FindBy(id="totalm")
	private WebElement purchaseAmtBefore;
	@FindBy(xpath="//button[text()=\"Purchase\"]")
	private WebElement purchaseBtn;
	
	@FindBy(xpath="//h2[contains(text(),\"Thank you\")]//following-sibling::p")
	private WebElement purchaseId;
	@FindBy(xpath="//button[text()=\"OK\"]")
	private WebElement OKBtn;
	
	public void clickOnDeleteBtn()
	{
		deleteBtn.click();
	}
	public void clickOnPlaceOrderBtn()
	{
		placeOrderBtn.click();
	}
	public void enterName(String name)
	{
		nameField.sendKeys(name);
	}
	public void enterCountry(String country)
	{
		countryField.sendKeys(country);
	}
	public void enterCity(String city)
	{
		cityField.sendKeys(city);
	}
	public void enterCard(String card)
	{
		cardField.sendKeys(card);
	}
	public void enterMonth(String month)
	{
		monthField.sendKeys(month);
	}
	public void clickOnPurchaseBtn()
	{
		purchaseBtn.click();
	}
	public void enterYear(String year)
	{
		yearField.sendKeys(year);
	}
	public String getPurchaseAmtBeforeOrder()
	{
		String amt= purchaseAmtBefore.getText();
		System.out.println(amt+"==========");
		amt=amt.substring(7);
		return amt;
	}
	public String getPurchaseId()
	{
		return purchaseId.getText();
	}
	public void clickOnOKBtn()
	{
		OKBtn.click();
	}
}
