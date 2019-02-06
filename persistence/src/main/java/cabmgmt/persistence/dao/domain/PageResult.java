package cabmgmt.persistence.dao.domain;

import java.util.List;

public class PageResult<T> {
	
	private final List<T> results;
	private final int pageIndex;
	private final long totalRecourds;
	private final int totalPages;

	public PageResult(List<T> results, int pageIndex, long totalRecords, int totalPages) {
		this.results = results;
		this.pageIndex = pageIndex;
		this.totalRecourds = totalRecords;
		this.totalPages = totalPages;
	}
	
	public List<T> getResults(){
		return this.results;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public long getTotalRecourds() {
		return totalRecourds;
	}

	public int getTotalPages() {
		return totalPages;
	};
}
