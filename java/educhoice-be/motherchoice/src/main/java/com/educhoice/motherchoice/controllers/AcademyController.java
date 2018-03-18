package com.educhoice.motherchoice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.service.AcademyService;
import com.educhoice.motherchoice.valueobject.models.academies.AcademyDto;
import com.educhoice.motherchoice.valueobject.models.academies.ImageListDto;
import com.educhoice.motherchoice.valueobject.models.academies.ImageUploadDto;
import com.educhoice.motherchoice.valueobject.models.query.AcademyQueryDto;

@RequestMapping("/api/academy")
@RestController
public class AcademyController {

	@Autowired
	private AcademyService academyService;

	@CrossOrigin
	@ResponseBody
	@PostMapping("")
	public ResponseEntity inputAcademyDetail(@RequestBody Academy academy) {
		academyService.saveAcademy(academy);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@CrossOrigin
	@ResponseBody
	@PostMapping("/image")
	public ResponseEntity inputImage(@RequestBody ImageUploadDto imageDto) {
		academyService.saveImages(imageDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	// Search
	@CrossOrigin
	@ResponseBody
	@PostMapping(value = "/search", consumes = { MediaType.APPLICATION_JSON_UTF8_VALUE })
	public List<AcademyDto> returnSearchResult(@RequestBody(required = false) AcademyQueryDto academyQueryDto) {
		return academyService.getAcademyDtos(academyQueryDto);
	}

	// Detail
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/uZopi
	@GetMapping("/{academyId}")
	public AcademyDto returnAcademyDetail(@PathVariable("academyId") long academyId) {
		return AcademyDto.generateAcademyDto(academyService.getAcademyById(academyId), 1);
	}

	// 사진 / 배너 확대
	// https://ovenapp.io/view/odVQjLGs9tMGhnbNPzVIktJP5dTrXnoP/etqqp
	@GetMapping("/imageList/{academyId}")
	public List<ImageListDto> returnImageList(@PathVariable("academyId") long academyId) {
		return academyService.findMulitpleImageList(academyId);
	}
}