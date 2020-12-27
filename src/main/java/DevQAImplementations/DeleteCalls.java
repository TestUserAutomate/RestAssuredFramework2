package DevQAImplementations;

import java.util.Scanner;

import org.testng.annotations.Test;

import ProjectSpecifics.DevQAProjects;
import io.restassured.response.Response;

public class DeleteCalls extends DevQAProjects{

	@Test
	public static void deleteTest() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id that you would want to delete");
		int delval= sc.nextInt();
		log.info("Deleting according to the request");
		Response response=doDeleteRequest(delval);
		response.prettyPrint();
	}
}
