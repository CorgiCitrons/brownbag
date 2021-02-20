package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserUsername_taskEight extends BaseClass {

    @Test
    void getUserByUsername() {
        response = httpRequest.request(Method.GET, properties.getProperty("getUserByUsername"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().prettyPrint();
        System.out.print(responseBody);
        Assert.assertTrue(responseBody.contains("Lui"));
    }
    @Test
    void getUserByUsernameInvalid() {
        response = httpRequest.request(Method.GET, properties.getProperty("getUserByUsernameNone"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));
    }
}
