package com.techelevator;

import java.math.BigDecimal;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
													   MAIN_MENU_OPTION_PURCHASE };
	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, 
														   PURCHASE_MENU_OPTION_SELECT_PRODUCT, 
														   PURCHASE_MENU_OPTION_FINISH_TRANSACTION };
	
	private static final String[] FEED_MONEY_AMOUNT_OPTIONS = { "1", "2", "5", "10", "20" };
		
	private Menu menu;
	
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}
	
	private void displayItems(Inventory inventory) {
		for (Item item : inventory.getVendingOptions()) {
			inventory.createDisplayOptions(item);
//			System.out.println(inventory.createDisplayOptions(item));
		}
	}
	
	private void displayFeedMoneyMenu(String choice, MoneyManager money, TransactionAuditLogger transactionLogger) {
		while (true) {
			choice = (String)menu.getChoiceFromOptions(FEED_MONEY_AMOUNT_OPTIONS);
			money.feedMoney(Integer.valueOf(choice));							
			transactionLogger.logFeed(choice, money.getCurrentUserMoney());
			break;
		}
	}
	
	private void displayCurrentUserMoney(MoneyManager money) {
		System.out.println();
		System.out.printf("Current User Money: %4.2f", money.getCurrentUserMoney());
		System.out.println();
	}
	
	private void logSaleAndTransaction(Inventory inventory, String choice, MoneyManager money, TransactionAuditLogger transactionLogger, SalesReportLogger salesLogger) {
		transactionLogger.logVend(inventory.getItemDescription(choice), choice, inventory.getItemPrice(choice), money.getCurrentUserMoney());
		salesLogger.logToSalesReport(inventory.getItemDescription(choice), inventory.getItemPrice(choice));
	}
	
	private void vendItem(Inventory inventory, String choice, MoneyManager money) {
		inventory.vendItem(choice);
		money.subtractFromCurrentUserTotal(inventory.getItemPrice(choice));
	}
	
	private boolean isPurchasable(Inventory inventory, MoneyManager money, String choice) {
		if (inventory.getRemainingStock(choice) > 0 && money.getCurrentUserMoney().compareTo(new BigDecimal(Double.valueOf(inventory.getItemPrice(choice)))) >= 0) { 
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isSoldOut(Inventory inventory, String choice) {
		if (inventory.getRemainingStock(choice) < 1) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean canUserAffordItem(Inventory inventory, String choice, MoneyManager money) {
		if (money.getCurrentUserMoney().compareTo(new BigDecimal(Double.valueOf(inventory.getItemPrice(choice)))) <= 0) {
			return false;
		} else {
			return true;
		}
	}
	
	private void buyProduct(Inventory inventory, String choice, MoneyManager money, TransactionAuditLogger transactionLogger, SalesReportLogger salesLogger) {
		if (isPurchasable(inventory, money, choice)) { 

			vendItem(inventory, choice, money);
			logSaleAndTransaction(inventory, choice, money, transactionLogger, salesLogger);


		} else if (isSoldOut(inventory, choice)) {
			
			System.out.println();
			System.out.print("Item is Sold Out");
			System.out.println();
			
		} else if (!canUserAffordItem(inventory, choice, money)) {
			
			System.out.println();
			System.out.println("Not Enough Money For Purchase");
			System.out.println();
		}
	}

	private void displaySelectProductMenu(Inventory inventory, String choice, MoneyManager money, TransactionAuditLogger transactionLogger, SalesReportLogger salesLogger) {
		displayItems(inventory);
		
		choice = (String)menu.getChoiceFromOptions(inventory.getVendingInventory());
		buyProduct(inventory, choice, money, transactionLogger, salesLogger);
		
	}
	
	private void finishTransaction(Inventory inventory, MoneyManager money, TransactionAuditLogger transactionLogger) {
		transactionLogger.logChange(money.getCurrentUserMoney());
		money.getChange();
		System.out.println();
		System.out.println("Quarters Returned: " + money.getQuartersReturned());
		System.out.println("Dimes Returned: " + money.getDimesReturned());
		System.out.println("Nickels Returned: " + money.getNickelsReturned());
		System.out.println("Pennies Returned: " + money.getPenniesReturned());
		System.out.println();
		
		for (Item item : inventory.getVendingTray()) {
			
			System.out.println(item.eatingNoise());
		}
		
		inventory.emptyVendingTray();
	}
	
	private void displayPurchaseMenu(Inventory inventory, String choice, MoneyManager money, TransactionAuditLogger transactionLogger, SalesReportLogger salesLogger) {
		while (true) {
			displayCurrentUserMoney(money);
			

			choice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			
			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {	
				
				displayFeedMoneyMenu(choice, money, transactionLogger);

				
			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				
				displaySelectProductMenu(inventory, choice, money, transactionLogger, salesLogger);

	
			} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				
				finishTransaction(inventory, money, transactionLogger);
				
				break;
			}
		}
	}
	
	public void run() {
		Inventory inventory = new Inventory();
		MoneyManager money = new MoneyManager();
		SalesReportLogger salesLogger = new SalesReportLogger(inventory.getVendingOptions());
		TransactionAuditLogger transactionLogger = new TransactionAuditLogger();
		
		System.out.println("    ___    __           _________            ______  ___      __________           _______________________ ");
		 System.out.println("   __ |  / /_________________  /_____       ___   |/  /_____ __  /___(_)______    ___  ____/_  __ \\_  __ \\");
		 System.out.println("   __ | / /_  _ \\_  __ \\  __  /_  __ \\________  /|_/ /_  __ `/  __/_  /_  ___/    ______ \\ _  / / /  / / /");
		 System.out.println("   __ |/ / /  __/  / / / /_/ / / /_/ //_____/  /  / / / /_/ // /_ _  / / /__       ____/ / / /_/ // /_/ / ");
		 System.out.println("   _____/  \\___//_/ /_/\\__,_/  \\____/       /_/  /_/  \\__,_/ \\__/ /_/  \\___/      /_____/  \\____/ \\____/  ");
	
		
		while(true) {
			
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); 
			
			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				
				displayItems(inventory);

			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				
				displayPurchaseMenu(inventory, choice, money, transactionLogger, salesLogger);

			}
		}
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
