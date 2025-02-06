import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Send GET request to the endpoint
        Response response = RestAssured.get("https://api.coindesk.com/v1/bpi/currentprice.json");
        
        // Check if the response status code is 200 (OK)
        response.then().statusCode(200);

        // Verify that the response body contains 3 BPIs (USD, GBP, EUR)
        response.then().body("bpi.USD", notNullValue());
        response.then().body("bpi.GBP", notNullValue());
        response.then().body("bpi.EUR", notNullValue());

        // Verify that the 'description' for GBP equals 'British Pound Sterling'
        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        assertThat(gbpDescription, equalTo("British Pound Sterling"));
        
        // Optionally, print the whole response for inspection
        response.prettyPrint();

	}

}
