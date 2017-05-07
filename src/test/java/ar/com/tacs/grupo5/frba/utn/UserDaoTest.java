package ar.com.tacs.grupo5.frba.utn;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.controllers.ApiController;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages="ar.com.tacs.grupo5.frba.utn")
public class UserDaoTest {
	// Mockeo Apicontroller para que no levante el servidor embebido de spark
	@MockBean
	ApiController apiController;
	
	@SpyBean
	private UserDao userDao;
	@SpyBean
	private UserMapper userMapper;
	
	@Test
	public void testRegisterUser()
	{
		User userDto = new User("user","user","user","lastAccess");
		UserEntity userToRegister = userMapper.dtoToEntity(userDto);
		this.userDao.saveUser(userToRegister);
		UserEntity found = this.userDao.findByUserName("user");
		Assert.assertNotNull(found);
	}
	
	@Test
	public void testGetAllUsers()
	{
		List<UserEntity> allUsers = this.userDao.getAllUsers();
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.size()==2);
	}
	
	@Test
	public void testGetUnexistingUserByUserName()
	{
		UserEntity found = this.userDao.findByUserName("AnyUser");
		Assert.assertNull(found);
	}
	
	@Test
	public void testGetUnexistingUserById()
	{
		UserEntity found = this.userDao.getUserById("300");
		Assert.assertNull(found);
	}
	
	
}
