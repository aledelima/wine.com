package br.com.asl.wine.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.asl.wine.service.validation.RangeUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RangeUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZiprangeUpdateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "Required Field.")
	private Integer id;
	@NotEmpty(message = "Required Field.")
	private String storeCode;
	@Pattern(regexp="\\d{8}", message = "Invalid Field Format. It requires the format dddddddd.")
	private String rangeStart;
	@Pattern(regexp="\\d{8}", message = "Invalid Field Format. It requires the format dddddddd.")
	private String rangeEnd;

	
}
