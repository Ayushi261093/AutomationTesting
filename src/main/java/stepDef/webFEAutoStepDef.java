package stepDef;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import pageObjects.cartPage;
import pageObjects.homePage;
import pageObjects.productPage;
import util.objectProvider;

public class webFEAutoStepDef {

	WebDriver driver;
	objectProvider obj;
	homePage HomePage;
	productPage productPageObj;
	cartPage cartPageObj;
	String amtCart,amtAfterOrder;
	public webFEAutoStepDef()
	{
		obj=new objectProvider();
		HomePage=obj.homePageObject();
		productPageObj=obj.productPageObject();
		cartPageObj=obj.cartPageObject();
	}
	
	
	@Given("^I launch chrome driver and maximize window$")
	public void i_launch_chrome_driver_and_maximize_window() throws Throwable {
	   System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
	   driver=new ChromeDriver();
	   driver.manage().window().maximize();
	}

	@Then("^I navigate to demo \"([^\"]*)\"$")
	public void i_navigate_to_demo(String url) throws Throwable {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Then("^I click on laptop bar and select laptop one and add to cart$")
	public void i_click_on_laptop_bar_and_select_laptop_one_and_add_to_cart() throws Throwable {
		PageFactory.initElements(driver, HomePage);
	    HomePage.clickOnLaptopNav();
//	    Thread.sleep(2000);
	    HomePage.clickOnLaptopName1();
//	    Thread.sleep(2000);
	    PageFactory.initElements(driver, productPageObj);
	    productPageObj.clickOnAddCartBtn();
	    Thread.sleep(2000);
	    driver.switchTo().alert().accept();
	    productPageObj.clickOnHomeBtn();
	}
	@Then("^I click on laptop bar and select laptop two and add to cart$")
	public void i_click_on_laptop_bar_and_select_laptop_two_and_add_to_cart() throws Throwable {
//		PageFactory.initElements(driver, HomePage);
	    HomePage.clickOnLaptopNav();
//	    Thread.sleep(2000);
	    HomePage.clickOnLaptopName2();
//	    Thread.sleep(2000);
//	    PageFactory.initElements(driver, productPageObj);
	    productPageObj.clickOnAddCartBtn();
	    Thread.sleep(2000);
	    driver.switchTo().alert().accept();
	}
	@Then("^I navigate to cart and delete laptop two$")
	public void i_navigate_to_cart_and_delete_laptop_two() throws Throwable {
	    productPageObj.clickOnCartBtn();
//	    Thread.sleep(2000);
	    PageFactory.initElements(driver, cartPageObj);
	    cartPageObj.clickOnDeleteBtn();
	}
	@Then("^I click on place order$")
	public void i_click_on_place_order() throws Throwable {
		Thread.sleep(2000);
	    cartPageObj.clickOnPlaceOrderBtn();
	}
	@Then("^I fill all web form fields$")
	public void i_fill_all_web_form_fields(DataTable table) throws Throwable {
	   Map<String, String> formDetails=table.asMap(String.class, String.class);
	   Thread.sleep(2000);
	   amtCart=cartPageObj.getPurchaseAmtBeforeOrder();
	   System.out.println("Cart amount before order= "+amtCart);
	   cartPageObj.enterName(formDetails.get("name"));
	   cartPageObj.enterCountry(formDetails.get("country"));
	   cartPageObj.enterCity(formDetails.get("city"));
	   cartPageObj.enterCard(formDetails.get("credit card"));
	   cartPageObj.enterMonth(formDetails.get("month"));
	   cartPageObj.enterYear(formDetails.get("year"));
	}
	@Then("^I click on purchase button$")
	public void i_click_on_purchase_button() throws Throwable {
		cartPageObj.clickOnPurchaseBtn();
		Thread.sleep(2000);
	}
	@Then("^I log purchase id and amt$")
	public void i_log_purchase_id_and_amt() throws Throwable {
	   String purchaseDetails=cartPageObj.getPurchaseId();
	   String purchaseDetail[]=purchaseDetails.split("\n");
	   System.out.println("Purchase Id is: "+purchaseDetail[0].substring(4));
	   amtAfterOrder=purchaseDetail[1].substring(8);
	   System.out.println("Amount After Order "+amtAfterOrder);
			   
	}
	@Then("^I validate purchase amount is correct$")
	public void i_validate_purchase_amount_is_correct() throws Throwable {
	    amtCart=amtCart+" USD";
	    Assert.assertEquals("Validating amt to be correct", amtCart, amtAfterOrder);
	}
	@Then("^I click on ok button$")
	public void i_click_on_ok_button() throws Throwable {
	    cartPageObj.clickOnOKBtn();
	}

}
