package application;

import java.util.ArrayList;
import java.util.Scanner;

import menu.FoodItem;
import payment.Employee;
import utility.AdministratorUtility;
import utility.EmployeeUtility;
import utility.MenuUtility;
import utility.PaymentUtility;
import utility.Validator;

/**
 * @authors allison.farr, anthony.reakoff, benjamin.mcbrayer, and mike.decoopman
 *
 */
public class TerminalInterface {

	public static void main(String[] args) {
		// Declare and initialize variables.
		Scanner scnr = new Scanner(System.in);
		ArrayList<Employee> employeeList = new ArrayList<>();
		ArrayList<FoodItem> cart = new ArrayList<>();
		ArrayList<FoodItem> menu = new ArrayList<>();
		String ans = null;
		int choice = 0;
		int num = 0;
		int item = 0;
		int passTry = 4;
		double subtotal = 0.0;
		double total = 0.0;

		// Welcome users.
		System.out.println("Welcome to Byte Me Burgers!\n");
		ans = Validator.getString(scnr, "Hit enter to view our menu.\n");

		if (ans.equalsIgnoreCase("admin")) { // Display administrator functions.
			ans = Validator.getString(scnr, "Please enter password to access administrator privileges: \n");
			if (ans.equalsIgnoreCase("admin")) {
				System.out.println("Welcome to the Employee Database!");
				AdministratorUtility.modifyAccess(scnr);
				// TODO: Log out and restart program (instead of terminating program).
			} else {
				System.out.println("Password invalid. Exiting program.");
			}

		} else if (ans.equalsIgnoreCase("manager")) { // Display management functions (ability to add/delete items).
			employeeList = EmployeeUtility.readFromFileToArrayList();

			do {
				ans = Validator.getString(scnr, "Please verify ID number: ");
				if (EmployeeUtility.validateUserID(ans, employeeList)) {
					modifyMenu(scnr);
					passTry = 0;
					// TODO: Log out and restart program (instead of terminating program).
				} else {
					passTry = tryPass(passTry);
				}
			} while (passTry > 0);

		} else { // Display customer interface.

			do { // Take customer order.
				menu = MenuUtility.generateCurrentMenu();
				item = Validator.getInt(scnr, "Enter number of item would you like to order: ", 1, menu.size());
				num = Validator.getInt(scnr, "How many orders? ", 1, Integer.MAX_VALUE);
				ans = Validator.getString(scnr,
						"You selected " + num + " " + menu.get(item - 1).getName() + "(s). Add item(s) to cart? (y/n) ");
				if (ans.equalsIgnoreCase("y")) {
					MenuUtility.addItemToOrder(cart, menu, num, item);
				}
				ans = Validator.getString(scnr, "Would you like to order anything else? (y/n) ");
			} while (ans.equalsIgnoreCase("y"));

			// Display subtotal, calculate total, and request payment.
			subtotal = PaymentUtility.displayBill(cart, subtotal);
			total = PaymentUtility.calculateTotal(subtotal);
			do {
				choice = PaymentUtility.choosePaymentMethod(scnr, total);
			} while ((choice == 1 || choice == 2 || choice == 3) == false);
			System.out.println(
					"Thank you for your payment! Your order has been sent to the kitchen. Your name will be called shortly. Have a great day!");
		}
	}

	/**
	 * @param passTry
	 * @return
	 */
	private static int tryPass(int passTry) {
		passTry = passTry - 1;
		if (passTry > 1) {
			System.out.println("Password invalid. " + passTry + " tries remaining. Please try again.\n");
		} else if (passTry == 1) {
			System.out.println("Password invalid. " + passTry + " try remaining. Please try again.\n");
		} else {
			System.out.println("Password not recognized. Call customer support for lost password at (313) 555-1212");
			System.out.println("Terminating program.");
		}
		return passTry;
	}

	/**
	 * @param scnr
	 */
	private static void modifyMenu(Scanner scnr) {
		String ans;
		do {
			ans = Validator.getString(scnr,
					"How would you like to adjust the menu? \n1. Add an item.\n2. Delete an item.\n3. Exit\n");
			if (ans.equals("1")) {
				addItem(scnr);
			} else if (ans.equals("2")) {
				displayMenu();
				deleteItem(scnr);
			} else if (ans.equals("3")) {
				System.out.println("Exiting program.");
				ans = "-1";
			} else {
				System.out.println("Input not recognized. Please try again.\n");
				ans = "-1";
			}
		} while (!(ans.equals("-1")));
	}

	// Management methods.
	/**
	 * @param scnr
	 * @return
	 */
	private static String deleteItem(Scanner scnr) {
		String ans;
		do {
			System.out.println();
			ans = Validator.getString(scnr, "Which item would you like to delete? ");
			EmployeeUtility.deleteItemFromMenu(ans);
			System.out.println("Updated menu:");
			MenuUtility.printItems("CompanyInfo/Menu");
			ans = Validator.getString(scnr, "Would you like to delete another item? y/n: ");
		} while (ans.equalsIgnoreCase("y"));
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static String addItem(Scanner scnr) {
		String ans;
		do {
			displayMenu();
			FoodItem f = createMenuItem(scnr);
			EmployeeUtility.addItemToMenu(f);
			System.out.println("\nUpdated menu:\n");
			MenuUtility.printItems("CompanyInfo/Menu");
			ans = Validator.getString(scnr, "Would you like to add another item? y/n: ");
		} while (ans.equalsIgnoreCase("y"));
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static FoodItem createMenuItem(Scanner scnr) {
		String foodName = Validator.getString(scnr,
				"What is the name of the new menu item? (Include item number and tab after): ");
		String category = Validator.getString(scnr, "New item category (include tab after): ");
		String description = Validator.getString(scnr, "Brief description of item (include tab after): ");
		double price = Validator.getDouble(scnr, "Price of new item (no comma): ", 0, Double.MAX_VALUE);
		FoodItem f = new FoodItem(foodName, category, description, price);
		return f;
	}

	/**
	 * 
	 */
	private static void displayMenu() {
		System.out.println("Current menu:");
		System.out.println("=============");
		MenuUtility.printItems("CompanyInfo/Menu");
		System.out.println();
	}
}
