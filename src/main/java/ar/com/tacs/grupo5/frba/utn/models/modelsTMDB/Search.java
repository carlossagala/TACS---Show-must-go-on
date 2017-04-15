package ar.com.tacs.grupo5.frba.utn.models.modelsTMDB;

import java.util.List;

public class Search {

	private int page;
	private List<SearchResult> result;
	private int total_results;
	private int total_pages;

	public Search() {

	}

	public Search(String id, String type, String name) {
		super();
		// this.id = id;
		// this.type = type;
		// this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<SearchResult> getResult() {
		return result;
	}

	public void setResult(List<SearchResult> result) {
		this.result = result;
	}

	public int getTotal_results() {
		return total_results;
	}

	public void setTotal_results(int total_results) {
		this.total_results = total_results;
	}

	public int getTotal_pages() {
		return total_pages;
	}

	public void setTotal_pages(int total_pages) {
		this.total_pages = total_pages;
	}

}
