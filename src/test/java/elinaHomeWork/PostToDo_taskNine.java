package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostToDo_taskNine extends BaseClass {

    @Test
    public void PostToDo() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Buy food");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postToDo"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void PostToDoInvalid() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postToDo"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("InvalidTodoException"));
    }
    @Test
    public void PostToDoAgain() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Buy toys");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postToDo"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        jsonObject.put("title", "Buy toys");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.POST, properties.getProperty("postToDo"));
        int statusCode2 = response.getStatusCode();
        Assert.assertEquals(statusCode2, 400);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("DublicatedTodoException"));
    }
}
