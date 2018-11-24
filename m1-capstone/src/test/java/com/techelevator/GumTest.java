package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GumTest {

	Gum gum;
	
	@Before
	public void setup() {
		String[] array = new String[] { "slot", "name", "type", "price" };
		gum = new Gum(array);
	}
	
	@Test
	public void eating_noise_returns_string() {
		Assert.assertEquals("Chew Chew, Yum!", gum.eatingNoise());
	}
	
}
