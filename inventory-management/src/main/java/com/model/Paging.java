package com.model;

public class Paging {
	// tong so ban ghi
	private long totalRows;

	// tong so trang
	private int totalPages;

	// trang hien tai
	private int indexPage;

	// tong so ban ghi o 1 trang
	private int recordPerPage = 10;

	// stt khi quety
	// trang 1 => 0ffset: 0 - 9
	// trang 2 => offset: 10 - 19
	private int offset;

	public Paging(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		if (totalRows > 0) {
			totalPages = (int) Math.ceil(totalRows / (double) recordPerPage);
		}
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getIndexPage() {
		return indexPage;
	}

	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getOffset() {
		// tinh vi tri bat dau cua offset
		if (indexPage > 0) {
			offset = indexPage * recordPerPage - recordPerPage; // 1 * 10 - 10 = 0; 2 * 10 - 10 = 10
		}
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
