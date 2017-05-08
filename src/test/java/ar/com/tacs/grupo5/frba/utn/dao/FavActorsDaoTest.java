package ar.com.tacs.grupo5.frba.utn.dao;

import static org.junit.Assert.fail;

//import org.hsqldb.util.DatabaseManagerSwing;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="ar.com.tacs.grupo5.frba.utn")
public class FavActorsDaoTest {
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	@SpyBean
	private FavActorDao favActorDao;
	@SpyBean
	private UserDao userDao;
	
	private UserEntity user;
	
	@Before
	public void Before()
	{
		user = this.userDao.getUserById("2");
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testSaveFavActor() {
		this.favActorDao.addFavActor(user, "2");

		Assert.assertEquals((long)19, (long)favActorDao.countByUser(user));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testGetFavActors() {
		Assert.assertEquals((long)18,(long)favActorDao.countByUser(user));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFavActor() {
		try {
			favActorDao.deleteFavActor(user, "10");
		} catch (ResourceNotFoundException e) {
			fail();
		}

		Assert.assertEquals((long)17,(long)favActorDao.countByUser(user));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFavActorNotFound() {
		try {
			favActorDao.deleteFavActor(user, "37");
			fail();
		} catch (ResourceNotFoundException e) {
		}
	}
}
