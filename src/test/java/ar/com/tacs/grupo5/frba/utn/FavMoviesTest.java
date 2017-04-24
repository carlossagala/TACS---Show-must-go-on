package ar.com.tacs.grupo5.frba.utn;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class FavMoviesTest {
	
//	@SpyBean
//	private FavMoviesDao favMoviesDao;
//	@SpyBean
//	private UserDao userDao;
//	@SpyBean
//	private MovieDao movieDao;
//	@SpyBean
//	private UserMapper userMapper;
//	@SpyBean
//	private MovieMapper movieMapper;
//	@SpyBean
//	private FavMoviesService favMoviesService;
//	@SpyBean
//	private MovieService movieService;
//	
//	@Test
//	public void testSaveFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userrrr1", "userrrr1", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Lista", savedUser.getId()));
//		Assert.assertNotNull(savedFavMovie);
//		Assert.assertTrue(savedFavMovie.getName().equals("Mi Lista"));
//	}
//	
//	@Test
//	public void testGetFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userrrr2", "userrrr2", "user"));
//		FavMovie savedFavMovie1 = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		FavMovie savedFavMovie2 = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Segunda Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie1);
//		savedUser.getFavMovies().add(savedFavMovie2);
//		savedUser = this.userDao.saveUser(savedUser);
//		Assert.assertTrue(savedUser.getFavMovies().size()>0);
//	}
//	
//	@Test
//	public void testUpdateFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userrrr3", "userrrr3", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie); 
//		savedUser = this.userDao.saveUser(savedUser);
//		
//		try
//		{
//			savedFavMovie = favMoviesService.updateFavMovie("Mi Segunda Lista", savedFavMovie.getId());
//		}
//		catch(ResourceNotFound e)
//		{
//			fail();
//		}
//				
//		Assert.assertTrue(this.favMoviesDao.getFavMovie(savedFavMovie.getId()).getName() == "Mi Segunda Lista");
//	}
//	
//	@Test
//	public void testDeleteFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userrrr4", "userrrr4", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie); 
//		savedUser = this.userDao.saveUser(savedUser);
//		
//		try
//		{
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//		}
//		catch(ResourceNotFound e)
//		{
//			fail();
//		}
//		
//		Assert.assertNull(this.favMoviesDao.getFavMovie(savedFavMovie.getId()));
//	}
//	
//	@Test
//	public void testDeleteFavMoviesNotFound(){
//		User savedUser = this.userDao.saveUser(new User("userrrr5", "userrrr5", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie); 
//		savedUser = this.userDao.saveUser(savedUser);
//		
//		try	{
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//		}
//		catch(ResourceNotFound e)
//		{
//			fail();
//		}
//		
//		//Intento borrar la entidad una segunda vez, en caso de que haya funcionado el primer delete, 
//		//debería tirar la excepción
//		try {
//			favMoviesService.deleteFavMovie(savedFavMovie.getId());
//			fail();
//		}
//		catch(ResourceNotFound e)
//		{
//		}
//	}
//	
//	@Test
//	public void testAddMovieToFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userAddMovie", "userAddMovie", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Una lista para dias de lluvia", savedUser.getId()));
//		String favMovieId = savedFavMovie.getId();
//		Movie addedMovie = null;
//		try {
//			addedMovie = movieService.addMovie(favMovieId, "123");
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//		Assert.assertNotNull(this.movieDao.getMovie("123"));
//	}
//	
//	@Test
//	public void testRemoveMovieFromFavMovies()
//	{
//		User savedUser = this.userDao.saveUser(new User("userRemoveMovie", "userRemoveMovie", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Una lista para dias de lluvia", savedUser.getId()));
//		String favMovieId = savedFavMovie.getId();
//		Movie addedMovie = null;
//		try {
//			addedMovie = movieService.addMovie(favMovieId, "123");
//			Assert.assertNotNull(this.movieDao.getMovie("123"));
//			this.movieService.removeMovie(favMovieId, "123");
//			Assert.assertNull(this.movieDao.getMovie("123"));
//		} catch (ResourceNotFound e) {
//			fail();
//		}
//		
//	}
//	
//	
//	@Test
//	public void testGetFavMoviesDetail()
//	{
//		FavMovie getFavMovie = new FavMovie();
//		
//		User savedUser = this.userDao.saveUser(new User("usrGetFavDetail", "usrGetFavDetail", "user"));
//		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
//		savedUser.getFavMovies().add(savedFavMovie); 
//		savedUser = this.userDao.saveUser(savedUser);
//		
//		try
//		{
//			getFavMovie = favMoviesService.getFavMovieDetail(savedFavMovie.getId());
//		}
//		catch(ResourceNotFound e)
//		{
//			fail();
//		}
//				
//		Assert.assertTrue(getFavMovie.getName() == "Mi Primera Lista");
//		Assert.assertTrue(getFavMovie.getUserId() == savedUser.getId());
//		Assert.assertTrue(getFavMovie.getId() == savedFavMovie.getId());
//	}
}
