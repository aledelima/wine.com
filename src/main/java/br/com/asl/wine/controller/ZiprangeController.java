package br.com.asl.wine.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import br.com.asl.wine.dto.StoreBriefDto;
import br.com.asl.wine.dto.ZiprangeNewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.asl.wine.dto.ZiprangeDto;
import br.com.asl.wine.dto.ZiprangeUpdateDto;
import br.com.asl.wine.model.Ziprange;
import br.com.asl.wine.service.ZiprangeService;

@RestController
@RequestMapping("/api/zipranges")
@Validated
@RequiredArgsConstructor
public class ZiprangeController {

	private final ZiprangeService service;
	
	@GetMapping
	public ResponseEntity<List<ZiprangeDto>> findAll() {
		List<ZiprangeDto> dtos = service.findAll().stream().map(ziprange -> new ZiprangeDto(ziprange)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ZiprangeDto> findById(@PathVariable("id")Integer id) {
		return ResponseEntity.ok().body(new ZiprangeDto(service.findById(id)));
	}

	@GetMapping("/findStore/{zipcode}")
	public ResponseEntity<StoreBriefDto> findById(@PathVariable("zipcode") @Pattern(regexp="\\d{8}") String zipcode) {
		StoreBriefDto dto =
				new RestTemplate().getForObject("http://localhost:8080/api/stores/findByZip/{zipcode}", StoreBriefDto.class, zipcode);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<ZiprangeDto> insert(@Valid @RequestBody ZiprangeNewDto dto) {
		Ziprange range = service.fromNewDto(dto);
		range = service.insert(range);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(range.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public ResponseEntity<Void> update(@Valid @RequestBody ZiprangeUpdateDto dto) {
		Ziprange range = service.fromUpdateDto(dto);
		service.update(range);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
