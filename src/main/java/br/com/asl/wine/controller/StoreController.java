package br.com.asl.wine.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Pattern;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.asl.wine.dto.StoreBriefDto;
import br.com.asl.wine.dto.StoreDto;
import br.com.asl.wine.model.Store;
import br.com.asl.wine.service.StoreService;

@RestController
@RequestMapping("/api/stores")
@Validated
@RequiredArgsConstructor
public class StoreController {

	final private StoreService service;
	
	@GetMapping
	public ResponseEntity<List<StoreDto>> findAll() {
		return ResponseEntity.ok().body(service.findAll().stream().map(s -> new StoreDto(s)).collect(Collectors.toList()));
	}
	
	@GetMapping("/findByZip/{zipcode}")
	ResponseEntity<StoreBriefDto> findByZipcode(
			@PathVariable("zipcode")
			@Pattern(regexp="\\d{8}", message = "Invalid zip format. Required: dddddddd")
					String zipcode) {
		Store store = service.findByZipcode(Integer.parseInt(zipcode));
		return ResponseEntity.ok().body(new StoreBriefDto(store));
	}
	
}
