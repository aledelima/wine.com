package br.com.asl.wine.service;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.asl.wine.dto.StoreNewDto;
import br.com.asl.wine.model.Store;
import br.com.asl.wine.repository.StoreRepository;
import br.com.asl.wine.service.exception.ObjectNotFoundException;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository repository;
	
	public List<Store> findAll() {
		return repository.findAll();
	}
	
	public Store findById(Integer id) {
		Optional<Store> store = repository.findById(id);
		return store.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found! Id: " + id + ", Type:  " + Store.class.getName()));
	}
	
	public Store findByStoreCode(String storecode) {
		Optional<Store> store = repository.findByStoreCode(storecode);
		return store.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found! StoreCode: " + storecode + ", Type:  " + Store.class.getName()));
	}
	
	public Store findByZipcode(Integer zipcode) {
		Optional<Store> store = repository.findByZipcode(zipcode);
		return store.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found! Zip code not covered by any store. Zipcode: " + zipcode + ", Type:  " + Store.class.getName()));
	}
	
	public Store fromNewDto(StoreNewDto dto) {
		return Store.builder()
				.storeCode(dto.getStoreCode())
				.address(dto.getAddress())
				.phone(dto.getPhone())
				.build();
	}

	public Store insert(Store store) {
		return repository.save(store);
	}
}
