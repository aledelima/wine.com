package br.com.asl.wine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreNewDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String storeCode;
	private String address;
	private String phone;
	
}
