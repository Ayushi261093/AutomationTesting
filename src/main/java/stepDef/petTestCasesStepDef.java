package stepDef;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import org.apache.commons.lang3.RandomUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.it.Date;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;
import io.restassured.response.ValidatableResponse;
import junit.framework.Assert;
import util.objectProvider;

public class petTestCasesStepDef {
	
	String baseUrl="https://petstore.swagger.io/v2";
	ValidatableResponse response;
	objectProvider obj;
	int newId=RandomUtils.nextInt(1, 1000000);
	
	public petTestCasesStepDef() {
		obj=new objectProvider();
	}
	@Given("^I hit on GET API to get list of \"([^\"]*)\" pets$")
	public void i_hit_on_GET_API_to_get_list_of_pets(String type) throws Throwable {
		String endpoint="/pet/findByStatus";
		Map<String,String> parameter=new HashMap<String,String>();
		parameter.put("status", type);
	    response=obj.restAssureObject().getCallWithQueryParam(baseUrl, endpoint, parameter);
	    
	}
	@And("^I validate output for having list of available pets$")
	public void i_validate_output_for_having_list_of_available_pets() throws Throwable {
		Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	    Assert.assertTrue("Validating output for not empty", !response.extract().body().asString().isEmpty());
	}

	@Then("^I hit POST API to post a new pet to store with \"([^\"]*)\"$")
	public void i_hit_POST_API_to_post_a_new_pet_to_store_with(String arg1) throws Throwable {
	    String endpoint="/pet";
	    JsonParser parser=new JsonParser();
	    FileReader fileRead=new FileReader(new File("./src/test/resources/json/newPetJson.json"));
	    JsonElement jsonElem=parser.parse(fileRead);
	    String payload=jsonElem.getAsJsonObject().toString();
	    
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.id", newId).jsonString();
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.category.id", newId).jsonString();
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.tags[0].id", newId).jsonString();
	    response=obj.restAssureObject().postCall(baseUrl, endpoint, payload);
	    Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	}

	@Then("^I hit API with pet Id to validate pet is posted successfully to store$")
	public void i_hit_API_with_pet_Id_to_validate_pet_is_posted_successfully_to_store() throws Throwable {
	    String endpoint="/pet/"+newId;
	    System.out.println("Id of pet is "+newId);
	    response=obj.restAssureObject().getCall(baseUrl, endpoint);
	    Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	    String data=response.extract().body().asString();
	    System.out.println("Name of pet is: "+com.jayway.jsonpath.JsonPath.read(data, "$.name"));
	}

	@Then("^I update status of above pet id to new \"([^\"]*)\"$")
	public void i_update_status_of_above_pet_id_to_new(String newStatus) throws Throwable {
		String endpoint="/pet";
	    JsonParser parser=new JsonParser();
	    FileReader fileRead=new FileReader(new File("./src/test/resources/json/updateStatus.json"));
	    JsonElement jsonElem=parser.parse(fileRead);
	    String payload=jsonElem.getAsJsonObject().toString();
	    
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.id", newId).jsonString();
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.category.id", newId).jsonString();
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.tags[0].id", newId).jsonString();
	    payload=com.jayway.jsonpath.JsonPath.parse(payload).set("$.status", newStatus).jsonString();
	    response=obj.restAssureObject().putCall(baseUrl, endpoint, payload);
	    Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	}

	@Then("^I validate status to be updated using find pet by status api$")
	public void i_validate_status_to_be_updated_using_find_pet_by_status_api() throws Throwable {
		String endpoint="/pet/findByStatus";
		Map<String,String> parameter=new HashMap<String,String>();
		parameter.put("status", "sold");
	    response=obj.restAssureObject().getCallWithQueryParam(baseUrl, endpoint, parameter);
	    Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	    List<Long> idList=com.jayway.jsonpath.JsonPath.read(response.extract().body().asString(), "$.[*].id");
	    Assert.assertTrue("validating id to be present with sold status",idList.contains(newId));
	}

	@Then("^I delete this pet by id$")
	public void i_delete_this_pet_by_id() throws Throwable {
		String endpoint="/pet/"+newId;
		response=obj.restAssureObject().deleteCall(baseUrl, endpoint);
	    Assert.assertEquals(200, response.extract().statusCode());
	    System.out.println("Validated status code");
	}

}
