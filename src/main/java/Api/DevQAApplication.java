package Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Base.BaseApi;

import io.restassured.response.Response;

public class DevQAApplication extends BaseApi {

	@BeforeSuite
	public void init() {
		reportSetup("QA environment","Nithin Devarajan","API testing - DevQA application");
		log.info("initialization of the  application executed");
	}

	@Test(dataProvider="PostData",enabled=true)
	public void postTestData(int id, String employee_name,String employee_salary, int employee_age,String profile_image) {
		log.info("Posting the request");
		Response response = doPostRequest(id,employee_name,employee_salary,employee_age,profile_image);
		response.prettyPrint();
	}
	
	@Test(dataProvider="CustomerFeedDataProvider",enabled=false)
	public void excelPostTest(String id, String employee_name,String employee_salary, String employee_age,String profile_image ) throws IOException {
		log.info("Posting the request using excel");
		Response response =doPostExcel(id, employee_name,employee_salary,employee_age,profile_image);	
		response.prettyPrint();
	}

	/*@Test(enabled=false)
	public void putTest() {
		log.info("Updating according to the request");
		Response response=doPutRequest("http://localhost:3000/data/14");
		response.prettyPrint();
	}*/
	
	@Test(enabled=true,dataProvider="DeleteData")
	public void deleteTest(int id) {
		log.info("Deleting according to the request");
		Response response=doDeleteRequest(id);
		response.prettyPrint();
	}
	
	@Test(enabled=false)
	public void test_001_emp_greater_4lakhs() {
		test = extent.createTest("DevQA validation 4 lakhs : ", " Validating Employees greater than 4 lakhs");
		Response response = doGetRequest("http://localhost:3000/data");
		long resp_time = response.getTime();
		log.info("Response received in "+resp_time);


		List<String> list_of_names =new ArrayList<String>();
		List<Object> val = response.jsonPath().getList("employee_salary");
		int size = val.size();
		for(int i =0;i<size;i++) {
			int temp= response.jsonPath().getInt("employee_salary[" +i+"]");
			if(temp>400000) {
				String name = response.jsonPath().getString("employee_name["+i+"]");
				list_of_names.add(name);
			}
		}


		System.out.println("List of employees earning more than 400000");
		System.out.println("NUmber of employees : "+list_of_names.size());
		test.info("list of employees having sal greater than 4 lks  :"+list_of_names.size());
		for(String a : list_of_names) {
			System.out.println(a);
		}
		test.pass("Test Executed and results to be validated ");
	}
	@Test(enabled=false)
	public void test_002_emp_greater_3lakhs() {
		test = extent.createTest("DevQA validation 3 lakhs", " Validating Employees greater than 3 lakhs");

		Response response = doGetRequest("http://localhost:3000/data");
		long resp_time = response.getTime();
		log.info("Response received in "+resp_time);
		List<String> list_of_names =new ArrayList<String>();
		List<Object> val = response.jsonPath().getList("employee_salary");
		int size = val.size();
		for(int i =0;i<size;i++) {
			int temp= response.jsonPath().getInt("employee_salary[" +i+"]");
			if(temp>300000) {
				String name = response.jsonPath().getString("employee_name["+i+"]");
				list_of_names.add(name);
			}
		}


		System.out.println("List of employees earning more than 300000");
		System.out.println("NUmber of employees : "+list_of_names.size());
		test.info("list of employees having sal greater than 3 lks : "+list_of_names.size());

		for(String a : list_of_names) {
			System.out.println(a);
		}		test.pass("Test Executed and results to be validated ");

	}@Test(enabled=false)
	public void test_002_emp_greater_2lakhs() {
		test = extent.createTest("DevQA validation 2 lakhs", " Validating Employees greater than 2 lakhs salary");

		Response response = doGetRequest("http://localhost:3000/data");
		long resp_time = response.getTime();
		log.info("Response received in "+resp_time);
		List<String> list_of_names =new ArrayList<String>();
		List<Object> val = response.jsonPath().getList("employee_salary");
		int size = val.size();
		for(int i =0;i<size;i++) {
			int temp= response.jsonPath().getInt("employee_salary[" +i+"]");
			if(temp>200000) {
				String name = response.jsonPath().getString("employee_name["+i+"]");
				list_of_names.add(name);
			}
		}


		System.out.println("List of employees earning more than 200000");
		System.out.println("NUmber of employees : "+list_of_names.size());
		test.info("list of employees having sal greater than 2 lks : "+list_of_names.size());

		for(String a : list_of_names) {
			System.out.println(a);
		}
		test.pass("test executed data to be validated");
	}
	
	 @Test (enabled=false,priority=1)	
	 public void test_validation() {
			test = extent.createTest("JsonPlaceholder validation -", " Validating the catchphase validation ");

	        Response response = doGetRequest("https://jsonplaceholder.typicode.com/users");
	      List<String> jsonResponse = response.jsonPath().getList("address.geo");
	      List<String> catchPhase = response.jsonPath().getList("company.catchPhrase");
	       Object object = response.jsonPath().get("address.geo[0].lng");
	       System.out.println(jsonResponse);
	       System.out.println(object);
	       System.out.println("List of catchphases :"+catchPhase);
	       int size =catchPhase.size();
	       for(int k =0;k<size;k++) {
	    	   if(catchPhase.get(k).equals("Proactive didactic contingency")) {
	    		   		List<String> list = response.jsonPath().getList("name");
	    		   		String validate = list.get(k);
	    		   		System.out.println("Name of the user with catchphase  : "+catchPhase.get(k)+"is  : "+validate);
	    		 Constants.Constants.catchPhase=catchPhase.get(k);	
	    		 test.info("Catchphase extracted"+catchPhase.get(k));
	    	   }
	       }
	       test.pass("Catchphase  testcase passed");
	      
	    }
	 @Test (enabled=false,priority=2)	
	public void test2() {
			test = extent.createTest("Validating constants setup", " getting the catchphase from the constants val ");

  	   String test1= Constants.Constants.catchPhase;
  	   test.info("The data from the constants pulled : "+test1);
  	   test.pass("test case executed");
                   }
    
	 
	 @Test (enabled=false)
		public void failed_validation() {
				test = extent.createTest("Validating failed outcome", " validating Failed Scenarios");

	  	  test.fail("Execution of failed testcase setup");
	                   }
	    
     
      	



	@AfterSuite
	public void teardown(){


		extent.flush();
	}

}