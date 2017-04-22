package ar.com.tacs.grupo5.frba.utn.mapper;

public interface GenericMapper <T, D> {
	
	D entityToDto(T entity);
	T dtoToEntity(D dto);
	
}