package br.com.asl.wine.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.asl.wine.model.Store;
import br.com.asl.wine.model.Ziprange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {

	private Integer id;
	private String storeCode;
	private String address;
	private String phone;
	private List<ZiprangeDto> zipRanges = new ArrayList<>();

	public StoreDto(Store store) {
		this.id = store.getId();
		this.storeCode = store.getStoreCode();
		this.address = store.getAddress();
		this.phone = store.getPhone();
		this.zipRanges = store.getZipRanges().stream().map(zRange -> new ZiprangeDto(zRange)).collect(Collectors.toList());
	}

}
