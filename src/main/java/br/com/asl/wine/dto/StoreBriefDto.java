package br.com.asl.wine.dto;

import br.com.asl.wine.model.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreBriefDto {

	private String storeCode;
	private String address;
	private String phone;

	public StoreBriefDto(Store store) {
		this.storeCode = store.getStoreCode();
		this.address = store.getAddress();
		this.phone = store.getPhone();
	}

	
}
