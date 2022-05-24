package FTP_REST_project.RestSwaggerProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.apache.commons.io.IOUtils;

public class day1 {
@Test
public void getinuserMethod(){
	System.out.println("swagger project");
}

@Test(priority=2)
public void getMethod() {
	RestAssured.baseURI="https://petstore.swagger.io/v2";

	given()
	        .get("/user/seena").
	then()
	         .statusCode(200).log().all();
				
}
@Test(priority=1)
public void postmethodwithstring(){
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	String body="{\"username\":\"seena\",\"firstName\":\"seena\",\"lastName\":\"Deshmukh\",\"email\":\"s@gmail.com\",\"password\":\"str\",\"phone\":\"3456789320\",\"userStatus\":10}";
	Response resp=given()                
			.header("content-type","application/json").body(body).
	   when()
			.post("/user");
	System.out.println(resp.asString());
	Assert.assertEquals(resp.getStatusCode(), 200);
	Assert.assertEquals( resp.jsonPath().getInt("code"),200);
	
}

@Test
public void postwithJsontype() throws IOException {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	FileInputStream file=new FileInputStream(new File(System.getProperty("user.dir")+"\\Data\\post.json"));
	Response resp=given()
			.body(IOUtils.toString(file)).header("content-type","application/json").
	when()
	        .post("/user");
	Assert.assertEquals( resp.getStatusCode(),200);
	
}

@Test(dependsOnMethods="getMethod")
public void putMethodWithstring() {
	RestAssured.baseURI="https://petstore.swagger.io/v2";

	String body="{\"username\":\"see\",\"firstName\":\"seena\",\"lastName\":\"Deshmukh\",\"email\":\"s@gmail.com\",\"password\":\"str\",\"phone\":\"3456789320\",\"userStatus\":10}";
	Response resp=given()                
			.header("content-type","application/json").body(body).
	when()
	        .put("/user/seena");
	Assert.assertEquals( resp.getStatusCode(),200);

}@Test
public void putWithJsontype() throws IOException {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	JSONObject p=new JSONObject();
	p.put("id", 56);
	p.put("username", "seena");
	p.put("firstName", "seena");
	p.put("lastName", "deshmkh");
	p.put("email", "s@gmail.com");
	p.put("password", "3435");
	p.put("phone", "3456789320");
	p.put("userStatus", 10);
	Response resp=given()
			.header("Content-Type","application/json").body(p.toJSONString()).
	when()
	      .put("/user/see");

	Assert.assertEquals( resp.getStatusCode(),200);
}
@Test()
public void deleteMethod() {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
    given()
           .delete("/user/see").
    then()
          .statusCode(200).log().all().extract().response();
}
@Test
public void getMethod1() {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	given()
	      .get("/user/SEen").
	then()
	      .statusCode(404).log().all().extract().response();
}

@Test
public void deleteMethod2() {
	RestAssured.baseURI="https://petstore.swagger.io/v2";
  given()
         .delete("/user/seeea").
  then()
         .statusCode(404).log().all().extract().response();
}

}
