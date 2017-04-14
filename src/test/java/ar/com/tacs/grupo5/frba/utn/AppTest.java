package ar.com.tacs.grupo5.frba.utn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.controllers.JsonTransformer;
import spark.servlet.SparkApplication;

/**
 * Unit test for simple App.
 */
public class AppTest

{

	public static class TestContollerTestSparkApplication implements SparkApplication {
		@Override
		public void init() {
			new TestController();
		}
	}

	@ClassRule
	public static SparkServer<TestContollerTestSparkApplication> testServer = new SparkServer<>(
			AppTest.TestContollerTestSparkApplication.class, 8080);

//	@Test
//	public void testUsers() throws Exception {
//		GetMethod get = testServer.get("/api/users/", false);
//		HttpResponse httpResponse = testServer.execute(get);
//		assertEquals(200, httpResponse.code());
//		assertNotNull(httpResponse.body());
//	}
}

class TestController {

	public TestController() {
		ApiController apiController = new ApiController(null, null);
		App.sparkInit(apiController, 8080, new JsonTransformer(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()));
	}

}
