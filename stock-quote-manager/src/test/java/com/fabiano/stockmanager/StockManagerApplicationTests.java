package com.fabiano.stockmanager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import com.fabiano.stockquotemanager.StockManagerApplication;

@Profile("test")
@SpringBootTest(classes = StockManagerApplication.class)
class StockManagerApplicationTests {

	@Test
	void contextLoads() {
	}

}
