package io.springbootstarter.coronavirustracker.coronavirustracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.springbootstarter.coronavirustracker.coronavirustracker.data.LocationStats;

@Service
public class CoronaVirusFetchData {

	
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList<>();
		int totalCases=0;
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
		HttpResponse<String> httpResponse= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		
		
		for (CSVRecord record : records) {
			LocationStats locationStat = new LocationStats();
			int differenceFromPreviousDay;
			locationStat.setState(record.get("Province/State"));
			locationStat.setCountry(record.get("Country/Region"));
			String lastDate = record.get(record.size()-1);
			differenceFromPreviousDay = Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2));
			locationStat.setLatestTotalCases(Integer.parseInt(lastDate));
			locationStat.setDiffFromPreviousDay(differenceFromPreviousDay);
			newStats.add(locationStat);
		}
		this.allStats = newStats;
	}
	
	


	public static String getVIRUS_DATA_URL() {
		return VIRUS_DATA_URL;
	}

	public static void setVIRUS_DATA_URL(String vIRUS_DATA_URL) {
		VIRUS_DATA_URL = vIRUS_DATA_URL;
	}

	public List<LocationStats> getAllStats() {
		return allStats;
	}

	public void setAllStats(List<LocationStats> allStats) {
		this.allStats = allStats;
	}
	
	
}
