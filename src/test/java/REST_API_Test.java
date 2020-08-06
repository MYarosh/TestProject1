

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class REST_API_Test {
    /*
    Create user with name "TestUser" and with job "QA Automation Engineer"
     */
    @Test
    public void createUser(){
        Person person = new Person("TestUser","QA Automation Engineer");

        given().log().body()
                .contentType("application/json").body(person)

                .when().post("https://reqres.in/api/users")

                .then().log().body()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
