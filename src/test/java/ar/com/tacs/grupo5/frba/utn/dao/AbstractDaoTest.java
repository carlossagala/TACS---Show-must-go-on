package ar.com.tacs.grupo5.frba.utn.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.tacs.grupo5.frba.utn.entity.FavActorEntity;
import ar.com.tacs.grupo5.frba.utn.entity.FavMoviesEntity;
import ar.com.tacs.grupo5.frba.utn.entity.MovieEntity;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoTestConfiguration.class })
public abstract class AbstractDaoTest {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public void insertInitialData()
	{
		UserEntity adminUser = new UserEntity("1", "admin", "$2a$12$2O2mnHcxLPtao/wVJ3vwo.pdbd13Z9WRuZ/79m8fi1eZJ2TbNPfUW", "admin");
		UserEntity testUser = new UserEntity("2", "test", "$2a$12$t6TyjGcTFwMn9pBk.2rwj.UfJu8lxtoaK8yo05X/lVet/Ja2OfOwq", "user");
		mongoTemplate.save(adminUser);
		mongoTemplate.save(testUser);
		mongoTemplate.save(new FavActorEntity(testUser, "1"));
		mongoTemplate.save(new FavActorEntity(testUser, "2"));
		mongoTemplate.save(new FavActorEntity(testUser, "3"));
		mongoTemplate.save(new FavActorEntity(testUser, "4"));
		mongoTemplate.save(new FavActorEntity(testUser, "5"));
		mongoTemplate.save(new FavActorEntity(testUser, "6"));
		mongoTemplate.save(new FavActorEntity(testUser, "7"));
		mongoTemplate.save(new FavActorEntity(testUser, "8"));
		mongoTemplate.save(new FavActorEntity(testUser, "9"));
		mongoTemplate.save(new FavActorEntity(testUser, "10"));
		mongoTemplate.save(new FavActorEntity(testUser, "11"));
		mongoTemplate.save(new FavActorEntity(testUser, "12"));
		mongoTemplate.save(new FavActorEntity(testUser, "13"));
		mongoTemplate.save(new FavActorEntity(testUser, "14"));
		mongoTemplate.save(new FavActorEntity(testUser, "15"));
		mongoTemplate.save(new FavActorEntity(testUser, "16"));
		mongoTemplate.save(new FavActorEntity(testUser, "17"));
		mongoTemplate.save(new FavActorEntity(testUser, "18"));
		mongoTemplate.save(new FavActorEntity(testUser, "19"));
		mongoTemplate.save(new FavActorEntity(testUser, "20"));
		mongoTemplate.save(new FavActorEntity(testUser, "21"));
		mongoTemplate.save(new FavActorEntity(testUser, "22"));
		FavMoviesEntity miPrimeraLista = new FavMoviesEntity("Mi primera lista", testUser);
		miPrimeraLista.setId("1");
		mongoTemplate.save(miPrimeraLista);
		FavMoviesEntity miSegundaLista = new FavMoviesEntity("Mi segunda lista", testUser);
		miSegundaLista.setId("2");
		mongoTemplate.save(miSegundaLista);
		FavMoviesEntity topPeliculas = new FavMoviesEntity("Top peliculas 1", adminUser);
		topPeliculas.setId("bac25bb4c3dd");
		mongoTemplate.save(topPeliculas);
		FavMoviesEntity peliculasDeTerror = new FavMoviesEntity("Peliculas de terror", adminUser);
		peliculasDeTerror.setId("838987df153f");
		mongoTemplate.save(peliculasDeTerror);
		FavMoviesEntity mejoresPeliculas = new FavMoviesEntity("Las mejores peliculas", testUser);
		mejoresPeliculas.setId("db67970a4304");
		mongoTemplate.save(mejoresPeliculas);
		FavMoviesEntity otraLista = new FavMoviesEntity("Otra lista", testUser);
		otraLista.setId("5b377bf69c18");
		mongoTemplate.save(otraLista);
		MovieEntity movie1 = new MovieEntity("2", topPeliculas);
		movie1.setId("100");
		mongoTemplate.save(movie1);
		MovieEntity movie2 = new MovieEntity("9", topPeliculas);
		movie2.setId("b0de755ad68d");
		mongoTemplate.save(movie2);
		MovieEntity movie3 = new MovieEntity("2", peliculasDeTerror);
		movie3.setId("c852bd8933e8");
		mongoTemplate.save(movie3);
		MovieEntity movie4 = new MovieEntity("11", peliculasDeTerror);
		movie4.setId("5b97ba8c355d");
		mongoTemplate.save(movie4);
		MovieEntity movie5 = new MovieEntity("6", mejoresPeliculas);
		movie5.setId("e5f48069cc6c");
		mongoTemplate.save(movie5);
		MovieEntity movie6 = new MovieEntity("2", mejoresPeliculas);
		movie6.setId("3d60fd0592d8");
		mongoTemplate.save(movie6);
		MovieEntity movie7 = new MovieEntity("3", otraLista);
		movie7.setId("c8faf2e9f111");
		mongoTemplate.save(movie7);
		MovieEntity movie8 = new MovieEntity("2", otraLista);
		movie8.setId("60398be848c0");
		mongoTemplate.save(movie8);
		
		
	}
	
	public void deleteAllData()
	{
		mongoTemplate.dropCollection(UserEntity.class);
		mongoTemplate.dropCollection(FavActorEntity.class);
		mongoTemplate.dropCollection(MovieEntity.class);
		mongoTemplate.dropCollection(FavMoviesEntity.class);
	}

}
