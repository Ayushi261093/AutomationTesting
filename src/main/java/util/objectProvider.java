package util;

import pageObjects.cartPage;
import pageObjects.homePage;
import pageObjects.productPage;

public class objectProvider {

	public restAssuredFunctions restAssureObject()
	{
		return(new restAssuredFunctions());
	}
	public homePage homePageObject()
	{
		return(new homePage());
	}
	public productPage productPageObject()
	{
		return(new productPage());
	}
	public cartPage cartPageObject()
	{
		return(new cartPage());
	}
}
