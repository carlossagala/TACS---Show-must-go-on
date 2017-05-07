package ar.com.tacs.grupo5.frba.utn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import com.despegar.http.client.DeleteMethod;
import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpClientException;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.http.client.PutMethod;
//import com.despegar.http.client.GetMethod;
//import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.servlet.SparkApplication;
import spark.utils.IOUtils;

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
	public void testGetAdminIntersectFailNotAuthorized() throws Exception {
		GetMethod get = testServer.get("/api/users/bac25bb4c3dd/intersection/838987df153f/?page=1", false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testUserFavmoviesIntersectFailNotAuthorized() throws Exception {
		GetMethod get = testServer.get("/api/favmovies/bac25bb4c3dd/intersection/838987df153f/?page=1", false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testGetUserDetailFailNotAuthorized() throws Exception {
		GetMethod get = testServer.get("/api/users/1/", false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testUserRankingFailNotAuthorized() throws Exception {
		GetMethod get = testServer.get("/api/users/ranking/actors/", false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testUserFavActorFailNotAuthorized() throws Exception {
		GetMethod get = testServer.get("/api/users/1/favactors/", false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testRegister() throws Exception {
		genericPost("/api/user/", getFile("requestLoginTemplate.json").replace("{{user_name}}", "juan")
				.replace("{{password}}", "juan123"));
		assertNotNull(getApiKey("juan","juan123"));
	}
	@Test
	public void testLoginOK() throws Exception {
		PostMethod post = testServer.post("/api/user/login/", 
				getFile("requestLoginTemplate.json").replace("{{user_name}}", "test")
				.replace("{{password}}", "test1"), false);
		HttpResponse httpResponse = testServer.execute(post);
		assertEquals(200, httpResponse.code());
		JSONAssert.assertEquals(getFile("responseLogin.json"), new String(httpResponse.body()), false);

	}
	@Test
	public void testLoginFailUserInexistente() throws Exception {
		PostMethod post = testServer.post("/api/user/login/", 
				getFile("requestLoginTemplate.json").replace("{{user_name}}", "userInexistente")
				.replace("{{password}}", "asd321"), false);
		HttpResponse httpResponse = testServer.execute(post);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testLoginFailWrongPass() throws Exception {
		PostMethod post = testServer.post("/api/user/login/", 
				getFile("requestLoginTemplate.json").replace("{{user_name}}", "test1")
				.replace("{{password}}", "contraseniamal"), false);
		HttpResponse httpResponse = testServer.execute(post);
		assertEquals(401, httpResponse.code());
	}
	@Test
	public void testRegisterFail() throws Exception {
		
		PostMethod post = testServer.post("/api/user/", 
				getFile("requestLoginTemplate.json").replace("{{user_name}}", "admin")
				.replace("{{password}}", "asdasd"), false);

		HttpResponse httpResponse = testServer.execute(post);
		assertEquals(400, httpResponse.code());	
	}
	@Test
	public void testDeleteFavMovies() throws Exception {
		genericDelete("/api/favmovies/db67970a4304/");
	}
	@Test
	public void testPutFavMovies() throws Exception {
		Map<String,String> requestMap = new HashMap<>();
		requestMap.put("new_title", "Nueva lista");
		genericPut("/api/favmovies/db67970a4304/", gson.toJson(requestMap));
	}
	@Test
	public void testGetFavMovies() throws Exception {
		genericGetTest("/api/favmovies/bac25bb4c3dd/", "responseGetFavMovie.json");
	}
	@Test
	public void testPostMovie() throws Exception {
		Map<String,String> requestMap = new HashMap<>();
		requestMap.put("id", "9");
		genericPost("/api/favmovies/db67970a4304/movies/", gson.toJson(requestMap));
	}
	@Test
	public void testDeleteMovie() throws Exception {
		genericDelete("/api/favmovies/5b377bf69c18/movies/3/");
	}
	@Test
	public void testCrearFavMovie() throws Exception {
		Map<String,String> requestMap = new HashMap<>();
		requestMap.put("name", "lista1");
		genericPost("/api/favmovies/",gson.toJson(requestMap));
	}
	@Test
	public void testCrearFavActor() throws Exception {
		Map<String,String> requestMap = new HashMap<>();
		requestMap.put("id", "1");
		genericPost("/api/favactors/",gson.toJson(requestMap));
	}
	private void genericPost(String path, String request)throws Exception{
		PostMethod post = testServer.post(path, 
				request, false);
		post.addHeader("api_key", getApiKey("admin","admin1"));

		HttpResponse httpResponse = testServer.execute(post);
		assertEquals(201, httpResponse.code());	
	}
	private void genericPut(String path, String request)throws Exception{
		PutMethod put = testServer.put(path, 
				request, false);
		put.addHeader("api_key", getApiKey("admin","admin1"));

		HttpResponse httpResponse = testServer.execute(put);
		assertEquals(200, httpResponse.code());	
	}
	@Test
	public void testGetFavActor() throws Exception {
		GetMethod get = testServer.get("/api/favactors/", false);
		get.addHeader("api_key", getApiKey("admin","admin1"));

		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(404, httpResponse.code());
		GetMethod getTest = testServer.get("/api/favactors/", false);

		getTest.addHeader("api_key", getApiKey("test","test1"));
		httpResponse = testServer.execute(getTest);

		assertEquals(200, httpResponse.code());
		JSONAssert.assertEquals(getFile("responseGetFavActors.json"), new String(httpResponse.body()), false);

	}
	
	@Test
	public void testGetActor() throws Exception {
		genericGetTest("/api/actor/1/","responseActor.json");
	}
	@Test
	public void testSearchMovie() throws Exception {
		genericGetTest("/api/search/movie/Keanu%20Reeves/","responseSearchMovie.json");
	}
	@Test
	public void testSearchActor() throws Exception {
		genericGetTest("/api/search/actor/Keanu%20Reeves/","responseSearchActor.json");
	}
	@Test
	public void testSearchFull() throws Exception {
		genericGetTest("/api/search/full/Keanu%20Reeves/","responseSearchFull.json");
	}
	@Test
	public void testGetMovie() throws Exception {
		genericGetTest("/api/movie/2/","responseMovie.json");
	}
	private void genericGetTest(String path,String responseFile)throws Exception {
		GetMethod get = testServer.get(path, false);
		get.addHeader("api_key", getApiKey("test","test1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(200, httpResponse.code());
		JSONAssert.assertEquals(getFile(responseFile), new String(httpResponse.body()), false);
		
	}
	private void genericAdminGetTest(String path,String responseFile)throws Exception {
		GetMethod get = testServer.get(path, false);
		get.addHeader("api_key", getApiKey("admin","admin1"));
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(200, httpResponse.code());
		JSONAssert.assertEquals(getFile(responseFile), new String(httpResponse.body()), false);
		
	}
	public void genericDelete(String path) throws Exception {
		DeleteMethod delete = testServer.delete(path, 
				false);
		delete.addHeader("api_key", getApiKey("test","test1"));

		HttpResponse httpResponse = testServer.execute(delete);
		assertEquals(200, httpResponse.code());
	}
	@Test
	public void testDeleteFavActor() throws Exception {
		genericDelete("/api/favactors/1/");
	}
	
	@Test
	public void testGetUser() throws Exception {
		genericAdminGetTest("/api/users/1/","responseGetUserByID.json");
	}
	@Test
	public void getUserFavMovies() throws Exception {
		genericAdminGetTest("/api/users/1/favmovies/?page=1","responseGetUserFavMovies.json");
	}
	@Test
	public void getListIntersection1() throws Exception {
		genericAdminGetTest("/api/users/bac25bb4c3dd/intersection/838987df153f/?page=1","responseIntersectionFavMovies.json");
	}
	@Test
	public void getListIntersection2() throws Exception {
		genericAdminGetTest("/api/favmovies/bac25bb4c3dd/intersection/838987df153f/?page=1","responseIntersectionFavMovies.json");
	}
	@Test
	public void getAdminUserFavActors() throws Exception {
		genericAdminGetTest("/api/users/2/favactors/?page=1","resposeUserFavActors.json");
	}
	
	@Test
	public void testGetUsers() throws Exception {
		GetMethod get = testServer.get("/api/users/", false);
		HttpResponse httpResponse = testServer.execute(get);
		assertEquals(401, httpResponse.code());
		assertNotNull(httpResponse.body());
		get.addHeader("api_key", getApiKey("admin","admin1"));
		httpResponse = testServer.execute(get);
		assertEquals(200, httpResponse.code());
		assertNotNull(httpResponse.body());
		String responseJSON = new String(httpResponse.body());
		JSONAssert.assertEquals(getFile("responseGetUsers.json"), responseJSON, false);

	}
	  private String getFile(String fileName){

		  String result = "";

		  ClassLoader classLoader = getClass().getClassLoader();
		  try {
			result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
		  } catch (IOException e) {
			e.printStackTrace();
		  }

		  return result;

	  }
	public String getApiKey(String userName,String password) throws HttpClientException, FileNotFoundException, IOException{
		PostMethod post = testServer.post("/api/user/login/", 
				getFile("requestLoginTemplate.json").replace("{{user_name}}", userName)
				.replace("{{password}}", password), false);
		HttpResponse httpResponse = testServer.execute(post);
		if(httpResponse.code()!=200){
			return null;
		}
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
