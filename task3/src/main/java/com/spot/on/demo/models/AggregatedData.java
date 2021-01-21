package com.spot.on.demo.models;

import java.util.ArrayList;
import java.util.List;

public class AggregatedData {

	private List<Root> roots;

	public AggregatedData() {
		roots = new ArrayList<>();
	}

	public void addRoot(Root root) {
		this.roots.add(root);
	}

	public List<Root> getRoots() {
		return roots;
	}

}
