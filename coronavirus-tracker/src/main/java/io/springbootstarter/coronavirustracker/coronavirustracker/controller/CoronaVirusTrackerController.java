package io.springbootstarter.coronavirustracker.coronavirustracker.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.springbootstarter.coronavirustracker.coronavirustracker.data.LocationStats;
import io.springbootstarter.coronavirustracker.coronavirustracker.service.CoronaVirusFetchData;

@Controller
public class CoronaVirusTrackerController {

	@Autowired
	private CoronaVirusFetchData coronaVirusFetchData;
	
	
	@GetMapping("/")
	public String home(Model model) throws IOException, InterruptedException
	{
		int totalCases = 0;
		int totalNewCasesToday = 0;
		List<LocationStats> allStats = coronaVirusFetchData.getAllStats();
		for (LocationStats locationStats : allStats) {
			totalCases+=locationStats.getLatestTotalCases();
		}
		for (LocationStats locationStats : allStats) {
			totalNewCasesToday+=locationStats.getDiffFromPreviousDay();
		}
		model.addAttribute("locationStats", coronaVirusFetchData.getAllStats());
		model.addAttribute("totalCases", totalCases);
		model.addAttribute("totalNewCasesToday", totalNewCasesToday);
		return "home";
	}
}
