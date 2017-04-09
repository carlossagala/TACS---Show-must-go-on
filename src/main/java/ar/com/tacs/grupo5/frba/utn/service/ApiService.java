package ar.com.tacs.grupo5.frba.utn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.tacs.grupo5.frba.utn.dao.HelloWorldDAO;

@Component
public class ApiService {
	private static Logger logger = LoggerFactory.getLogger(ApiService.class);

	private HelloWorldDAO helloWorldDao;

	@Autowired
	public ApiService(HelloWorldDAO helloWorldDao) {
		super();
		this.helloWorldDao = helloWorldDao;
	}

	public String helloWorld() {
		logger.info("Hello World");
		String result = helloWorldDao.helloWorld();
		return result;
	}

}
