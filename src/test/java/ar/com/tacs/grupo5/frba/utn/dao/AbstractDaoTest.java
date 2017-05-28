package ar.com.tacs.grupo5.frba.utn.dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoTestConfiguration.class })
public abstract class AbstractDaoTest {
	
	@Autowired
    private MongoTemplate mongoTemplate;
	
	public void insertInitialData()
	{
		mongoTemplate.save(new UserEntity("1", "user1", "user1", "user"));
		mongoTemplate.save(new UserEntity("2", "user2", "user2", "user"));
	}
	
	public void deleteAllData()
	{
		mongoTemplate.dropCollection(UserEntity.class);
	}

}
