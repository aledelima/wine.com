package br.com.asl.wine.service.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.asl.wine.controller.exception.FieldMessage;
import br.com.asl.wine.dto.ZiprangeNewDto;
import br.com.asl.wine.repository.ZiprangeRepository;

public class RangeInsertValidator implements ConstraintValidator<RangeInsert, ZiprangeNewDto> {
	
	@Autowired
	private ZiprangeRepository repository;
	
	@Override
	public void initialize(RangeInsert ann) {
	}

	@Override
	public boolean isValid(ZiprangeNewDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if (repository.zipCovered(Integer.parseInt(objDto.getRangeStart())).isPresent()) {
			list.add(new FieldMessage("rangeStart", "Zip code range already covered by another store!"));
		}
		
		if (repository.zipCovered(Integer.parseInt(objDto.getRangeEnd())).isPresent()) {
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
