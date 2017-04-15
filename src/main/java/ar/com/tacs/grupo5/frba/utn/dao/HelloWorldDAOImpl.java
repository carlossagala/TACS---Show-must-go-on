package ar.com.tacs.grupo5.frba.utn.dao;

//import org.hsqldb.util.DatabaseManagerSwing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.tacs.grupo5.frba.utn.dao.repository.HelloWorldRepository;
import ar.com.tacs.grupo5.frba.utn.entity.HelloWorldEntity;
import ar.com.tacs.grupo5.frba.utn.models.User;
import ar.com.tacs.grupo5.frba.utn.service.UserService;;

@Repository
public class HelloWorldDAOImpl implements HelloWorldDAO {
	private static Logger logger = LoggerFactory.getLogger(HelloWorldDAOImpl.class);

	private HelloWorldRepository helloWorldRepository;
	@Autowired
	UserService userService;
	@Autowired
	public HelloWorldDAOImpl(HelloWorldRepository helloWorldRepository) {
		super();
		this.helloWorldRepository = helloWorldRepository;
	}

	@Override
	@Transactional
	public String helloWorld() {
		logger.info("dao");
		HelloWorldEntity helloWorld = null;
		helloWorldRepository.save(new HelloWorldEntity("1", "Hola mundo"));
//		  DatabaseManagerSwing.main(
//					new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
		userService.saveUser(new User("test","test1","user"));

		helloWorld = helloWorldRepository.findOne("1");
		if (helloWorld == null) {
			return "not_found";
		}
		return helloWorld.getName();
	}

}
