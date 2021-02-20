package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserID_taskSeven extends BaseClass {

    @Test
    void getUserByID() {
        response = httpRequest.request(Method.GET, properties.getProperty("getUserByID"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().prettyPrint();
        System.out.print(responseBody);
        Assert.assertTrue(responseBody.contains("Eli"));
    }
    @Test
    void getUserByIdInvalid() {
        response = httpRequest.request(Method.GET, properties.getProperty("getUserByIDNone"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));
    }
}
