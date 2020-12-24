package Base;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import Data.POJOClass;
import Utils.DataLibrary;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;


public class BaseApi{
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
	
	public static Response doPostRequest(int id, String employee_name,String employee_salary, int employee_age,String profile_image) {
		
		/*Map<String, Object> jsonToPost=new HashMap<>();
		jsonToPost.put("id", "26");
		jsonToPost.put("employee_name", "Steven smith");
		jsonToPost.put( "employee_salary", "500800");
		jsonToPost.put("employee_age","41");
		jsonToPost.put("profile_image", "");*/
		
	/*	POJOClass pojo=new POJOClass();
		pojo.setId(30);
		pojo.setEmployee_age(40);
		pojo.setEmployee_name("Sachin");
		pojo.setEmployee_salary("100000");
		pojo.setProfile_image("");*/
		
		JSONObject json=new JSONObject();
		json.put("id", id);
		json.put("employee_name", employee_name);
		json.put( "employee_salary", employee_salary);
		json.put("employee_age",employee_age);
		json.put("profile_image", profile_image);
		
		return RestAssured.given().
				contentType(ContentType.JSON).
		body(json).
		when().post("http://localhost:3000/data").
		then().extract().response();
	}
	
	
	
	public static Response doPutRequest(String endpoint,String id, String employee_name,String employee_salary, String employee_age,String profile_image) {
		
		/*Map<String, Object> jsonToPut=new HashMap<>();
		jsonToPut.put("id", "1");
		jsonToPut.put("employee_name", "Virat Kohli");
		jsonToPut.put( "employee_salary", "500800");
		jsonToPut.put("employee_age","41");
		jsonToPut.put("profile_image", "");*/
		
		POJOClass pojo=new POJOClass();
		pojo.setId(14);
		pojo.setEmployee_age(30);
		pojo.setEmployee_name("ViratKohli");
		pojo.setEmployee_salary("900000");
		pojo.setProfile_image("");
		
		return
		RestAssured.given().
		contentType(ContentType.JSON)
		.body(pojo)
		.when().put(endpoint)
		.then().extract().response();
		
	}
	
	public static Response doDeleteRequest(int id) {
		
		
		return
		RestAssured.given().
		when().delete("http://localhost:3000/data/"+id)
		.then().extract().response();
		
	}

public static Response doPostExcel(String id, String employee_name,String employee_salary, String employee_age,String profile_image ) throws IOException {
		
		
		return RestAssured.given().
				contentType(ContentType.JSON).
		body(readExcelData("CustomerFeed")).
		when().post("http://localhost:3000/data").
		then().extract().response();
	}
	
	
	public static Object[][] readExcelData(String excelfileName) throws IOException {
		XSSFWorkbook wbook = 
				new XSSFWorkbook("./data/"+excelfileName+".xlsx");
		String stringCellValue =null;
		XSSFSheet sheet = wbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum();
//		System.out.println("Row Count is: "+rowCount);
		int colCount = sheet.getRow(0).getLastCellNum();
//		System.out.println("Col Count is: "+colCount);
		Object[][] data = new Object[rowCount][colCount];
		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				XSSFCell cell = row.getCell(j);
				if(cell==null) {
					continue;
				}
				else if(cell.getCellType()==CellType.STRING)
					stringCellValue=cell.getStringCellValue();
				else if(cell.getCellType()==CellType.NUMERIC)
					stringCellValue=String.valueOf(cell.getNumericCellValue());
				//String stringCellValue = cell.getStringCellValue();
				data[i-1][j] = stringCellValue;
//				System.out.println(stringCellValue);
			} 
		}
		wbook.close();
		return data;
	}
	
	@DataProvider(name="CustomerFeedDataProvider")
	public Object[][] readCustomerFeed() throws IOException {
		Object[][] readExcelData=readExcelData("CustomerFeed");
		return readExcelData;
	}
	
	@DataProvider(name="PostData")
	public Object[][] dataForPost() {
		return new Object[][] {
			{34,"Raul","100000",30,""},
			{35,"Pepe","50000",31,""},
			{36,"Ozil","90000",32,""}
		};
	}

	
	@DataProvider(name="DeleteData")
	public Object[][] dataForDelete() {
		return new Object[][] {
			{38.0}
		};
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
