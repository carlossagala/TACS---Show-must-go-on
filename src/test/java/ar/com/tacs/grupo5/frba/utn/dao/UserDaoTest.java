package ar.com.tacs.grupo5.frba.utn.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.impl.UserDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoTestConfiguration.class})
@PropertySource("classpath:application.properties")
public class UserDaoTest extends AbstractDaoTest{
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	
	private UserDaoImpl userDao;
	
	@Autowired
	private UserRepository userRepository;
	
	@Before
	public void beforeEach()
	{
		MockitoAnnotations.initMocks(getClass());
		insertInitialData();
		userDao = new UserDaoImpl(userRepository);
		userDao.setPageSize(20);
	}
	
	@After
	public void afterEach()
	{
		deleteAllData();
	}
	
	@Test
	public void testRegisterUser()
	{
		UserEntity userToRegister = new UserEntity("3","user3","user3","user");
		userDao.saveUser(userToRegister);
		UserEntity found = userDao.findByUserName("user3");
		Assert.assertNotNull(found);
	}
	
	@Test
	public void testGetAllUsers()
	{
		List<UserEntity> allUsers = userDao.getAllUsers();
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.size()==2);
	}
	
	@Test
	public void testGetAllUsersWithPage()
	{
		Page<UserEntity> allUsers = userDao.getAllUsersWithPage(0);
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.getContent().size()==2);
	}
	
	@Test
	public void testGetUnexistingUserByUserName()
	{
		UserEntity found = userDao.findByUserName("AnyUser");
		Assert.assertNull(found);
	}
	
	@Test
	public void testGetUnexistingUserById()
	{
		UserEntity found = userDao.getUserById("300");
		Assert.assertNull(found);
	}
}
