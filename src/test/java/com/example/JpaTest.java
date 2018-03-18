package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.repository.SearchAddressRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaTest {

	private static final Logger log = LoggerFactory.getLogger(JpaTest.class);

	@Autowired
	SearchAddressRepository searchAddressRepository;

	@Before
	public void setUp() {
	}

	@Test
	public void 리턴값() {
		log.debug("Query : {}", searchAddressRepository.findByValue("서울특별시"));
	}

}