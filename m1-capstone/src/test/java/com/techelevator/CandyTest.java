package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CandyTest {
	Candy candy;
	
	@Before
	public void setup() {
		String[] array = new String[] { "slot", "name", "type", "price" };
		candy = new Candy(array);
	}
	
	@Test
	public void eating_noise_returns_string() {
		Assert.assertEquals("Munch Munch, Yum!", candy.eatingNoise());
	}

}
