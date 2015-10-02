package com.epayroll.model.adminConsole;

import java.util.List;

public class FlexigridDataResponseModel {
	private Integer page;
	private Integer total;
	private List<Row> rows;

	public FlexigridDataResponseModel() {
	}

	public FlexigridDataResponseModel(Integer page, Integer total, List<Row> cells) {
		super();
		this.page = page;
		this.total = total;
		this.rows = cells;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Row> getRows() {
		return rows;
	}

	public void setRows(List<Row> cells) {
		this.rows = cells;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlexigridDataResponseModel [page=");
		builder.append(page);
		builder.append(", total=");
		builder.append(total);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}

}
