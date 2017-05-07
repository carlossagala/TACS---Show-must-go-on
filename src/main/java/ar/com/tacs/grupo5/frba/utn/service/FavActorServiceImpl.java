package ar.com.tacs.grupo5.frba.utn.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.FavActorDao;
import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.FavActorMapper;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.PagedResponse;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Component
@Transactional
public class FavActorServiceImpl implements FavActorService {
	
	private FavActorDao favActorDao;
	private FavActorMapper favActorMapper;
	private UserDao userDao;
	private UserMapper userMapper;

	@Autowired
	public FavActorServiceImpl(FavActorDao favActorDao, FavActorMapper favActorMapper, UserDao userDao,UserMapper userMapper) {
		this.favActorDao = favActorDao;
		this.favActorMapper = favActorMapper;
		this.userDao = userDao;
		this.userMapper = userMapper;
	}

	@Override
	public void addFavActor(User user, String idActor) {
		UserEntity userEntity = userDao.getUserById(user.getId());
		favActorDao.addFavActor(userEntity, idActor);
	}

	@Override
	public void deleteFavActor(User user, String idActor) {
		UserEntity userEntity = userDao.getUserById(user.getId());
		favActorDao.deleteFavActor(userEntity, idActor);
	}

	@Override
	public void getFavActors(String id, int page, PagedResponse resp) {
		UserEntity userEntity = userDao.getUserById(id);
		Page<FavActorEntity> favActors = favActorDao.getFavActors(userEntity,page-1);
		if(favActors==null){
			resp.setTotalResults(0L);
			return;
		}
		resp.setData(favActors.getContent().stream().map(FavActorEntity::getActorId).collect(Collectors.toList()));
		resp.setPage(page);
		resp.setTotalPages(favActors.getTotalPages());
		resp.setTotalResults(favActors.getTotalElements());
		return ;
	}
	
	@Override
	public List<String> getFavActorsId(String userId, int page) {
		UserEntity userEntity = userDao.getUserById(userId);
		Page<FavActorEntity> favActorsPage = favActorDao.getFavActors(userEntity,page-1);
		
		List<String> ids = favActorsPage.getContent().stream().map(FavActorEntity::getActorId).collect(Collectors.toList());
		return ids;
	}

	@Override
	public Long countByUser(User user) {
		return favActorDao.countByUser(userMapper.dtoToEntity(user));
	}


}
