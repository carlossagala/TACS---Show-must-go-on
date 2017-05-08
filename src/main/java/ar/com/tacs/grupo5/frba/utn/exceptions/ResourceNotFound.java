package ar.com.tacs.grupo5.frba.utn.exceptions;

public class ResourceNotFound extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1823279448740137525L;
	
	public ResourceNotFound()
	{
		
	}
	
	public ResourceNotFound(String message)
	{
		super(message);
	}

}
