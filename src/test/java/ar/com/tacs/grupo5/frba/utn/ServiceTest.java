package ar.com.tacs.grupo5.frba.utn;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.tacs.grupo5.frba.utn.models.Actor;
import ar.com.tacs.grupo5.frba.utn.models.Images;
import ar.com.tacs.grupo5.frba.utn.models.Movie;
import ar.com.tacs.grupo5.frba.utn.models.Reviews;
import ar.com.tacs.grupo5.frba.utn.service.MovieService;
import ar.com.tacs.grupo5.frba.utn.service.MovieServiceImpl;

public class ServiceTest {

	private MovieService movieService;

	@Before
	public void initTest() {
		movieService = new MovieServiceImpl();
	}

	// ejemplo hecho en base al ejemplo de tmdb
	@Test
	public void getReviewsTest() {
		Reviews reviews = movieService.getReviews("297761");
		Assert.assertEquals(5, reviews.getResults().size());

	}

	// ejemplo hecho en base al ejemplo de tmdb
	@Test
	public void getImagesTest() {
		Images images = movieService.getImages("550");
		Assert.assertEquals(0, images.getBackdrops().size());
		Assert.assertEquals(3, images.getPosters().size());

	}

	// ejemplo hecho en base al ejemplo de tmdb
	@Test
	public void getMovieActorsTest() {
		List<Actor> actors = movieService.getMovieActors("550");
		Assert.assertEquals(77, actors.size());
		Assert.assertTrue(actors.stream().anyMatch(actor -> "Brad Pitt".equals(actor.getName())));
		Assert.assertTrue(actors.stream().anyMatch(actor -> "Michael Girardin".equals(actor.getName())));
		Assert.assertFalse(actors.stream().anyMatch(actor -> "Angeline Jolie".equals(actor.getName())));
		

	}
	
	// ejemplo hecho en base al ejemplo de tmdb
		@Test
		public void getMovieDetailTest() {
			Movie movie = movieService.getMovieDetail("550");
			Assert.assertEquals(77, movie.getActors().size());
			Assert.assertEquals(0, movie.getImages().getBackdrops().size());
			Assert.assertEquals(3, movie.getImages().getPosters().size());
			Assert.assertEquals(0, movie.getReviews().getResults().size());
			Assert.assertEquals("El club de la lucha", movie.getTitle());
			Assert.assertTrue(movie.getOverview().contains("En un viaje en avión conoce a Tyler Durden, un carismático vendedor de jabón"));
			
			


		}
		
		
	

}
