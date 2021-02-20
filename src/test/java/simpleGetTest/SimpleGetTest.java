package simpleGetTest;

import baseclass.BaseClass;
import com.google.gson.JsonObject;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.apache.xmlbeans.impl.tool.PrettyPrinter;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest extends BaseClass {

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
