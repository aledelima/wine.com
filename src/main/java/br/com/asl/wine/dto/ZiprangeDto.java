package br.com.asl.wine.dto;

import java.io.Serializable;

import br.com.asl.wine.model.Ziprange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZiprangeDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String storeCode;
	private String rangeStart;
	private String rangeEnd;

	public ZiprangeDto(Ziprange ziprange) {
		this.id = ziprange.getId();
		this.storeCode = ziprange.getStore().getStoreCode();
		this.rangeStart = ziprange.getRangeStart().toString();
		this.rangeEnd = ziprange.getRangeEnd().toString();
	}
	
}
