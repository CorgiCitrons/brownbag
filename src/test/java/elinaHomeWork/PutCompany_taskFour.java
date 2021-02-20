package elinaHomeWork;

import baseclass.BaseClass;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutCompany_taskFour extends BaseClass {

    @Test
    public void PutCompanyToUser() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bs", "23-45");
        jsonObject.put("catchPhrase", "I can do it");
        jsonObject.put("name", "HotelParadise");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addCompany"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    public void PutCompanyToUserWithMissingData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bs", "23-45");
        jsonObject.put("catchPhrase", "");
        jsonObject.put("name", "HotelParadise");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addCompany"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("InvalidCompanyDataException"));

    }
    @Test
    public void PutCompanyToUserThatDoesNotExist() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bs", "23-45");
        jsonObject.put("catchPhrase", "We all are great");
        jsonObject.put("name", "HotelParadise");

        httpRequest.header("content-Type", "application/json");
        httpRequest.body(jsonObject.toJSONString());
        response = httpRequest.request(Method.PUT, properties.getProperty("addCompanyNone"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);

        String statusException = response.getBody().asString();
        Assert.assertTrue(statusException.contains("UserNotFoundException"));

    }
}
