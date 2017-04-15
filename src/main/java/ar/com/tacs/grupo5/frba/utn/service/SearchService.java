package ar.com.tacs.grupo5.frba.utn.service;

import ar.com.tacs.grupo5.frba.utn.models.Search;

public interface SearchService {
	public Search searchByActor(String query);
	public Search searchByMovie(String query);
	public Search searchMulti(String query);
}
