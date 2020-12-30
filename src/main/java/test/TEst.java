package test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Data.POJOClass;
import Utils.DataLibrary;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TEst {
	
	@Test(dataProvider = "Temp")
	public void circuitLocationTest(String id, String Emp_Name,String Emp_Sala, String age,String Profile) {
	POJOClass pojo=new POJOClass();
	pojo.setId(id);
	pojo.setEmployee_name(Emp_Name);
	pojo.setEmployee_salary(Emp_Sala);
	pojo.setEmployee_name(age);
	pojo.setProfile_image(Profile);
	Response response = doPostRequest(pojo);
	response.prettyPrint();
	}

	public static Response doPostRequest(POJOClass pojo) {
	
		return RestAssured.given().
		contentType(ContentType.JSON).
		body(pojo).
		when().post("http://localhost:3000/data").
		then().extract().response();
		
	}
		
		
	
	@DataProvider(name="Temp")
	public Object[][] readCustomerFeed() throws IOException {
		
		Object[][] readExcelData = DataLibrary.readExcelData("TestApplication");
		return readExcelData;
	}

	
	@DataProvider(name = "circuitLocations")
	public String[][] createCircuitTestData() {
			
		return new String[][] {
				{"adelaide","Australia"},
				{"detroit","USA"},
				{"george","South Africa"}
		};
	}

}
