package br.com.asl.wine.repository;

import br.com.asl.wine.model.Store;
import br.com.asl.wine.model.Ziprange;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ZiprangeRepositoryTest {


    @Autowired
    StoreRepository  storeRepo;

    @Autowired
    ZiprangeRepository zipRepo;

    Store storePinheiros = br.com.asl.wine.model.Store.builder()
            .storeCode("LOJA_PINHEIROS")
            .address("Rua Dos Pinheiros, Número 1")
            .phone("(11) 1111-1111")
            .build();

    Store storeJardins = br.com.asl.wine.model.Store.builder()
            .storeCode("LOJA_JARDINS")
            .address("Rua Dos Jardins, Número 2")
            .phone("(22) 2222-2222")
            .build();

    Ziprange zPinheiros1 = Ziprange.builder()
            .store(storePinheiros)
            .rangeStart(10000000)
            .rangeEnd(20000000)
            .build();

    Ziprange zPinheiros2 = Ziprange.builder()
            .store(storePinheiros)
            .rangeStart(20000001)
            .rangeEnd(30000000)
            .build();

    Ziprange zJardins1 = Ziprange.builder()
            .store(storeJardins)
            .rangeStart(30000001)
            .rangeEnd(40000000)
            .build();

    Ziprange zJardins2 = Ziprange.builder()
            .store(storeJardins)
            .rangeStart(40000001)
            .rangeEnd(50000000)
            .build();


    @BeforeEach
    void beforeEach() {
        storeRepo.saveAll(Arrays.asList(storePinheiros, storeJardins));
    }

    @Test
    void save_CreateNewZiprange_WhenSuccessfully() {
        Store store = storeRepo.save(storePinheiros);
        Assertions.assertTrue(zipRepo.findAll().isEmpty());
        zipRepo.save(zPinheiros1);
        Assertions.assertFalse(zipRepo.findAll().isEmpty());
    }

    @Test
    void save_CreateNewZiprange_WhenStoreDoesNotExist() {
//        Store store = storeRepo.save(storePinheiros);
        storeRepo.delete(storePinheiros);
        Assertions.assertTrue(zipRepo.findAll().isEmpty());
        zipRepo.save(zPinheiros1);
        Assertions.assertFalse(zipRepo.findAll().isEmpty());
    }

}
