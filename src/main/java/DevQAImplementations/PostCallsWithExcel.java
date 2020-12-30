package DevQAImplementations;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ProjectSpecifics.DevQAProjects;
import Utils.DataLibrary;
import io.restassured.response.Response;

public class PostCallsWithExcel extends DevQAProjects {

	
	@DataProvider(name="CustomerFeedDataProvider")
	public Object[][] readCustomerFeed() throws IOException {
		Object[][] readExcelData=DataLibrary.readExcelData("TestData");
		return readExcelData;
	}
	
	
	@Test(dataProvider="CustomerFeedDataProvider")
	public void excelPostTest(String id, String employee_name,String employee_salary, String employee_age,String profile_image ) throws IOException {
		log.info("Posting the request using excel");
		log.info(id);
		Response response =doPostExcel(id, employee_name,employee_salary,employee_age,profile_image);	
			response.prettyPrint();
	}

	

}
