package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SalesReportLogger {

	private File salesLog = new File("Lifetime_Sales_Log.txt");
	private Map<String, Integer> mapOfSalesLog = new LinkedHashMap<String, Integer>();
	private BigDecimal total = new BigDecimal(0);
	
	public SalesReportLogger(List<Item> possibleVendingOptions) {
		checkIfLogExists(possibleVendingOptions);
		generateSalesLogMap();
	}
	
	private void checkIfLogExists(List<Item> possibleVendingOptions) {
		if (salesLog.exists() == false) {
			try {
				createNewFile(possibleVendingOptions);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createNewFile(List<Item> possibleVendingOptions) throws IOException {
		salesLog.createNewFile();
		try(PrintWriter writer = new PrintWriter(salesLog)) {
			for (Item item : possibleVendingOptions) {
				writer.println(item.getName() + "|" + "0");
			}
			writer.println();
			writer.print("**TOTAL SALES** $0");
		}
	}
	
	private void createNewFile(Map<String, Integer> salesMap) throws IOException {
		salesLog.createNewFile();
		try(PrintWriter writer = new PrintWriter(salesLog)) {
			for (String s : salesMap.keySet()) {
				writer.println(s + "|" + salesMap.get(s));
			}
			writer.println();
			writer.print("**TOTAL SALES** $" + total);
		}
	}
	
	private void generateSalesLogMap() {
		try (Scanner scanner = new Scanner(salesLog)) {
			while (scanner.hasNextLine()) {			
				String input = scanner.nextLine();
				if (input.length() < 1) {
					continue;
				}
				if (input.contains("TOTAL SALES")) {
					input = input.replaceAll("\\$", "\\|");
					String[] salesLogInfo = input.split("\\|");
					total = total.add(new BigDecimal(salesLogInfo[1]));
					break;
				}
				String[] salesLogInfo = input.split("\\|");
				mapOfSalesLog.put(salesLogInfo[0], Integer.valueOf(salesLogInfo[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void addToSalesMap(String itemDescription, String itemPrice) {
		if (!mapOfSalesLog.keySet().contains(itemDescription)) {
			mapOfSalesLog.put(itemDescription, 1);
			total = total.add(new BigDecimal(itemPrice));
		}
		else {
			mapOfSalesLog.put(itemDescription, mapOfSalesLog.get(itemDescription) + 1);
			total = total.add(new BigDecimal(itemPrice));
		}
	}
	
	public void logToSalesReport(String itemDescription, String itemPrice) {
		addToSalesMap(itemDescription, itemPrice);
		try {
			createNewFile(mapOfSalesLog);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
