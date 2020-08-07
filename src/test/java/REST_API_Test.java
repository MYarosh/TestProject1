

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class REST_API_Test {
    private Person person = new Person("TestUser","QA Automation Engineer");
    /*
    Create user with name "TestUser" and with job "QA Automation Engineer"
     */
    @Test
    public void createUser(){
         person = given().log().body()
                .contentType("application/json").body(person)

                .when().post("https://reqres.in/api/users")

                .then().log().body()
                .statusCode(HttpStatus.SC_CREATED).and().extract().body().as(Person.class);
         /*person.setCreatedAt(person1.getCreatedAt());
         person.setId(person1.getId());*/
    }
    /*
    Get list of users
     */
    @Test
    public void getListUsers(){
        given().pathParam("id",person.getId())
                .when().get("https://reqres.in/api/users?per_page={id}")
                .then().log().body()
                .statusCode(HttpStatus.SC_OK);
    }
    /*
    Check that user are exist
     */
    @Test
    public void checkUser(){
        given().pathParam("id",person.getId())
                .when().get("https://reqres.in/api/users/{id}")
                .then().log().body().statusCode(HttpStatus.SC_OK);
    }
    /*
    Update job of user
     */
    @Test
    public void updateUser(){
        person.setJob("Senior QA Automation Engineer");
        given().log().body()
                .contentType("application/json").body(person)
                .pathParam("id",person.getId())
                .when().put("https://reqres.in/api/users/{id}")
                .then().log().body().statusCode(HttpStatus.SC_OK);
    }
    /*
    Delete user
     */
    @Test
    public void deleteUser(){
        given().pathParam("id",person.getId())
                .when().delete("https://reqres.in/api/users/{id}")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
