package br.com.asl.wine.repository;

import br.com.asl.wine.model.Store;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StoreRepositoryTest {

    Store storeFullfilled1 = Store.builder()
            .storeCode("LOJA_TEST1")
            .address("Rua da Loja de Teste1, Número 1")
            .phone("(11) 1111-1111")
            .build();

    Store storeFullfilled2 = Store.builder()
            .storeCode("LOJA_TEST2")
            .address("Rua da Loja de Teste2, Número 2")
            .phone("(22) 2222-2222")
            .build();

    Store storeWithNoCode = Store.builder()
            .address("Rua da Loja de Teste0, Número 0")
            .phone("(00) 0000-0000")
            .build();

    Store storeRepeated = Store.builder()
            .storeCode("LOJA_TEST1")
            .address("Rua da Loja de Teste1, Número 1")
            .phone("(11) 1111-1111")
            .build();

    Store storeUpdateFullFilled1 = Store.builder()
            .id(1)
            .storeCode("LOJA_TEST1")
            .address("Rua da Loja de Teste1, Número 1 updated")
            .phone("(11) 1111-1111 updated")
            .build();

    @Autowired
    StoreRepository repo;

    @Test
    void save_CreateNewStore_WhenSuccessfully()  {
        Assertions.assertFalse(repo.findByStoreCode(storeFullfilled1.getStoreCode()).isPresent());
        this.repo.save(storeFullfilled1);
        Assertions.assertTrue(repo.findByStoreCode(storeFullfilled1.getStoreCode()).isPresent());
    }

    @Test
    void save_CreateNewStoreWithNoCode_WhenSuccessfully()  {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> this.repo.save(storeWithNoCode));

    }

    @Test
    void save_CreateNewStore_WhenRepeatedStoreCode()  {
        repo.save(storeFullfilled1);
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> repo.save(storeRepeated));
    }

    @Test
    void save_UpdateFullFilled_WhenRepeatedStoreCode()  {
        repo.save(storeFullfilled1);
        Store updatedStore = repo.save(storeUpdateFullFilled1);
        Assertions.assertTrue(updatedStore.getId().equals(storeUpdateFullFilled1.getId()));
        Assertions.assertTrue(updatedStore.getStoreCode().equals(storeUpdateFullFilled1.getStoreCode()));
        Assertions.assertTrue(updatedStore.getPhone().equals(storeUpdateFullFilled1.getPhone()));
        Assertions.assertTrue(updatedStore.getAddress().equals(storeUpdateFullFilled1.getAddress()));
    }

    @Test
    void delete_DeleteStore_WhenDeletedSuccessfully()  {
        Store savedStore = repo.save(storeUpdateFullFilled1);
        Assertions.assertTrue(repo.findByStoreCode(storeUpdateFullFilled1.getStoreCode()).isPresent());
        repo.delete(savedStore);
        Assertions.assertFalse(repo.findByStoreCode(storeUpdateFullFilled1.getStoreCode()).isPresent());
    }

    @Test
    void delete_DeleteStore_WhenStoreNotFound()  {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> repo.deleteById(1));
    }

}
