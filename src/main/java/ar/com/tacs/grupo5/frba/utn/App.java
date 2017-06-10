package ar.com.tacs.grupo5.frba.utn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.tacs.grupo5.frba.utn.config.SpringMongoConfig;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan({ "ar.com.tacs.grupo5.frba.utn" })
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class, SpringMongoConfig.class);
		Environment environment = ctx.getBean(Environment.class);
		String port = environment.getProperty("server.port");
		logger.info("App La aplicación levantó correctamente y escucha en el puerto " + port);
	}

	@Bean
	public Gson gson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		return gsonBuilder.create();
	}


}
