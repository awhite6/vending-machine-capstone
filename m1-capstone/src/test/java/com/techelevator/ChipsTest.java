package com.techelevator;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class ChipsTest {
	Chips chips;
	
	@Before
	public void setup() {
		String[] array = new String[] { "slot", "name", "type", "price" };
		chips = new Chips(array);
	}
	
	@Test
	public void eating_noise_returns_string() {
		Assert.assertEquals("Crunch Crunch, Yum!", chips.eatingNoise());
	}
	
	
}
