package ar.com.tacs.grupo5.frba.utn;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
	
	@SpyBean
	private UserDao userDao;
	
	
	@Test
	public void testRegisterUser()
	{
		User created = new User("TRU1","TRU1","user");
		created = this.userDao.saveUser(created);
		User found = this.userDao.findByUserName("TRU1");
		Assert.assertNotNull(found);
		Assert.assertTrue(created.getId().equals(found.getId()));
	}
	
	@Test
	public void testGetAllUsers()
	{
		this.userDao.saveUser(new User("userTGAU1", "userTGAU1", "user"));
		this.userDao.saveUser(new User("userTGAU2", "userTGAU2", "admin"));
		this.userDao.saveUser(new User("userTGAU3", "userTGAU3", "user"));
		this.userDao.saveUser(new User("userTGAU4", "userTGAU4", "user"));
		//en este punto existen los 4 users creados aca mas los dos creados por script al iniciar el contexto
		List<User> allUsers = this.userDao.getAllUsers();
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.size()==6);
	}
	
	
}
