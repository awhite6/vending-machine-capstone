package com.techelevator;
import java.math.BigDecimal;

public class MoneyManager {
	private int totalMoneyFed;
	private BigDecimal currentUserMoney = new BigDecimal(0);
	private int quartersReturned;
	private int dimesReturned;
	private int nickelsReturned;
	private int penniesReturned;
	
	public BigDecimal getCurrentUserMoney() {
		return currentUserMoney;
	}
	
	public int getTotalMoneyFed() {
		return totalMoneyFed;
	}
	
	public int getQuartersReturned() {
		return quartersReturned;
	}

	public int getDimesReturned() {
		return dimesReturned;
	}

	public int getNickelsReturned() {
		return nickelsReturned;
	}

	public int getPenniesReturned() {
		return penniesReturned;
	}

	public void feedMoney(int money) {
		currentUserMoney = currentUserMoney.add(new BigDecimal(money));
		totalMoneyFed += Integer.valueOf(money);
	}
	
	public void subtractFromCurrentUserTotal(String itemPrice) {
		BigDecimal price = new BigDecimal(itemPrice);
		currentUserMoney = currentUserMoney.subtract(price);
		
		if (currentUserMoney.compareTo(new BigDecimal(0)) <= 0) {
			currentUserMoney = new BigDecimal(0);
		}
	}
	
	private void calculateCorrectAmountOfChange() {
		while (currentUserMoney.compareTo(new BigDecimal(0.04d)) > 0) {
			
			if (currentUserMoney.compareTo(new BigDecimal("20")) >= 0) {
				quartersReturned += 80;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("20"));
			} 
			else if (currentUserMoney.compareTo(new BigDecimal("10")) >= 0) {
				quartersReturned += 40;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("10"));
			} 
			else if (currentUserMoney.compareTo(new BigDecimal("5")) >= 0) {
				quartersReturned += 20;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("5"));
			}
			else if (currentUserMoney.compareTo(new BigDecimal("1")) >= 0) {
				quartersReturned += 4;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("1"));
			} 
			else if (currentUserMoney.compareTo(new BigDecimal("0.25")) >= 0) {
				quartersReturned++;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("0.25"));
			} 
			else if (currentUserMoney.compareTo(new BigDecimal("0.10")) >= 0) {
				dimesReturned++;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("0.10"));
			} 
			else if (currentUserMoney.compareTo(new BigDecimal("0.05")) >= 0) {
				nickelsReturned++;
				currentUserMoney = currentUserMoney.subtract(new BigDecimal("0.05"));
			}	
		}
	}
	
	public void getChange() {
		quartersReturned = 0;
		dimesReturned = 0;
		nickelsReturned = 0;
		penniesReturned = 0;
		
		calculateCorrectAmountOfChange();
		
		currentUserMoney = new BigDecimal(0);
	}
	
	
}
