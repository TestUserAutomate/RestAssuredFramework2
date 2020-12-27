package ProjectSpecifics;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Base.BaseApi;

public class DevQAProjects extends BaseApi{
	


	@BeforeSuite
	public void init() {
		reportSetup("QA environment","Nithin Devarajan","API testing - DevQA application");
		log.info("initialization of the  application executed");
	}


	@AfterSuite
	public void teardown(){


		extent.flush();
	}

}
