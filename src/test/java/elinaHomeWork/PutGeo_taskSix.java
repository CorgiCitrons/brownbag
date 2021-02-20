package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutGeo_taskSix extends BaseClass {

    @Test
    void putGeoToAddress() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat", "27.4589");
        jsonObject.put("lng", "98.1254");


        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addGeo"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);

    }

    @Test
    void putInvalidGeoData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat", "27.4589");
        jsonObject.put("lng", "");


        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addGeo"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("InvalidGeoDataException"));
    }

    @Test
    void putGeoToUserThatDoesNotExist() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat", "27.4589");
        jsonObject.put("lng", "77.8256");


        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addGeoNone"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));
    }

    @Test
    void putGeoToUserWithNoAddress() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat", "27.4589");
        jsonObject.put("lng", "77.8256");


        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addGeoNoAddress"));

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("MissingAddressException"));
    }
}
