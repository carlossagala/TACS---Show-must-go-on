package ar.com.tacs.grupo5.frba.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
public class SpringMongoConfig extends AbstractMongoConfiguration {
	
    @Value("${spring.data.mongodb.host}")
    private String host;
    @Value("${spring.data.mongodb.port}")
    private Integer port;
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.database}")
    private String database;
    @Value("${spring.data.mongodb.password}")
    private String password;
    
    @Override
    public String getDatabaseName() {
        return database;
    }
    
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(new ServerAddress(host, port),
        		Arrays.asList(MongoCredential.createCredential(username,database, password.toCharArray())));
    }
    
    
}