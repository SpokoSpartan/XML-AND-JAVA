package com.spot.on.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spot.on.demo.models.ColorsContent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Task2App {

	private static final Logger LOGGER = Logger.getLogger(Task2App.class.getName());

	private static final List<String> availableColors = Arrays.asList("red", "green", "blue", "pink");

	public static void main(String[] args) throws IOException {
		ColorsContent colorsContent = generateRandomContent();
		saveAsJson(colorsContent);
		String colorToCalc = availableColors.get(new Random().nextInt(availableColors.size()));
		Double average = getAverageOfColour(colorToCalc);
		LOGGER.info("The average of color " + colorToCalc + ": " + average);
	}

	private static ColorsContent generateRandomContent() {
		ColorsContent colorsContent = new ColorsContent();
		Random random = new Random();
		for (int i = 0; i <= random.nextInt(400); i++) {
			colorsContent.addValue(random.nextDouble() * 255.0);
			colorsContent.addColor(availableColors.get(random.nextInt(availableColors.size())));
		}
		return colorsContent;
	}

	private static void saveAsJson(ColorsContent colorsContent) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(colorsContent);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("colors.json"))) {
			writer.write(json);
			writer.flush();
		}
	}

	private static Double getAverageOfColour(String color) throws IOException {
		ColorsContent colorsContent = readFromJson();
		long itemsFound = colorsContent.getColors()
				.stream()
				.filter(col -> col.equalsIgnoreCase(color))
				.count();
		long totalItems = colorsContent.getColors().size();
		return itemsFound * 1.0 / totalItems;
	}

	private static ColorsContent readFromJson() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(Paths.get("colors.json").toFile(), ColorsContent.class);
	}

}
