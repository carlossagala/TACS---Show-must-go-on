package ar.com.tacs.grupo5.frba.utn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.config.SpringMongoConfig;
import ar.com.tacs.grupo5.frba.utn.dao.MongoTestConfiguration;

//@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"ar.com.tacs.grupo5.frba.utn"}, excludeFilters={
		@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value={SpringMongoConfig.class,App.class})})
public class AppTest {
	private static Logger logger = LoggerFactory.getLogger(AppTest.class);

	public AnnotationConfigApplicationContext mainTest(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppTest.class, MongoTestConfiguration.class);
		Environment environment = ctx.getBean(Environment.class);
		String port = environment.getProperty("server.port");
		logger.info("La aplicación levantó correctamente y escucha en el puerto " + port);
		return  ctx;
	}
	
	@Bean
	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}
}
