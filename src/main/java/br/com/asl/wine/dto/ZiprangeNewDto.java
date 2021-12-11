package br.com.asl.wine.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import br.com.asl.wine.service.validation.RangeInsert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RangeInsert
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ZiprangeNewDto {

	@NotEmpty(message = "Required Field.")
	private String storeCode;
	@Pattern(regexp="\\d{8}", message = "Required Field with Specific format (dddddddd).")
	private String rangeStart;
	@Pattern(regexp="\\d{8}", message = "Required Field with Specific format (dddddddd).")
	private String rangeEnd;

}
