package ar.com.tacs.grupo5.frba.utn.models;

public class PagedResponse extends Response{
	
	private Integer page;
	private Long totalResults;
	private Integer totalPages;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	public Integer getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	

}
