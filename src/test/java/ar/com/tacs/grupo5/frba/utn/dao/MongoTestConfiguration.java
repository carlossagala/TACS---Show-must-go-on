package ar.com.tacs.grupo5.frba.utn.dao;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories
public class MongoTestConfiguration extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "showmustgoon";
	}

	@Override
	public Mongo mongo() throws Exception {
		Fongo f = new Fongo("showmustgoon");
		return f.getMongo();
	}
	
}
