package br.com.asl.wine.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.asl.wine.controller.exception.FieldMessage;
import br.com.asl.wine.dto.ZiprangeUpdateDto;
import br.com.asl.wine.repository.ZiprangeRepository;

public class RangeUpdateValidator implements ConstraintValidator<RangeUpdate, ZiprangeUpdateDto> {
	
	@Autowired
	private ZiprangeRepository repository;
	
	@Override
	public void initialize(RangeUpdate ann) {
	}

	@Override
	public boolean isValid(ZiprangeUpdateDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (repository.rangeConveredByDiffZipId(Integer.parseInt(objDto.getRangeStart()), objDto.getId()).isPresent()) {
			list.add(new FieldMessage("rangeStart", "Zip code range already covered by another store!"));
		}
		
		if (repository.rangeConveredByDiffZipId(Integer.parseInt(objDto.getRangeEnd()), objDto.getId()).isPresent()) {
			list.add(new FieldMessage("rangeEnd", "Zip code range already covered by another store!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
