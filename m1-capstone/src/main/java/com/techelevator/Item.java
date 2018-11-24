package com.techelevator;

public abstract class Item {
	private String slotIdentifier;
	private String type;
	private String name;
	private String price;
	
	
	public Item(String[] array) {
		this.slotIdentifier = array[0];
		this.type = array[1];
		this.name = array[2];
		this.price = array[3];
	}
	
	public abstract String eatingNoise();
	
	public String getSlotIdentifier() {
		return slotIdentifier;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}
	
}
