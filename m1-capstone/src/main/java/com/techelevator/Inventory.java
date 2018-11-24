package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Inventory {

	private List<Item> vendingOptions = new ArrayList<Item>();
	private Map<String, Stack<Item>> vendingInventory = new HashMap<String, Stack<Item>>();
	private Stack<Item> item;
	private List<String> slotIdentifier = new ArrayList<String>();
	private List<Item> vendingTray = new ArrayList<Item>();
	
	public Inventory() {
		Reader reader = new Reader();
		this.vendingOptions = reader.getItems();
		stockInventory();
		createValidSelectionList();
	}
	
	public List<Item> getVendingTray() {
		return vendingTray;
	}
	
	public List<Item> getVendingOptions() {
		return vendingOptions;
	}
	
	public Map<String, Stack<Item>> getVendingInventory() {
		return vendingInventory;
	}
	
	public void emptyVendingTray() {
		vendingTray = new ArrayList<Item>();
	}

	public void vendItem(String slotIdentifier) {
		//if (getVendingInventory().get(slotIdentifier).size() > 0) {
			vendingTray.add(vendingInventory.get(slotIdentifier).pop());
		//}
		//return ???
	}
	
	public String getItemPrice(String slotIdentifier) {
		return vendingInventory.get(slotIdentifier).get(0).getPrice();
	}
	
	public int getRemainingStock(String slotIdentifier) {
		return vendingInventory.get(slotIdentifier).size() - 1; //so that we can leave one in stack to get the info via map key
	}
	
	public String getItemDescription(String slotIdentifier) {
		return vendingInventory.get(slotIdentifier).get(0).getName();
	}
	
	public void createDisplayOptions(Item item) {
		if (getRemainingStock(item.getSlotIdentifier()) > 0) {
			System.out.printf(item.getSlotIdentifier() + "  %-20s" + " $" + item.getPrice() + "   Qty: " + Integer.toString(getRemainingStock(item.getSlotIdentifier())), item.getName());
			System.out.println();
		} else {
			System.out.printf(item.getSlotIdentifier() + "  %-20s" + " $" + item.getPrice() + "   SOLD OUT" , item.getName());
			System.out.println();
		}
	}
	
	private void createValidSelectionList() {
		for (String s : vendingInventory.keySet()) {
			slotIdentifier.add(s);
		}
	}
	
	private void stockInventory() {
		for (Item vendingItem : vendingOptions) {
			item = new Stack<Item>();
			for (int i = 0; i < 6; i++) { //6 so that one is left in stack to get info from via map key
				item.add(vendingItem);
			}
			vendingInventory.put(vendingItem.getSlotIdentifier(), item);
		}
	}
	
	
	
	
}
