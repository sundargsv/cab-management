package cabmgmt.rest.domain.response;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PageResultResponse<T> {
	
	@ApiModelProperty(value="page index of current result page", readOnly=true)
	private final int pageIndex;
	
	@ApiModelProperty(value="page size", readOnly=true)
	private final int pageSize;
	
	@ApiModelProperty(value="total number of results", readOnly=true)
	private final long totalRecords;
	
	@ApiModelProperty(value="total pages", readOnly=true)
	private final int totalPages;
	
	@ApiModelProperty(value="results of current result page", readOnly=true )
	private final List<T> results;

	public PageResultResponse(List<T> results, int pageIndex, int pageSize, long totalRecords, int totalPages) {
		this.results = results;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		this.totalPages = totalPages;
	}
	
	public List<T> getResults(){
		return this.results;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getPageSize() {
		return pageSize;
	};
}
