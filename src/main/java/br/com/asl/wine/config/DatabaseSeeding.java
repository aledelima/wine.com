package br.com.asl.wine.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.asl.wine.model.Store;
import br.com.asl.wine.model.Ziprange;
import br.com.asl.wine.repository.StoreRepository;
import br.com.asl.wine.repository.ZiprangeRepository;

import java.util.Arrays;

@Configuration
public class DatabaseSeeding implements CommandLineRunner {

	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	ZiprangeRepository zipRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		//LOJA_PINHEIROS|10000000|20000000AND20000001|30000000
		Store pinheiros = Store.builder()
				.storeCode("LOJA_PINHEIROS")
				.address("Bairro dos Pinheiros")
				.phone("2215-3030")
				.build();
		Store jardins = Store.builder()
				.storeCode("LOJA_JARDINS")
				.address("Bairro dos Jardins")
				.phone("2234-5520")
				.build();

		storeRepo.saveAll(Arrays.asList(pinheiros, jardins));

		Ziprange z1 = Ziprange.builder()
						.store(pinheiros)
						.rangeStart(10000000)
						.rangeEnd(20000000)
						.build();

		Ziprange z2 = Ziprange.builder()
						.store(pinheiros)
						.rangeStart(20000001)
						.rangeEnd(30000000)
						.build();

		Ziprange z3 = Ziprange.builder()
						.store(jardins)
						.rangeStart(30000001)
						.rangeEnd(40000000)
						.build();

		Ziprange z4 = Ziprange.builder()
						.store(jardins)
						.rangeStart(40000001)
						.rangeEnd(50000000)
						.build();

		zipRepo.saveAll(Arrays.asList(z1, z2, z3, z4));

	}

}
