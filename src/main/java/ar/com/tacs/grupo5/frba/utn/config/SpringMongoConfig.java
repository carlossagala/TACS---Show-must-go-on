package ar.com.tacs.grupo5.frba.utn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {

	@Override
	public String getDatabaseName() {
		return "show_must_go_on";
	}

	@Override
	@Bean
	public Mongo mongo() throws Exception {
		   String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
	        if (host == null) {
	            return new MongoClient("127.0.0.1");
	           
	        }
	        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
	        String dbname = System.getenv("OPENSHIFT_APP_NAME");
	        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
	        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
	        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
	        return new MongoClient(new ServerAddress(host, port), mongoClientOptions);
	}

}
