import org.apache.http.HttpStatus;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class REST_API_Test {
    private Person person;
    private List<Person> list = new ArrayList<Person>();

    /*
    Create user with name and job
     */
    @Parameters({"name", "job"})
    @Test(priority = 0)
    public void createUser(String name, String job){
         person = new Person(name,job);
         person = given()
                 .contentType("application/json").body(person)
                 .when().post("https://reqres.in/api/users")
                 .then()
                 .statusCode(HttpStatus.SC_CREATED).and().extract().body().as(Person.class);
         assertEquals(person.getJob(), job);
         assertEquals(person.getName(), name);
         System.out.println("Test createUser. Complete.");
    }

    /*
    Get list of users
     */
    @Test(priority = 1)
    public void getListUsers(){
        list = given().pathParam("id",person.getId())
                .when().get("https://reqres.in/api/users?per_page={id}")
                .then()
                .statusCode(HttpStatus.SC_OK).and().extract().body().jsonPath().getList("$.data", Person.class);
        assertNotNull(list);
        System.out.println("Test getListUsers. Complete.");
    }

    /*
    Check that user are exist
     */
    @Test(priority = 2)
    public void checkUser(){
        boolean b = list.contains(person);
        assertTrue(b);
        System.out.println("Test checkUser. Complete.");
    }

    /*
    Update job of user
     */
    @Parameters({"newJob"})
    @Test(priority = 3)
    public void updateUser(String newJob){
        person.setJob(newJob);
        person = given()
                .contentType("application/json").body(person)
                .pathParam("id",person.getId())
                .when().put("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK).and().extract().body().as(Person.class);
        assertEquals(person.getJob(), newJob);
        System.out.println("Test updateUser. Complete.");
    }

    /*
    Delete user
     */
    @Test(priority = 4)
    public void deleteUser(){
        given().pathParam("id",person.getId())
                .when().delete("https://reqres.in/api/users/{id}")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
        System.out.println("Test deleteUser. Complete.");
    }
}
