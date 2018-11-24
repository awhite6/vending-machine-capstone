package com.techelevator;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class TransactionAuditLogger {

	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
	private Date date;
	
	public String getCurrentDate() {
		date = new Date();
		String currentDate = formatter.format(date);
		return currentDate;
	}

	public void logFeed(String feedAmount, BigDecimal currentUserMoney) {
		try (FileWriter fileWriter = new FileWriter("Log.txt", true); //let's us append to the file if it exists
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); //safer option since it's buffered
				PrintWriter out = new PrintWriter(bufferedWriter)) //gives us print functionality from its methods
		{
			out.printf(getCurrentDate() + " %-23s" + " $%3.2f" + " $%3.2f", "FEED MONEY: ", new BigDecimal(feedAmount), currentUserMoney);
			out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logVend(String itemDescription, String itemChoice, String itemPrice, BigDecimal currentUserMoney) {
		try (FileWriter fileWriter = new FileWriter("Log.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter out = new PrintWriter(bufferedWriter))
		{
			String itemNameAndSlot = itemDescription + "  " + itemChoice;
			out.printf(getCurrentDate() + " %-22s" + " " + " $" + (currentUserMoney.add(new BigDecimal(itemPrice))) + " $" + currentUserMoney, itemNameAndSlot);
			out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void logChange(BigDecimal currentUserMoney) {
		try (FileWriter fileWriter = new FileWriter("Log.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				PrintWriter out = new PrintWriter(bufferedWriter))
		{
			out.printf(getCurrentDate() +" %-23s" + " $" + currentUserMoney + " $0.00", "GIVE CHANGE:");
			out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
