package ar.com.tacs.grupo5.frba.utn.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import ar.com.tacs.grupo5.frba.utn.dao.FavActorDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.impl.FavActorDaoImpl;
import ar.com.tacs.grupo5.frba.utn.dao.impl.UserDaoImpl;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.FavActorMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;

public class FavActorServiceTest {
	
	private FavActorDao favActorDao = null;
	private UserDao userDao = null;
	private UserMapper userMapper = null;
	private FavActorMapper favActorMapper = null;
	
	private FavActorService favActorService = null;
	
	private UserEntity userEntAdmin = null;
	private User userAdmin = null;
	
	private void prepareContext()
	{
		favActorDao = Mockito.mock(FavActorDaoImpl.class);
		userDao = Mockito.mock(UserDaoImpl.class);
		userMapper = Mockito.mock(UserMapper.class);
		favActorMapper = Mockito.mock(FavActorMapper.class);
		favActorService = new FavActorServiceImpl(favActorDao, favActorMapper, userDao, userMapper);
		
		userEntAdmin = new UserEntity("1", "admin", "admin", "admin");
		userAdmin = new User("1", "admin");
	}
	
	@Test
	public void testAddFavActor()
	{
		prepareContext();
		Mockito.when(userDao.getUserById("1")).thenReturn(userEntAdmin);
		favActorService.addFavActor(userAdmin, "1000");
	}
	
	@Test
	public void testDeleteFavActor()
	{
		prepareContext();
		Mockito.when(userDao.getUserById("1")).thenReturn(userEntAdmin);
		favActorService.deleteFavActor(userAdmin, "1000");
	}
	
	@Test
	public void testGetFavActors()
	{
		prepareContext();
		List<FavActorEntity> listOfFavActorEntities = new ArrayList<>();
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1000"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1001"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1002"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1003"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1004"));
		Page<FavActorEntity> pageOfFavActorEntities = new PageImpl<>(listOfFavActorEntities);
		Mockito.when(userDao.getUserById("1")).thenReturn(userEntAdmin);
		Mockito.when(favActorDao.getFavActors(userEntAdmin,0)).thenReturn(pageOfFavActorEntities);
		PagedResponse resp = new PagedResponse();
		favActorService.getFavActors("1", 1, resp);
		Assert.assertNotNull(resp.getData());
	}
	
	@Test
	public void testGetFavActorsId()
	{
		prepareContext();
		List<FavActorEntity> listOfFavActorEntities = new ArrayList<>();
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1000"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1001"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1002"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1003"));
		listOfFavActorEntities.add(new FavActorEntity(userEntAdmin, "1004"));
		Page<FavActorEntity> pageOfFavActorEntities = new PageImpl<>(listOfFavActorEntities);
		Mockito.when(userDao.getUserById("1")).thenReturn(userEntAdmin);
		Mockito.when(favActorDao.getFavActors(userEntAdmin,0)).thenReturn(pageOfFavActorEntities);
		List<String> favActorsId = favActorService.getFavActorsId("1", 1);
		Assert.assertNotNull(favActorsId);
		Assert.assertTrue(favActorsId.size() == 5);
	}
	
	@Test
	public void testCountByUser()
	{
		prepareContext();
		Mockito.when(userMapper.dtoToEntity(userAdmin)).thenReturn(userEntAdmin);
		Mockito.when(favActorDao.countByUser(userEntAdmin)).thenReturn(Long.valueOf(3));
		Long count = favActorService.countByUser(userAdmin);
		Assert.assertNotNull(count);
		Assert.assertTrue(count == 3);
	}
	

}
