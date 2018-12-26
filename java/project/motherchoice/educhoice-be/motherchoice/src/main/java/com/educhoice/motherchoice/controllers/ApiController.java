package com.educhoice.motherchoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educhoice.motherchoice.models.persistent.geolocation.SearchAddress;
import com.educhoice.motherchoice.service.AcademyService;

@RequestMapping("/api")
@RestController
public class ApiController {

	@Autowired
	private AcademyService academyService;

	@CrossOrigin
	@GetMapping("/searchAddress/{value}")
	public List<SearchAddress> findByAddressContaining(@PathVariable("value") String value) {
		return academyService.retrieveAddressList(value);
	}
}