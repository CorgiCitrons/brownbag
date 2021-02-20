package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignToDo_taskEleven extends BaseClass {

    @Test
    void putToDoToUser() {
        response = httpRequest.request(Method.PUT, properties.getProperty("assignToDo"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    void putToDoToUserInvalid() {
        response = httpRequest.request(Method.PUT, properties.getProperty("assignToDoNoUser"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));
    }

    @Test
    void putToDoInvalidToUser() {
        response = httpRequest.request(Method.PUT, properties.getProperty("assignToDoNoToDo"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("TodoNotFoundException"));
    }
    @Test
    void putToDoToUserNotComplete() {
        response = httpRequest.request(Method.PUT, properties.getProperty("assignToDOUserNotComplete"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 424);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("NotCompletedUserDataException"));
    }

}
