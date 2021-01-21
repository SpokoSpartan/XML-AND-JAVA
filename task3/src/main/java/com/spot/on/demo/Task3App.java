package com.spot.on.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spot.on.demo.models.AggregatedData;
import com.spot.on.demo.models.Root;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task3App {

	private static final Logger LOGGER = Logger.getLogger(Task3App.class.getName());

	private static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) throws IOException {
		List<String> allDates = getAllDates(stringToDate("2020-12-30"), stringToDate("2021-01-10"));
		AggregatedData aggregatedData = fetchAllData(allDates);
		saveAsJson(aggregatedData);
	}

	private static LocalDate stringToDate(String date) {
		return LocalDate.parse(date, formatters);
	}

	private static List<String> getAllDates(LocalDate beginningSharp, LocalDate endSharp) {
		long numOfDays = ChronoUnit.DAYS.between(beginningSharp, endSharp) + 1;
		return Stream.iterate(beginningSharp, date -> date.plusDays(1))
				.limit(numOfDays)
				.map(Task3App::dateToString)
				.collect(Collectors.toList());
	}

	private static String dateToString(LocalDate date) {
		return date.format(formatters);
	}

	private static AggregatedData fetchAllData(List<String> allDates) throws IOException {
		AggregatedData aggregatedData = new AggregatedData();
		for (String date : allDates) {
			aggregatedData.addRoot(fetchData(date));
		}
		return aggregatedData;
	}

	private static Root fetchData(String date) throws IOException {
		LOGGER.info("Fetching for: " + date);
		URL url = new URL("https://api.data.gov.sg/v1/environment/air-temperature?date=" + date);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		InputStream responseStream = connection.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(responseStream, Root.class);
	}

	private static void saveAsJson(AggregatedData aggregatedData) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(df);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(aggregatedData);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("api.json"))) {
			writer.write(json);
			writer.flush();
		}
	}

}
