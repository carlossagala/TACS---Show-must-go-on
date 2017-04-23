package ar.com.tacs.grupo5.frba.utn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
//import com.despegar.http.client.GetMethod;
//import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;

import spark.servlet.SparkApplication;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@Configuration
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })

public class IntegrationTest
{

	public static class TestContollerTestSparkApplication implements SparkApplication {
		@Override
		public void init() {
			App.main(null);
		}
	}

	@ClassRule
	public static SparkServer<TestContollerTestSparkApplication> testServer = new SparkServer<>(
			IntegrationTest.TestContollerTestSparkApplication.class, 8080);

	@Test
	public void testUsers() throws Exception {
		GetMethod get = testServer.get("/api/users/", false);
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
		assertNotNull(httpResponse.body());
		get.addHeader("api_key", getApiKey("admin","admin1"));
		httpResponse = testServer.execute(get);
		assertEquals(200, httpResponse.code());
		assertNotNull(httpResponse.body());
		String s = new String(httpResponse.body());
		assertNotNull(s);

	}
	public String getApiKey(String userName,String password){
		return "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE0OTI5NjcyODI4MzksImV4cCI6MTQ5MzU3MjA4Mn0.bZh02PnZrfAs1bfhg7QL3P5tr86_bYzX-qC2EexlL_PsZJgeak54QuNAfeAJfuB0TMppzuoBm6-RYkau_74YeQ";
	}
}
