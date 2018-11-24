package com.techelevator;

public class Drink extends Item {
	public Drink(String[] array) {
		super(array);
	}
	
	public String eatingNoise() {
		return "Glug Glug, Yum!";
	}
}
