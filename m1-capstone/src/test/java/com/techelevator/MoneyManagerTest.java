package com.techelevator;

import org.junit.Before;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

public class MoneyManagerTest {
	MoneyManager tom;
	
	@Before
	public void setup() {
		tom = new MoneyManager();
	}
	
	@Test
	public void feed_money_adds_money_correctly() {
		tom.feedMoney(200);
		Assert.assertEquals(new BigDecimal(200), tom.getCurrentUserMoney());
	}
	
	@Test
	public void subtract_money_subtracts_from_user_money_correctly() {
		tom.feedMoney(200);
		tom.subtractFromCurrentUserTotal("100");
		Assert.assertEquals(new BigDecimal(100), tom.getCurrentUserMoney());
	}
	
	@Test
	public void user_cant_have_negative_dollars() {
		tom.subtractFromCurrentUserTotal("20");
		Assert.assertEquals(new BigDecimal(0), tom.getCurrentUserMoney());
	}
	
	@Test
	public void return_change_returns_correct_amounts() {
		tom.feedMoney(10);
		tom.subtractFromCurrentUserTotal("3.10");
		tom.getChange();
		Assert.assertEquals(27, tom.getQuartersReturned());
		Assert.assertEquals(1, tom.getDimesReturned());
		Assert.assertEquals(1, tom.getNickelsReturned());
	}
}
