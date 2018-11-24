package com.techelevator;

public class Candy extends Item{
	public Candy(String[] array) {
		super(array);
	}
	
	public String eatingNoise() {
		return "Munch Munch, Yum!";
	}
}
