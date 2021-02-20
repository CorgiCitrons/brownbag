package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUsers_taskOne extends BaseClass {

    @Test
    void requestToServer() {
        response = httpRequest.request(Method.GET, properties.getProperty("getAllUser"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        String responseBody = response.getBody().prettyPrint();
        System.out.print(responseBody);
        Assert.assertTrue(responseBody.contains("Eli"));
    }
}
