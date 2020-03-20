package apiTest;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseTest;
import utils.ExtentReport;
import utils.Helper;

public class UserTest extends BaseTest {

	/*
	 * Count the books returned in response
	 */
	@Test
	public void userData() {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("get users",
				" status code should be 200");
		
		Helper.loginfo("testcase name", "get users");
		
		Response resp = getUserDetails("/api/users/2");

		int statusCode = resp.getStatusCode();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		
		//ArrayList<String> allBooks = (ArrayList) jsonPathEvaluator.getList("docs");
		//ExtentReport.ExtentReportInfoLog("Number of the books returned:     " + allBooks.size());

		assertEquals(statusCode, 200);

	}
	
	@Test
	public void userIdAsZero() {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("get users",
				" status code should be 404");
		
		Helper.loginfo("testcase name", "get users");
		
		Response resp = getUserDetails("/api/users/0");

		int statusCode = resp.getStatusCode();
		JsonPath jsonPathEvaluator = resp.jsonPath();

		assertEquals(statusCode, 404);

	}
		
		@Test
		public void userforPagetwo() {
			ExtentReport.extentlog = ExtentReport.extentreport.startTest("get users",
					" status code should be 404");
			
			Helper.loginfo("testcase name", "get users");
			
			Response resp = getUserDetails("https://reqres.in/api/users?page=2");

			int statusCode = resp.getStatusCode();
			JsonPath jsonPathEvaluator = resp.jsonPath();
			int pagenumber= resp.path("page");
            assertEquals(pagenumber,2);
			assertEquals(statusCode, 200);

		}
	
	@Test
	public void userIdAsSpecialChar() {
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("get users",
				" status code should be 404");
		
		Helper.loginfo("testcase name", "get users");
		
		Response resp = getUserDetails("/api/users/@#");

		int statusCode = resp.getStatusCode();
		JsonPath jsonPathEvaluator = resp.jsonPath();

		assertEquals(statusCode, 404);

	}

	/*
	 * params tailUrl - tail part of API token - Authorization token
	 * 
	 * return - response of the API
	 */
	public static Response getUserDetails(String tailUrl) {
		
		String baseUrl = Helper.propertyReader(Helper.commonFilePath, "baserurl");

		Helper.loginfo("Hitting API URL :- ", baseUrl + tailUrl);
		
		Response resp = null;
		
		try{
			
	    resp = given()
	    		.when().contentType(ContentType.JSON)
	    		.get(baseUrl + tailUrl);
		
		//Adding logs to log and html report
		Helper.loginfo("Response :- ", resp.body().asString());
		Helper.loginfo("Response time :- ", String.valueOf(resp.getTime()));
		ExtentReport.ExtentReportInfoLog("Response time:     " + String.valueOf(resp.getTime()));
		}
		catch (Exception e) {
			Helper.loginfo("Error :- ", e.getMessage());
		}
		
		return resp;
	}
}
