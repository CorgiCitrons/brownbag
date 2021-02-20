package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUser_taskThree extends BaseClass {

    @Test
    public void RequestToServerToDelete() {
        response = httpRequest.request(Method.DELETE, properties.getProperty("deleteUser"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);

    }

    @Test
    public void RequestToServerToDeleteExistingUser() {
        response = httpRequest.request(Method.DELETE, properties.getProperty("deleteUser"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));

    }
}
