package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InventoryTest {

	Inventory inventory;
	
	@Before
	public void setup() {
		inventory = new Inventory();
	}
	
	@Test
	public void inventory_correctly_pulls_from_reader() {
		Assert.assertEquals("Potato Crisps", inventory.getVendingOptions().get(0).getName());
		Assert.assertEquals("D1", inventory.getVendingOptions().get(12).getSlotIdentifier());
		Assert.assertEquals("1.75", inventory.getVendingOptions().get(7).getPrice());
	}
	
	@Test
	public void inventory_stocks_correctly() {
		Assert.assertEquals("1.75", inventory.getVendingInventory().get("A2").get(0).getPrice());
		Assert.assertEquals("Dr. Salt", inventory.getVendingInventory().get("C2").get(0).getName());
		Assert.assertEquals("CANDY", inventory.getVendingInventory().get("B4").get(0).getType());
		Assert.assertEquals(5,  inventory.getRemainingStock("D3"));
	}
	
	@Test
	public void inventory_decrements_available_stock_on_vend() {
		inventory.vendItem("D3");
		Assert.assertEquals(4,  inventory.getRemainingStock("D3"));
	}
	
	@Test
	public void get_remaining_inventory_returns_correct_value() {
		Assert.assertEquals(5, inventory.getRemainingStock("A1"));
		inventory.vendItem("A1");
		Assert.assertEquals(4, inventory.getRemainingStock("A1"));
	}
	
	@Test
	public void get_price_of_item_returns_correct_price() {
		Assert.assertEquals("1.75", inventory.getItemPrice("A2"));
	}
	
	@Test
	public void get_item_description_returns_correct_description() {
		Assert.assertEquals("Dr. Salt",  inventory.getItemDescription("C2"));
	}
}
