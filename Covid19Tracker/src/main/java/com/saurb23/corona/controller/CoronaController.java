package com.saurb23.corona.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.saurb23.corona.model.LocationStats;
import com.saurb23.corona.service.CoronaService;

@Controller
public class CoronaController {
	
	@Autowired
	CoronaService coronaService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats=coronaService.getAllStats();
		int totalReportedCases= allStats.stream().mapToInt(stat->stat.getLatestTotalCases()).sum();
		int totalNewCases= allStats.stream().mapToInt(stat->stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
		
	}
	

}
