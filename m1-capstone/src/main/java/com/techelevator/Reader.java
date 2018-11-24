package com.techelevator;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
	private String filePath = "vendingmachine.csv";
	
	public List<Item> getItems() {
		
		File file = new File(filePath);
		List<Item> itemList = new ArrayList<Item>();
		
		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				
				String input = scanner.nextLine();
				String[] itemInfo = input.split("\\|");
				
				switch (itemInfo[1]) {
				case "CHIPS":
					itemList.add(new Chips(itemInfo));
					break;
				case "CANDY":
					itemList.add(new Candy(itemInfo));
					break;
				case "DRINK":
					itemList.add(new Drink(itemInfo));
					break;
				case "GUM":
					itemList.add(new Gum(itemInfo));
					break;
				}
			
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return itemList;
	}
}
