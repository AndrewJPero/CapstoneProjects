package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;

public class VendingMachineCLI {

	VendingMachine newVendingMachine = new VendingMachine();

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};


	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				newVendingMachine.listInventory();
				System.out.println("************************************************");
			}else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {  //can make its own method (Easier to Read)
					System.out.println("Current Money Provided:  $" + newVendingMachine.getBalance());
					 choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						newVendingMachine.methodFeedMoney();
						System.out.println("************************************************");
					}else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						newVendingMachine.listInventory();
						System.out.println("************************************************");
						newVendingMachine.selectForSlot();
						System.out.println("************************************************");
					}else if(choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						newVendingMachine.methodFinishTransaction();
						System.out.println("************************************************");
						break;
					}
				}
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
