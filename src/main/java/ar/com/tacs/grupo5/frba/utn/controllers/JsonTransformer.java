package ar.com.tacs.grupo5.frba.utn.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import spark.ResponseTransformer;

@Component
public class JsonTransformer implements ResponseTransformer {
	
	private Gson gson;
	private static Logger logger = LoggerFactory.getLogger(JsonTransformer.class);

	@Autowired
    public JsonTransformer(Gson gson) {
		super();
		this.gson = gson;
	}


    @Override
    public String render(Object model) {
		String responseJson = gson.toJson(model);
		logger.info(responseJson);
        return responseJson;
    }
}
