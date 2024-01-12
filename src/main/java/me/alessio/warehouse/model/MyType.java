package me.alessio.warehouse.model;

public enum MyType {

	IN("IN"), OUT("OUT");

	private String value;

	private MyType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
