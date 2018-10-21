import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authors allison.farr, anthony.reakoff, benjamin.mcbrayer, and mike.decoopman
 *
 */
public class TerminalInterface {

	public static void main(String[] args) {
		// Declare and initialize variables.
		Scanner scnr = new Scanner(System.in);
		ArrayList<Employee> empList = new ArrayList<>();
		ArrayList<FoodItem> cart = new ArrayList<>();
		ArrayList<FoodItem> menu = new ArrayList<>();
		Payment payment = null;
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

		// Display administrator functions.
		if (ans.equalsIgnoreCase("admin")) {
			ans = Validator.getString(scnr, "Please enter password to access administrator privileges: \n");
			if (ans.equalsIgnoreCase("beefaroni")) {
				System.out.println("Welcome to the Employee Database!");
				modifyAccess(scnr);
				//TODO: Log out and restart program (instead of terminating program).
			} else {
				System.out.println("Password invalid. Exiting program.");
			}
			// Display management functions (ability to add/delete items).
		} else if (ans.equalsIgnoreCase("manager")) {
			empList = EmployeeFileMethods.readFromFileToArrayList();
			do {
				ans = Validator.getString(scnr, "Please verify ID number: ");
				if (EmployeeFileMethods.validateUserID2(ans, empList)) {
					modifyMenu(scnr);
					passTry = 0;
					//TODO: Log out and restart program (instead of terminating program).
				} else {
					passTry = tryPass(passTry);
				}
			} while (passTry > 0);
			// Display customer interface.
		} else {
			do {

				menu = FoodItemFileMethods.readFromFile2();
				FoodItemFileMethods.displayMenu(menu);
				item = Validator.getInt(scnr, "Enter number of item would you like to order: ", 1, menu.size());
				num = Validator.getInt(scnr, "How many orders?: ", 1, Integer.MAX_VALUE);
				ans = Validator.getString(scnr,
						"You selected " + num + " " + menu.get(item - 1).getName() + "(s). Add item(s) to cart? y/n ");
				if (ans.equalsIgnoreCase("y")) {
					addItemToOrder(cart, menu, num, item);
				}
				ans = Validator.getString(scnr, "Would you like to order anything else? y/n: ");
			} while (ans.equalsIgnoreCase("y"));
			// Display subtotal.
			subtotal = displayBill(cart, subtotal);
			// Calculate total.
			total = calculateTotal(subtotal);
			// Request payment.
			do {
				choice = choosePaymentMethod(scnr, payment, total);
			} while ((choice == 1 || choice == 2 || choice == 3) == false);
			System.out.println(
					"Thank you for your payment! Your order has been sent to the kitchen. Your name will be called shortly. Have a great day!");
		}
	}

	/**
	 * @param cart
	 * @param subtotal
	 * @return
	 */
	private static double displayBill(ArrayList<FoodItem> cart, double subtotal) {
		System.out.println("Thank you for your order! Here is your bill: \n");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + "    " + cart.get(i).getPrice());
			subtotal += cart.get(i).getPrice();
		}
		return subtotal;
	}

	/**
	 * @param cart
	 * @param menu
	 * @param num
	 * @param item
	 */
	private static void addItemToOrder(ArrayList<FoodItem> cart, ArrayList<FoodItem> menu, int num, int item) {
		System.out.println(num + " " + menu.get((item - 1)).getName() + "(s) added to cart");
		cart.add(menu.get((item - 1)));
		cart.get((cart.size() - 1)).setPrice((cart.get((cart.size() - 1)).getPrice() * num));
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
			System.out.println(
					"Password not recognized. Call customer support for lost password at (313) 555-1212");
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
			EmployeeFileMethods.deleteItemFromMenu(ans);
			System.out.println("Updated menu:");
			FoodItemFileMethods.printFoodItems();
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
			EmployeeFileMethods.addItemToMenu(f);
			System.out.println("\nUpdated menu:");
			FoodItemFileMethods.printFoodItems();
			System.out.println();
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
		FoodItemFileMethods.printFoodItems();
		System.out.println();
	}

	// Administrator methods.
	/**
	 * @param scnr
	 * @return
	 */
	private static String modifyAccess(Scanner scnr) {
		String ans;
		do {
			ans = Validator.getString(scnr,
					"How would you like to modify management permissions? \n1. Grant \n2. Revoke \n3. Exit\n");
			if (ans.equals("1")) {
				grantAccess(scnr);
			} else if (ans.equals("2")) {
				revokeAccess(scnr);
			} else if (ans.equals("3")) {
				System.out.println("Exiting program.");
				ans = "-1";
			} else {
				System.out.println("Please select a valid option.\n");
				ans = "-1";
			}
		} while (!(ans.equals("-1")));
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static void grantAccess(Scanner scnr) {
		String ans;
		do {
			Employee e = createEmployee(scnr);
			EmployeeFileMethods.writeToFile(e);
			EmployeeFileMethods.printEmployeeList();
			ans = Validator.getString(scnr, e.getName()
					+ " has been given management permissions. Would you like to add another manager? y/n ");
		} while (ans.equalsIgnoreCase("y"));
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static Employee createEmployee(Scanner scnr) {
		String name = Validator.getString(scnr, "Enter name of new manager: ");
		String id = Validator.getString(scnr, "Enter ID number for new manager: ");
		Employee e = new Employee(id, name);
		return e;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static void revokeAccess(Scanner scnr) {
		String ans;
		String name;
		EmployeeFileMethods.printEmployeeList();
		do {
			name = Validator.getString(scnr, "Please enter the name of the manager whom you'd like to remove. \n");
			EmployeeFileMethods.deleteEmployeeFromFile(name);
			System.out.println("Updated manager list:");
			EmployeeFileMethods.printEmployeeList();
			ans = Validator.getString(scnr, "Would you like to delete another manager? y/n ");
		} while (ans.equalsIgnoreCase("y"));
	}

	/**
	 * @param payment
	 * @param subtotal
	 * @return
	 */
	private static double calculateTotal(double subtotal) {
		double total;
		double tax;
		System.out.println("Subtotal: $" + subtotal);
		tax = subtotal * 0.06;
		System.out.println("Tax: $" + Payment.formatAmount(tax));
		total = subtotal + tax;
		System.out.println("Grand total: $" + Payment.formatAmount(total) + "\n");
		return total;
	}

	/**
	 * @param scnr
	 * @param payment
	 * @param total
	 * @return
	 */
	static int choosePaymentMethod(Scanner scnr, Payment payment, double total) {
		int choice;
		choice = Validator.getInt(scnr, "Please select method of payment:\n1. Cash\n2. Card\n3. Check\n");
		if (choice == 1) {
			payment = new CashPayment();
			payment.acceptPayment();
		} else if (choice == 2) {
			payment = new CreditCardPayment();
			payment.acceptPayment();
		} else if (choice == 3) {
			payment = new CheckPayment();
			payment.acceptPayment();
		} else {
			System.out.println("Invalid entry. Please enter 1, 2, or 3.");
		}
		return choice;
	}

}
