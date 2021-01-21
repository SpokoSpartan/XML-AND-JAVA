package com.spot.on.demo.models;

import java.util.ArrayList;
import java.util.List;

public class ColorsContent {

	private List<Double> values;
	private List<String> colors;

	public ColorsContent() {
		values = new ArrayList<>();
		colors = new ArrayList<>();
	}

	public void addColor(String color) {
		colors.add(color);
	}

	public void addValue(Double value) {
		values.add(value);
	}

	public List<Double> getValues() {
		return values;
	}

	public List<String> getColors() {
		return colors;
	}


}
