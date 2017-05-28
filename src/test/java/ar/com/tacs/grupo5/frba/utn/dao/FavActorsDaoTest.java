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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavActorDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.impl.UserDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavActorRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.exceptions.ResourceNotFound;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoTestConfiguration.class})
@PropertySource("classpath:application.properties")
public class FavActorsDaoTest extends AbstractDaoTest{
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;

	private FavActorDaoImpl favActorDao;
	
	private UserDaoImpl userDao;
	
	private UserEntity user;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FavActorRepository favActorRepository;
	
	@Before
	public void beforeEach()
	{
		MockitoAnnotations.initMocks(getClass());
		insertInitialData();
		userDao = new UserDaoImpl(userRepository,20);
		favActorDao = new FavActorDaoImpl(favActorRepository);
		user = this.userDao.getUserById("2");
	}
	
	@After
	public void afterEach()
	{
		deleteAllData();
	}

	@Test
	public void testSaveFavActor() {
		this.favActorDao.addFavActor(user, "2");
		Assert.assertTrue(favActorDao.countByUser(user).intValue() == 23);
	}

	@Test
	public void testGetFavActors() {
		Assert.assertTrue(favActorDao.countByUser(user).intValue() == 22);
	}

	@Test
	public void testDeleteFavActor() {
		try {
			favActorDao.deleteFavActor(user, "10");
		} catch (ResourceNotFound e) {
			fail();
		}

		Assert.assertTrue(favActorDao.countByUser(user).intValue() == 21);
	}
	
	@Test
	public void testDeleteFavActorNotFound() {
		try {
			favActorDao.deleteFavActor(user, "37");
			fail();
		} catch (ResourceNotFound e) {
		}
	}
}
