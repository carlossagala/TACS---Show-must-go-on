package ar.com.tacs.grupo5.frba.utn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Actor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Image;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Images;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Movie;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Reviews;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.Search;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResult;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultActor;
import ar.com.tacs.grupo5.frba.utn.models.modelsTMDB.SearchResultMovie;
import ar.com.tacs.grupo5.frba.utn.service.ActorService;
import ar.com.tacs.grupo5.frba.utn.service.ActorServiceImpl;
import ar.com.tacs.grupo5.frba.utn.service.MovieService;
import ar.com.tacs.grupo5.frba.utn.service.MovieServiceImpl;
import ar.com.tacs.grupo5.frba.utn.service.SearchService;
import ar.com.tacs.grupo5.frba.utn.service.SearchServiceImpl;

public class ServiceTest {

	private MovieService movieService;
	private ActorService actorService;
	private SearchService searchService;

	@Before
	public void initTest() {
		movieService = new MovieServiceImpl();
		actorService = new ActorServiceImpl();
		searchService = new SearchServiceImpl();
	}

	// ejemplo hecho en base al ejemplo de tmdb
	@Test
	public void getMovieReviewsTest() {
		Reviews reviews = movieService.getReviews("297761");
		Assert.assertEquals(5, reviews.getResults().size());

	}

	// ejemplo hecho en base al ejemplo de tmdb
	@Test
	public void getMovieImagesTest() {
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
		Assert.assertTrue(movie.getOverview()
				.contains("En un viaje en avión conoce a Tyler Durden, un carismático vendedor de jabón"));

	}

	@Test
	public void getActorImagesTest() {
		List<Image> images = actorService.getActorImages("66633");
		Assert.assertEquals(2, images.size());

	}

	@Test
	public void getActorWorksTest() {
		List<Movie> movies = actorService.getActorMovies("66633");
		Assert.assertEquals(1, movies.size());
		Assert.assertTrue(movies.stream().anyMatch(
				movie -> "No Half Measures: Creating the Final Season of Breaking Bad".equals(movie.getTitle())));

	}

	@Test
	public void getActorDetailTest() {
		Actor actor = actorService.getDetailActor("66633");
		Assert.assertTrue(actor.getBiography().contains("is an American writer, producer, and director"));
		Assert.assertTrue(actor.getMovies().stream().anyMatch(
				movie -> "No Half Measures: Creating the Final Season of Breaking Bad".equals(movie.getTitle())));
		Assert.assertEquals(1, actor.getMovies().size());
		Assert.assertEquals(2, actor.getImage().size());
		Assert.assertEquals("Vince Gilligan", actor.getName());
	}

	
	@Test
	public void getMoviesWithActors(){
		
		List<String> actorIds = new ArrayList<>();
		actorIds.add("287");
		actorIds.add("819");
		
		List<Movie> movies = actorService.getMoviesWithActors(actorIds);
		Assert.assertEquals(1,movies.size());
		Assert.assertTrue(movies.stream().anyMatch(
				movie -> "Fight Club".equals(movie.getTitle())));
		
		actorIds.add("11701");//angeline jolie
		
		 movies = actorService.getMoviesWithActors(actorIds);
		Assert.assertNotEquals(1,movies.size());
		Assert.assertTrue(movies.stream().anyMatch(
				movie -> "Fight Club".equals(movie.getTitle())));
	}
	
	@Test
	public void getMoviesWithActors2(){
		
		List<String> actorIds = new ArrayList<>();
		
		
		List<Movie> movies = actorService.getMoviesWithActors(actorIds);
		Assert.assertEquals(0,movies.size());
		
	}
	
	
	
	
	
	@Test
	public void getMovieSearchResultTest() {
		Search search = searchService.searchByMovie("fight");
		Assert.assertEquals(20, search.getResult().size());

		List<SearchResult> results = search.getResult();
		Assert.assertTrue(results.stream().allMatch(result -> ((SearchResultMovie) result).getType().equals("movie")));

		Assert.assertTrue(
				results.stream().anyMatch(result -> ((SearchResultMovie) result).getTitle().equals("The Fight")));

	}

	@Test
	public void getActorSearchResultTest() {
		Search search = searchService.searchByActor("brad");
		Assert.assertEquals(20, search.getResult().size());

		List<SearchResult> results = search.getResult();
		Assert.assertTrue(results.stream().allMatch(result -> ((SearchResultActor) result).getType().equals("actor")));

		Assert.assertTrue(
				results.stream().anyMatch(result -> ((SearchResultActor) result).getName().equals("Brad Pitt")));

	}
	
	
	@Test
	public void getMultiSearchResultTest() {
		Search search = searchService.searchMulti("brad");
		Assert.assertEquals(40, search.getResult().size());

	}
	
	
	

}
