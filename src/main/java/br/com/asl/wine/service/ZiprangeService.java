package br.com.asl.wine.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import br.com.asl.wine.dto.ZiprangeNewDto;
import br.com.asl.wine.dto.ZiprangeUpdateDto;
import br.com.asl.wine.model.Store;
import br.com.asl.wine.model.Ziprange;
import br.com.asl.wine.repository.ZiprangeRepository;
import br.com.asl.wine.service.exception.ObjectNotFoundException;

@Service
@RequiredArgsConstructor
public class ZiprangeService {

	private final ZiprangeRepository zRepository;

	private final StoreService sService;
	
	public Ziprange fromNewDto(ZiprangeNewDto dto) {
		Store store = sService.findByStoreCode(dto.getStoreCode());
		return Ziprange.builder()
				.store(store)
				.rangeStart(Integer.parseInt(dto.getRangeStart()))
				.rangeEnd(Integer.parseInt(dto.getRangeEnd()))
				.build();
	}
	
	public Ziprange fromUpdateDto(ZiprangeUpdateDto dto) {
		Store store = sService.findByStoreCode(dto.getStoreCode());
		return Ziprange.builder()
				.id(dto.getId())
				.store(store)
				.rangeStart(Integer.parseInt(dto.getRangeStart()))
				.rangeEnd(Integer.parseInt(dto.getRangeEnd()))
				.build();
	}
	
	public List<Ziprange> findAll() {
		return zRepository.findAll();
	}
	
	public Ziprange findById(Integer id) {
		return zRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
					"Object Not Found! Id: " + id + ", Type:  " + Ziprange.class.getName()));
	}
	
	public Ziprange insert(Ziprange range) {
		return zRepository.save(range);
	}
	
	public Ziprange update(Ziprange range) {
		findById(range.getId());
		return zRepository.save(range);
	}
	
	public void deleteById(Integer id) {
		Ziprange zRange = findById(id);
		zRepository.delete(zRange);
	}
	
}
