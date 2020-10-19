package util;


import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class restAssuredFunctions {

	public ValidatableResponse getCallWithQueryParam(String baseUrl,String endpoint, Map<String,String> parameter) {
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri(baseUrl);
		builder.setBasePath(endpoint);
		builder.addQueryParams(parameter);
		builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
		builder.setUrlEncodingEnabled(false);
		builder.setContentType(ContentType.JSON);
		RequestSpecification request=builder.build();
		ValidatableResponse response= RestAssured.given().spec(request).get().then().log().status();
		return response;
	}
public ValidatableResponse postCall(String baseUrl,String endpoint, String payload) {
		
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setBaseUri(baseUrl);
		builder.setBasePath(endpoint);
		builder.setBody(payload);
		builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
		builder.setUrlEncodingEnabled(false);
		builder.setContentType(ContentType.JSON);
		RequestSpecification request=builder.build();
		ValidatableResponse response= RestAssured.given().spec(request).post().then().log().status();
		return response;
	}
public ValidatableResponse getCall(String baseUrl,String endpoint) {
	
	RequestSpecBuilder builder=new RequestSpecBuilder();
	builder.setBaseUri(baseUrl);
	builder.setBasePath(endpoint);
	builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
	builder.setUrlEncodingEnabled(false);
	builder.setContentType(ContentType.JSON);
	RequestSpecification request=builder.build();
	ValidatableResponse response= RestAssured.given().spec(request).get().then().log().status();
	return response;
}
public ValidatableResponse putCall(String baseUrl,String endpoint, String payload) {
	
	RequestSpecBuilder builder=new RequestSpecBuilder();
	builder.setBaseUri(baseUrl);
	builder.setBasePath(endpoint);
	builder.setBody(payload);
	builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
	builder.setUrlEncodingEnabled(false);
	builder.setContentType(ContentType.JSON);
	RequestSpecification request=builder.build();
	ValidatableResponse response= RestAssured.given().spec(request).put().then().log().status();
	return response;
}
public ValidatableResponse deleteCall(String baseUrl,String endpoint) {
	
	RequestSpecBuilder builder=new RequestSpecBuilder();
	builder.setBaseUri(baseUrl);
	builder.setBasePath(endpoint);
	builder.setConfig(RestAssuredConfig.config().sslConfig(new SSLConfig().relaxedHTTPSValidation()));
	builder.setUrlEncodingEnabled(false);
	builder.setContentType(ContentType.JSON);
	RequestSpecification request=builder.build();
	ValidatableResponse response= RestAssured.given().spec(request).delete().then().log().status();
	return response;
}
}
