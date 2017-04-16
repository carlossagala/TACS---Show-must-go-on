package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavActorRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;
import ar.com.tacs.grupo5.frba.utn.mapper.UserMapper;
import ar.com.tacs.grupo5.frba.utn.models.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	private FavActorRepository favActorRepository;
	
	@Autowired
	public UserDaoImpl(FavActorRepository favActorRepository) {
		super();
		this.favActorRepository = favActorRepository;
	}

	@Override
	public List<User> getAllUsers() {
		List<UserEntity> usersFound = userRepository.findAll();
		List<User> users = new ArrayList<>();
		for (UserEntity userEnt : usersFound) {
			users.add(userMapper.entityToDto(userEnt));
		}
		return users;
	}

	@Override
	public User getUserById(String id) {
		return userMapper.entityToDto(userRepository.findOne(id));
	}
	
	
	@Override
	@Transactional
	public User saveUser(User user) {
		UserEntity userEntity = userMapper.dtoToEntity(user);
		return userMapper.entityToDto(userRepository.saveAndFlush(userEntity));
	}

	@Override
	public User findByUserNameAndPass(String userName, String pass) {
		return userMapper.entityToDto(userRepository.findByUserNameAndPass(userName,pass));
	}

	@Override
	public User findByUserName(String userName) {
		return userMapper.entityToDto(userRepository.findByUserName(userName));
	}

	@Override
	@Transactional
	public List<String> getFavActors(String userId) {
		if(userId==null){
			return new ArrayList<>();
		}
		UserEntity userEntity = userRepository.findOne(userId);
		if(userEntity==null){
			return new ArrayList<>();
		}
		Set<FavActorEntity> favActors= userEntity.getFavActors();
		if(favActors==null){
			return new ArrayList<>();
		}
		return favActors.stream().map(FavActorEntity::getActorId).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void addFavActor(String idUser,String idActor) {
		if(idActor==null){
			return;
		}
		UserEntity userEntity = userRepository.findOne(idUser);
		Set<FavActorEntity> favActors= userEntity.getFavActors();
		if(favActors==null){
			favActors = new HashSet<>();
		}
		boolean idExists = favActors.stream().anyMatch(x->x.getActorId().equals(idActor));
		if(!idExists){
			favActors.add(new FavActorEntity(userEntity,idActor));
			userRepository.save(userEntity);
		}
	}

	@Override
	@Transactional
	public void deleteFavActor(String idUser, String idActor) {
		UserEntity userEntity = userRepository.findOne(idUser);
		Set<FavActorEntity> favActors= userEntity.getFavActors();
		if(favActors==null){
			favActors = new HashSet<>();
		}
		FavActorEntity favActor = favActors.stream().filter(x->x.getActorId().equals(idActor)).findAny().orElse(null);
		if(favActor!=null){
			favActors.remove(favActor);
			favActorRepository.delete(favActor);
			userRepository.save(userEntity);
		}
	}
	

}
