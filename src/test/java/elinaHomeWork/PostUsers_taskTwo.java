package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostUsers_taskTwo extends BaseClass {

    @Test
    public void PostNewUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "Ilmi@gmail.com");
        jsonObject.put("name", "Ilmars");
        jsonObject.put("phone", "55r5r5");
        jsonObject.put("userName", "Ilmarions");
        jsonObject.put("website", "www.Ilmis.com");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void PostUserWithInvalidData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eli@gmail.com");
        jsonObject.put("phone", "26133222");
        jsonObject.put("userName", "EliiU");
        jsonObject.put("website", "www.eliiU.com");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("InvalidUserDataException"));

    }

    @Test
    public void PostUserWithDuplicateUsernme() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eli@gmail.com");
        jsonObject.put("name", "Elina");
        jsonObject.put("phone", "26133222");
        jsonObject.put("userName", "EliiU");
        jsonObject.put("website", "www.eliiU.com");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postData"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("DublicateUserNameException"));

    }
}
