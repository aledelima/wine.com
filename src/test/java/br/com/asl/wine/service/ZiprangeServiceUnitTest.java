package br.com.asl.wine.service;

import br.com.asl.wine.model.Ziprange;
import br.com.asl.wine.repository.ZiprangeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ZiprangeServiceUnitTest {
    @Mock
    ZiprangeRepository zipRepo;

    @InjectMocks
    ZiprangeService ziprangeService;

    @Test
    public void findByIdFound() {
        Integer id = 1;
        Ziprange zipEntity = new Ziprange();

    }
}
