package ar.com.tacs.grupo5.frba.utn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.config.SpringMongoConfig;
import ar.com.tacs.grupo5.frba.utn.entity.UserEntity;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })

public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class, SpringMongoConfig.class);
		//Comentando la de arriba y dejando la de abajo funciona joya pero no levanta el entorno spark, habría que ver como hacer para que levante todo
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		Environment environment = ctx.getBean(Environment.class);
		String port = environment.getProperty("server.port");
		logger.info("La aplicación levantó correctamente y escucha en el puerto " + port);
		
		//@SuppressWarnings("resource")
		//ApplicationContext ctx2 = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
		MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
		
		UserEntity usr = new UserEntity();
		
		usr.setUserName("pepe argento");
		
		mongoOperation.save(usr);
	}

	@Bean
	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}


}
