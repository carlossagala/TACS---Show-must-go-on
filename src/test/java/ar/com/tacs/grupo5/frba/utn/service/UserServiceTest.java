package ar.com.tacs.grupo5.frba.utn.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.impl.UserDaoImpl;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;

public class UserServiceTest {
	
	private UserDao userDao = Mockito.mock(UserDaoImpl.class);
	private UserMapper userMapper = Mockito.mock(UserMapper.class);
	
	UserService userService = null;
	
	private UserEntity userEntAdmin = null;
	private UserEntity userEntTest1 = null;
	private UserEntity userEntTest2 = null;
	
	private User userAdmin = null;
	private User userTest1 = null;
	private User userTest2 = null;
	
	private void prepareContext() {
		userEntAdmin = new UserEntity("1", "admin", "admin1", "admin");
		userAdmin = new User("1", "admin");
		userEntTest1 = new UserEntity("2", "test1", "test1", "user");
		userTest1 = new User("2", "test1");
		userEntTest2 = new UserEntity("3", "test2", "test2", "user");
		userTest2 = new User("2", "test2");
		userService = new UserServiceImpl(userDao, userMapper);
	}
	
	@Test
	public void testGetAllUsers()
	{
		prepareContext();
		List<UserEntity> userEntities = 
				Arrays.asList(
						userEntAdmin,
						userEntTest1,
						userEntTest2);
		Mockito.when(userDao.getAllUsers()).thenReturn(userEntities);
		Mockito.when(userMapper.entityToDto(userEntAdmin)).thenReturn(userAdmin);
		Mockito.when(userMapper.entityToDto(userEntTest1)).thenReturn(userTest1);
		Mockito.when(userMapper.entityToDto(userEntTest2)).thenReturn(userTest2);
		List<User> allUsers = userService.getAllUsers();
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.size() == 3);
	}

	@Test
	public void testGetAllUsersWithPage()
	{
		prepareContext();
		List<UserEntity> userEntities = 
				Arrays.asList(
						userEntAdmin,
						userEntTest1,
						userEntTest2);
		Page<UserEntity> usersPage = new PageImpl<>(userEntities);
		Mockito.when(userDao.getAllUsersWithPage(0)).thenReturn(usersPage);
		Mockito.when(userMapper.entityToDto(userEntAdmin)).thenReturn(userAdmin);
		Mockito.when(userMapper.entityToDto(userEntTest1)).thenReturn(userTest1);
		Mockito.when(userMapper.entityToDto(userEntTest2)).thenReturn(userTest2);
		Page<User> allUsers = userService.getAllUsersWithPage(1);
		Assert.assertNotNull(allUsers);
		Assert.assertTrue(allUsers.getContent().size() == 3);
		Assert.assertTrue(allUsers.getTotalPages()==1);
		Assert.assertTrue(allUsers.getTotalElements()==3);
	}
	
	@Test
	public void testGetUserById()
	{
		prepareContext();
		Mockito.when(userDao.getUserById("1")).thenReturn(userEntAdmin);
		Mockito.when(userDao.getUserById("444")).thenReturn(null);
		Mockito.when(userMapper.entityToDto(userEntAdmin)).thenReturn(userAdmin);
		User userFound = userService.getUserById("1");
		Assert.assertNotNull(userFound);
		Assert.assertTrue(userFound.getUserName().equalsIgnoreCase("admin"));
		userFound = userService.getUserById("444");
		Assert.assertNull(userFound);
	}
	
	@Test
	public void testSaveUser()
	{
		prepareContext();
		UserEntity userEntTest3 = new UserEntity("4", "userTest3", "pass", "user");
		User userTest3 = new User("4", "userTest3");
		Mockito.when(userDao.saveUser(userEntTest3)).thenReturn(userEntTest3);
		Mockito.when(userMapper.dtoToEntity(userTest3)).thenReturn(userEntTest3);
		Mockito.when(userMapper.entityToDto(userEntTest3)).thenReturn(userTest3);
		User savedUser = userService.saveUser(userTest3);
		Assert.assertNotNull(savedUser);
		Assert.assertTrue(savedUser.getUserName().equalsIgnoreCase("userTest3"));
	}
	
	@Test
	public void testFindByUserNameAndPass()
	{
		prepareContext();
		Mockito.when(userDao.findByUserNameAndPass("test1","test1")).thenReturn(userEntTest1);
		Mockito.when(userDao.findByUserNameAndPass("test33","test33")).thenReturn(null);
		Mockito.when(userMapper.entityToDto(userEntTest1)).thenReturn(userTest1);
		User userFound = userService.findByUserNameAndPass("test1", "test1");
		Assert.assertNotNull(userFound);
		Assert.assertTrue(userFound.getUserName().equalsIgnoreCase("test1"));
		userFound = userService.findByUserNameAndPass("test33", "test33");
		Assert.assertNull(userFound);
	}
	
	@Test
	public void testFindByUserName()
	{
		prepareContext();
		Mockito.when(userDao.findByUserName("test1")).thenReturn(userEntTest1);
		Mockito.when(userDao.findByUserName("test33")).thenReturn(null);
		Mockito.when(userMapper.entityToDto(userEntTest1)).thenReturn(userTest1);
		User userFound = userService.findByUserName("test1");
		Assert.assertNotNull(userFound);
		Assert.assertTrue(userFound.getUserName().equalsIgnoreCase("test1"));
		userFound = userService.findByUserName("test33");
		Assert.assertNull(userFound);
	}


}
