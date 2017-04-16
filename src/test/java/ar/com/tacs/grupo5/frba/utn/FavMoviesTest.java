package ar.com.tacs.grupo5.frba.utn;

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
import ar.com.tacs.grupo5.frba.utn.mapper.MovieMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.FavMovie;
import ar.com.tacs.grupo5.frba.utn.models.User;

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

}
