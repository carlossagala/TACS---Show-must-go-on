package ar.com.tacs.grupo5.frba.utn.models.modelsTMDB;

import java.util.List;

public class Images {
	private List<Image> backdrops;
	private List<Image> posters;

	public List<Image> getBackdrops() {
		return backdrops;
	}

	public void setBackdrops(List<Image> backdrops) {
		this.backdrops = backdrops;
	}

	public List<Image> getPosters() {
		return posters;
	}

	public void setPosters(List<Image> posters) {
		this.posters = posters;
	}

}
