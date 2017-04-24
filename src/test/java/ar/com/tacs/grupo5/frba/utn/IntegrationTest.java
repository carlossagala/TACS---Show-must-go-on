package ar.com.tacs.grupo5.frba.utn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

import org.junit.ClassRule;
import org.junit.Test;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
//import com.despegar.http.client.GetMethod;
//import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.servlet.SparkApplication;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@Configuration
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })

public class IntegrationTest
{
	Gson gson = gson();
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
	public String getApiKey(String userName,String password) throws HttpClientException{
		PostMethod post = testServer.post("/api/user/login/", "{\"user_name\":\"admin\",\"password\":\"admin1\"}", false);
		HttpResponse httpResponse = testServer.execute(post);
		String json = new String(httpResponse.body());
		@SuppressWarnings("rawtypes")
		HashMap mapa = gson.fromJson(json, HashMap.class);
		return mapa.get("token").toString();
	}
	

	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}
}
