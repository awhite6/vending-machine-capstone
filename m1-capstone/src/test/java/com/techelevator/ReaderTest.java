package com.techelevator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import org.junit.Assert;

public class ReaderTest {
	Reader reader;
	
	@Before
	public void setup() {
		reader = new Reader();
	}
	
	@Test
	public void item_creation_test() {
		List<Item> list = reader.getItems();
		Assert.assertEquals("Potato Crisps", list.get(0).getName());
		Assert.assertEquals("D1", list.get(12).getSlotIdentifier());
		Assert.assertEquals("1.75", list.get(7).getPrice());
	}
}
