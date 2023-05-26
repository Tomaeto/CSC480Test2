package json;
import datamanage.PublisherBean;

import java.sql.SQLException;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datamanage.PubManager;

//Adrian Faircloth
//12-6-22
//CSC480 Test 2
//Class for building JSON file with publisher info from database
public class PublisherToJson {

	static PublisherBean[] beans;
	static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Main method, gets publisher data from PubManager and uses Jackson API to write data to JSON file test2.json in data folder
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException, IOException {
		PubManager pm = new PubManager();
		PublisherBean[] beans = pm.getBeans();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("data/test2.json"), beans);
	}
}
