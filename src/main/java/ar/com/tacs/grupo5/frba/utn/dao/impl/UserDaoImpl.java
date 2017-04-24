package ar.com.tacs.grupo5.frba.utn.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.UserDao;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavActorRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.FavMovieRepository;
import ar.com.tacs.grupo5.frba.utn.dao.repository.UserRepository;
import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	
	@Value("${page.size}")
	private int pageSize;
	
	private UserRepository userRepository;
	
//	@Autowired
//	private FavMovieRepository favMoviesRepository;
//	
//	private FavActorRepository favActorRepository;
	
	@Autowired
	public UserDaoImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		List<UserEntity> usersFound = userRepository.findAll();
		return usersFound;
	}

	@Override
	public UserEntity getUserById(String id) {
		return userRepository.findOne(id);
	}
	
	
	@Override
	@Transactional
	public UserEntity saveUser(UserEntity user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserEntity findByUserNameAndPass(String userName, String pass) {
		return userRepository.findByUserNameAndPass(userName,pass);
	}

	@Override
	public UserEntity findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

//	@Override
//	@Transactional
//	public Page<FavActorEntity> getFavActors(String userId,int page) {
//		UserEntity userEntity = userRepository.findOne(userId);
//		if(userEntity==null){
//			return null;
//		}
//		return favActorRepository.findByUserEntity(userEntity, new PageRequest(page, pageSize));
//	}

//	@Override
//	@Transactional
//	public void addFavActor(String idUser,String idActor) {
//		if(idActor==null){
//			return;
//		}
//		UserEntity userEntity = userRepository.findOne(idUser);
//		Set<FavActorEntity> favActors= userEntity.getFavActors();
//		if(favActors==null){
//			favActors = new HashSet<>();
//		}
//		boolean idExists = favActors.stream().anyMatch(x->x.getActorId().equals(idActor));
//		if(!idExists){
//			favActors.add(new FavActorEntity(userEntity,idActor));
//			userRepository.save(userEntity);
//		}
//	}

//	@Override
//	@Transactional
//	public void deleteFavActor(String idUser, String idActor) {
//		UserEntity userEntity = userRepository.findOne(idUser);
//		Set<FavActorEntity> favActors= userEntity.getFavActors();
//		if(favActors==null){
//			favActors = new HashSet<>();
//		}
//		FavActorEntity favActor = favActors.stream().filter(x->x.getActorId().equals(idActor)).findAny().orElse(null);
//		if(favActor!=null){
//			favActors.remove(favActor);
//			favActorRepository.delete(favActor);
//			userRepository.save(userEntity);
//		}
//	}
	
//	@Override
//	@Transactional
//	public boolean deleteFavMovies(String idUser, String idFavMovies) {
//		UserEntity userEntity = userRepository.findOne(idUser);
//		Set<FavMovieEntity> favMovies= userEntity.getFavMovies();
//		
//		FavMovieEntity favMov = favMovies.stream().filter(x->x.getId().equals(idFavMovies)).findAny().orElse(null);
//		if(favMov!=null){
//			favMovies.remove(favMov);
//			favMoviesRepository.delete(favMov);
//			userRepository.save(userEntity);
//			
//			return true;
//		}
//		
//		return false;
//	}

}
