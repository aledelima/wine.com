package br.com.asl.wine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.asl.wine.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer>{
	
	Optional<Store> findByStoreCode(String storecode);
	
	@Query("SELECT s FROM Store s RIGHT JOIN s.zipRanges z WHERE ?1 BETWEEN z.rangeStart AND z.rangeEnd")
	Optional<Store> findByZipcode(Integer zipcode);	
	
}
