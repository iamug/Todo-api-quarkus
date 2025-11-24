package org.acme.todo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TodoResourceTest {

    @Test
    public void testCreateTodo() {
        given()
                .contentType("application/json")
                .body("{\"title\":\"Test todo\",\"completed\":false}")
                .when()
                .post("/todos")
                .then()
                .statusCode(201)
                .body("title", equalTo("Test todo"))
                .body("completed", equalTo(false));
    }

    @Test
    public void testValidationFail() {
        given()
                .contentType("application/json")
                .body("{\"title\":\"\",\"completed\":false}")
                .when()
                .post("/todos")
                .then()
                .statusCode(400); // title is blank → validation failure
    }

    @Test
    public void testGetAll() {
        when().get("/todos")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateNotFound() {
        given()
                .contentType("application/json")
                .body("{\"title\":\"Updated\",\"completed\":true}")
                .when()
                .put("/todos/9999")
                .then()
                .statusCode(404);
    }

}
