package com.yash.medicine.model;

public enum Packaging {

	STRIP(0), BOX(1), Tester(2);

	private final int value;

	private Packaging(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
