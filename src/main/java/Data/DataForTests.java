package Data;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataForTests {

	/*@DataProvider(name="PostData")
	public Object[][] dataForPost() {
		return new Object[][] {
			{34,"Raul","100000",30,""},
			{35,"Pepe","50000",31,""},
			{36,"Ozil","90000",32,""}
		};
	}*/
	

	@Test(dataProvider="PostData",enabled=true)
	public void postTest(int id, String employee_name,String employee_salary, int employee_age,String profile_image) {
		JSONObject json=new JSONObject();
		json.put("id", id);
		json.put("employee_name", employee_name);
		json.put( "employee_salary", employee_salary);
		json.put("employee_age",employee_age);
		json.put("profile_image", profile_image);
		
		RestAssured.given().
		contentType(ContentType.JSON).
		body(json).
		when().post("http://localhost:3000/data").
		then().extract().response();
	}
}