package ar.com.tacs.grupo5.frba.utn.models.modelsTMDB;

public class Image {
	private String aspect_ratio;
	private String file_path;
	private int height;
	private String iso_639_1;
	private String vote_average;
	private String vote_count;
	private int width;

	public String getAspect_ratio() {
		return aspect_ratio;
	}

	public void setAspect_ratio(String aspect_ratio) {
		this.aspect_ratio = aspect_ratio;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getIso_639_1() {
		return iso_639_1;
	}

	public void setIso_639_1(String iso_639_1) {
		this.iso_639_1 = iso_639_1;
	}

	public String getVote_average() {
		return vote_average;
	}

	public void setVote_average(String vote_average) {
		this.vote_average = vote_average;
	}

	public String getVote_count() {
		return vote_count;
	}

	public void setVote_count(String vote_count) {
		this.vote_count = vote_count;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
