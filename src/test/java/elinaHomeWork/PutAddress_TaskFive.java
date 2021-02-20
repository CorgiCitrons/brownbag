package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutAddress_TaskFive extends BaseClass {

    @Test
    void putData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Bond street");
        jsonObject.put("suite", "007");
        jsonObject.put("city", "London");
        jsonObject.put("zipcode", "00700");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecord"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);

    }

    @Test
    void putInvalidData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Bond street");
        jsonObject.put("suite", "007");
        jsonObject.put("city", "");
        jsonObject.put("zipcode", "00700");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecord"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("InvalidAddressDataException"));
    }

    @Test
    void putDataToInvalidUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("street", "Bond street");
        jsonObject.put("suite", "007");
        jsonObject.put("city", "London");
        jsonObject.put("zipcode", "00700");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("updateRecordNone"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));
    }
}
