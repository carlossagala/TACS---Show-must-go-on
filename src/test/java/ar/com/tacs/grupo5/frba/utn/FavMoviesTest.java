package ar.com.tacs.grupo5.frba.utn;

import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.tacs.grupo5.frba.utn.dao.FavMoviesDao;
import ar.com.tacs.grupo5.frba.utn.dao.MovieDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.User;
import ar.com.tacs.grupo5.frba.utn.service.FavMoviesService;
import ar.com.tacs.grupo5.frba.utn.service.FavMoviesServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavMoviesTest {
	
	@SpyBean
	private FavMoviesDao favMoviesDao;
	@SpyBean
	private UserDao userDao;
	@SpyBean
	private MovieDao movieDao;
	@SpyBean
	private UserMapper userMapper;
	@SpyBean
	private MovieMapper movieMapper;
	@SpyBean
	private FavMoviesService favMoviesService;
	
	@Test
	public void testSaveFavMovies()
	{
		User savedUser = this.userDao.saveUser(new User("userrrr1", "userrrr1", "user"));
		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Lista", savedUser.getId()));
		Assert.assertNotNull(savedFavMovie);
		Assert.assertTrue(savedFavMovie.getName().equals("Mi Lista"));
	}
	
	@Test
	public void testGetFavMovies()
	{
		User savedUser = this.userDao.saveUser(new User("userrrr2", "userrrr2", "user"));
		FavMovie savedFavMovie1 = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
		FavMovie savedFavMovie2 = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Segunda Lista", savedUser.getId()));
		savedUser.getFavMovies().add(savedFavMovie1);
		savedUser.getFavMovies().add(savedFavMovie2);
		savedUser = this.userDao.saveUser(savedUser);
		Assert.assertTrue(savedUser.getFavMovies().size()>0);
	}
	
	@Test
	public void testUpdateFavMovies()
	{
		User savedUser = this.userDao.saveUser(new User("userrrr3", "userrrr3", "user"));
		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
		savedUser.getFavMovies().add(savedFavMovie); 
		savedUser = this.userDao.saveUser(savedUser);
		
		try
		{
			savedFavMovie = favMoviesService.updateFavMovie("Mi Segunda Lista", savedFavMovie.getId());
		}
		catch(ResourceNotFound e)
		{
			fail();
		}
				
		Assert.assertTrue(this.favMoviesDao.getFavMovie(savedFavMovie.getId()).getName() == "Mi Segunda Lista");
	}
	
	@Test
	public void testDeleteFavMovies()
	{
		User savedUser = this.userDao.saveUser(new User("userrrr4", "userrrr4", "user"));
		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
		savedUser.getFavMovies().add(savedFavMovie); 
		savedUser = this.userDao.saveUser(savedUser);
		
		try
		{
			favMoviesService.deleteFavMovie(savedFavMovie.getId());
		}
		catch(ResourceNotFound e)
		{
			fail();
		}
		
		Assert.assertNull(this.favMoviesDao.getFavMovie(savedFavMovie.getId()));
	}
	
	@Test(expected=ResourceNotFound.class)
	public void testDeleteFavMoviesNotFound() throws ResourceNotFound {
		User savedUser = this.userDao.saveUser(new User("userrrr4", "userrrr4", "user"));
		FavMovie savedFavMovie = this.favMoviesDao.saveFavMovie(new FavMovie("Mi Primera Lista", savedUser.getId()));
		savedUser.getFavMovies().add(savedFavMovie); 
		savedUser = this.userDao.saveUser(savedUser);
		
		try
		{
			favMoviesService.deleteFavMovie(savedFavMovie.getId());
		}
		catch(ResourceNotFound e)
		{
			fail();
		}
				
		favMoviesService.deleteFavMovie(savedFavMovie.getId());
	}
}
