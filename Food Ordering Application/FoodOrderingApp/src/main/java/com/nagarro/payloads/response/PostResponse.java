package com.nagarro.payloads.response;

import java.util.List;

public class PostResponse {
	
	private List<DishResponse> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;	
	private boolean lastPage;
	public List<DishResponse> getContent() {
		return content;
	}
	public void setContent(List<DishResponse> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public PostResponse(List<DishResponse> content, int pageNumber, int pageSize, long totalElements, int totalPages,
			boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
