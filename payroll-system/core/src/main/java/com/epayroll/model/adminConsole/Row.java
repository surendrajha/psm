package com.epayroll.model.adminConsole;

public class Row {
	private Integer id;
	private Object cell;

	public Row() {
	}

	public Row(Integer id, Object cell) {
		super();
		this.id = id;
		this.cell = cell;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Row [id=");
		builder.append(id);
		builder.append(", cell=");
		builder.append(cell);
		builder.append("]");
		return builder.toString();
	}

}
