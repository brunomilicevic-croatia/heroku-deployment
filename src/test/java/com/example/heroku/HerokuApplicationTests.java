package com.example.heroku;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class HerokuApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void shouldFailSometimes() {
		Random r = new Random();
		assertEquals(0, r.nextInt(99) % 2);
	}

}

