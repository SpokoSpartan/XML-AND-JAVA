package com.spot.on.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spot.on.demo.models.Catalog;
import com.spot.on.demo.models.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Task1App {

	public static void main(String[] args) throws JAXBException, IOException {
		Catalog catalog = readCatalog();
		saveCatalogAsJson(catalog);
	}

	private static Catalog readCatalog() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class, ObjectFactory.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (Catalog) unmarshaller.unmarshal(new File("catalog.xml"));
	}

	private static void saveCatalogAsJson(Catalog catalog) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(catalog);
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("catalog.json"))) {
			writer.write(json);
			writer.flush();
		}
	}

}
