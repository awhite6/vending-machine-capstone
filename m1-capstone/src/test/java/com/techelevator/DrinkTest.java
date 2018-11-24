package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DrinkTest {

	Drink drink;
	
	@Before
	public void setup() {
		String[] array = new String[] { "slot", "name", "type", "price" };
		drink = new Drink(array);
	}
	
	@Test
	public void eating_noise_returns_string() {
		Assert.assertEquals("Glug Glug, Yum!", drink.eatingNoise());
	}
	
}
