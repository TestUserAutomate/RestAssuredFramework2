package Base;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;


public class BaseApi {
	public static Logger log= LogManager.getLogger(BaseApi.class);

	public static ExtentHtmlReporter htmlReporter; //for look and feel of the report
	public static ExtentReports extent; //create entries in report for every TC
	public ExtentTest test;
	
	public static Response doGetRequest(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;
		log.info("Response parsed and extracted");
		return
				given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
				when().get(endpoint).
				then().contentType(ContentType.JSON).extract().response();
		
	}
	

	  public static void reportSetup(String environment,String Tester_name,String ReportName)  {
			
		  htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		  htmlReporter.config().setDocumentTitle("Automation Report"); //Title of the report
		  htmlReporter.config().setReportName(ReportName); //Name of the report
		  htmlReporter.config().setTheme(Theme.DARK);
		
		  
		  extent=new ExtentReports();
		  log.info("Extent Reports configured");
		  extent.attachReporter(htmlReporter);
		  extent.setSystemInfo("Environment",environment);
		  extent.setSystemInfo("Tester Name", Tester_name);
			
	}
}
