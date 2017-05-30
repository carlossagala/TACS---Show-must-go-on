package ar.com.tacs.grupo5.frba.utn.dao;
import static org.junit.Assert.fail;

import org.junit.After;
//import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavActorDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavMoviesDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.impl.UserDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMoviesRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoTestConfiguration.class})
@PropertySource("classpath:application.properties")
public class FavMoviesDaoTest extends AbstractDaoTest{
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;

	private FavMoviesDaoImpl favMoviesDao;

	private UserDaoImpl userDao;
	
	private UserEntity user;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FavMoviesRepository favMoviesRepository;
	
	@Before
	public void Before()
	{
		MockitoAnnotations.initMocks(getClass());
		insertInitialData();
		userDao = new UserDaoImpl(userRepository);
		userDao.setPageSize(20);
		favMoviesDao = new FavMoviesDaoImpl(favMoviesRepository);
		user = userDao.getUserById("2");
	}
	
	@After
	public void afterEach()
	{
		deleteAllData();
	}

	@Test
	public void testSaveFavMovies() {
		FavMoviesEntity savedFavMovie = favMoviesDao.saveFavMovie(new FavMoviesEntity("Mi Lista Test", user));
		Assert.assertNotNull(savedFavMovie);
		Assert.assertTrue(savedFavMovie.getName().equals("Mi Lista Test"));
		Assert.assertEquals((long)5,(long)favMoviesDao.countByUser(user));
	}

	@Test
	public void testGetFavMovies() {
		FavMoviesEntity getFavMovie = favMoviesDao.getFavMovie("2");
		Assert.assertEquals(getFavMovie.getName(), "Mi segunda lista");
		Assert.assertEquals(getFavMovie.getUser().getUserName(), user.getUserName());
		Assert.assertTrue(getFavMovie.getId() == "2");
	}

	@Test
	public void testCountFavMovies() {
		Assert.assertEquals((long)4,(long)favMoviesDao.countByUser(user));
	}

	@Test
	public void testUpdateFavMovies() {
		FavMoviesEntity fav = favMoviesDao.getFavMovie("2");

		fav.setName("Nombre cambiado");
		
		fav = favMoviesDao.saveFavMovie(fav);

		FavMoviesEntity fav2 = favMoviesDao.getFavMovie("2");
		
		Assert.assertTrue(fav2.getName() == "Nombre cambiado");
	}

	@Test
	public void testDeleteFavMovies() {
		try {
			favMoviesDao.deleteFavMovies("2");
		} catch (ResourceNotFound e) {
			fail();
		}

		Assert.assertNull(this.favMoviesDao.getFavMovie("2"));
		Assert.assertEquals((long)3,(long)favMoviesDao.countByUser(user));
	}

	@Test
	public void testDeleteFavMoviesNotFound() {
		try {
			favMoviesDao.deleteFavMovies("37");
			fail();
		} catch (ResourceNotFound e) {
		}

     	Assert.assertEquals((long)4,(long)favMoviesDao.countByUser(user));
	}
}
