package DevQAImplementations;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ProjectSpecifics.DevQAProjects;
import io.restassured.response.Response;

public class PostCalls extends DevQAProjects {
	@DataProvider(name="PostData")
	public Object[][] dataForPost() {
		return new Object[][] {
			{134,"TestName","100000",30,""},
			{135,"Pepe","50000",31,""}
		
		};
	}

	@Test(dataProvider="PostData",enabled=true)
	public void postTestData(int id, String employee_name,String employee_salary, int employee_age,String profile_image) {
		log.info("Posting the request");
		Response response = doPostRequest(id,employee_name,employee_salary,employee_age,profile_image);
		response.prettyPrint();
	}

}
