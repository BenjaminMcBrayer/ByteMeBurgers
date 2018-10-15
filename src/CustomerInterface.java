import java.util.ArrayList;
import java.util.Scanner;

/**
 * @authors allison.farr, anthony.reakoff, benjamin.mcbrayer, and mike.decoopman
 *
 */
public class CustomerInterface {

	public static void main(String[] args) {
		// Declare and initialize variables.
		Scanner scnr = new Scanner(System.in);
		ArrayList<Employee> empList = new ArrayList<>();
		ArrayList<FoodItem> cart = new ArrayList<>();
		ArrayList<FoodItem> menu = new ArrayList<>();
		Payment payment = null;
		String ans = null;
		String foodName = null;
		String category = null;
		String description = null;
		int choice = 0;
		int num = 0;
		int item = 0;
		int passTry = 4;
		double subtotal = 0.0;
		double total = 0.0;
		double price = 0.0;
		boolean idPass = false;

		System.out.println("Welcome to Byte Me Burgers!\n");
		ans = Validator.getString(scnr, "Hit enter to view our menu.\n");

		// Administrator functions.
		if (ans.equalsIgnoreCase("admin")) {
			ans = Validator.getString(scnr, "Please enter password to access admin privileges: \n");
			if (ans.equalsIgnoreCase("beefaroni")) {
				System.out.println("Welcome to the employee database!");
				do {
					ans = modifyAdminAccess(scnr);
				} while (ans.equalsIgnoreCase("1"));
			} else
				System.out.println("Password invalid. Exiting program.");

			// Management functions (ability to add/delete items).
		} else if (ans.equalsIgnoreCase("manager")) {
			empList = EmployeeFileMethods.readFromFileToArrayList();
			do {
				ans = Validator.getString(scnr, "Please verify ID number: ");
				idPass = EmployeeFileMethods.validateUserID2(ans, empList);
				if (idPass == true) {
					do {
						ans = Validator.getString(scnr, "How would you like to adjust the menu? add/delete/exit: ");
						if (ans.equalsIgnoreCase("add")) {
							System.out.println("Current menu:");
							System.out.println("=============");
							do {
								FoodItemFileMethods.printFoodItems();
								System.out.println();
								foodName = Validator.getString(scnr,
										"What is the name of the new menu item? (Include item number and tab after): ");
								category = Validator.getString(scnr, "New item category (include tab after): ");
								description = Validator.getString(scnr,
										"Brief description of item (include tab after): ");
								price = Validator.getDouble(scnr, "Price of new item (no comma): ", 0,
										Double.MAX_VALUE);
								FoodItem f = new FoodItem(foodName, category, description, price);
								EmployeeFileMethods.addItemToMenu(f);
								System.out.println();
								System.out.println("Updated menu:");
								FoodItemFileMethods.printFoodItems();
								System.out.println();
								ans = Validator.getString(scnr, "Would you like to add another item? y/n: ");
							} while (ans.equalsIgnoreCase("y"));
							System.out.println("Exiting program.");

						} else if (ans.equalsIgnoreCase("delete")) {
							System.out.println("Current menu:");
							System.out.println("=============");
							FoodItemFileMethods.printFoodItems();
							do {
								System.out.println();
								ans = Validator.getString(scnr, "Which item would you like to delete? ");
								EmployeeFileMethods.deleteItemFromMenu(ans);
								System.out.println("Updated menu:");
								FoodItemFileMethods.printFoodItems();
								ans = Validator.getString(scnr, "Would you like to delete another item? y/n: ");
							} while (ans.equalsIgnoreCase("y"));
							System.out.println("Exiting program.");

						} else if (ans.equalsIgnoreCase("exit")) {
							System.out.println("Exiting program.");
						} else {
							System.out.println("Input not recognized. Please try again.");
							System.out.println();
							ans = "2";
						}
						// giving user 3 tries to enter the correct password (security protocol)
					} while (ans.equalsIgnoreCase("2"));
					passTry = 0;
				} else {
					passTry = passTry - 1;
					if (passTry > 1) {
						System.out.println("Password invalid. " + passTry + " tries remaining. Please try again.");
						System.out.println();
					} else if (passTry == 1) {
						System.out.println("Password invalid. " + passTry + " try remaining. Please try again.");
						System.out.println();
					} else {
						System.out.println(
								"Password not recognized. Call customer support for lost password at (313) 555-1212");
						System.out.println("Terminating program.");
					}
				}
			} while (passTry > 0);

		} else {
			do {

				menu = FoodItemFileMethods.readFromFile2();
				FoodItemFileMethods.displayMenu(menu);
				item = Validator.getInt(scnr, "Enter number of item would you like to order: ", 1, menu.size());
				num = Validator.getInt(scnr, "How many orders?: ", 1, Integer.MAX_VALUE);
				ans = Validator.getString(scnr,
						"You selected " + num + " " + menu.get(item - 1).getName() + "(s). Add item(s) to cart? y/n ");
				if (ans.equalsIgnoreCase("y")) {
					System.out.println(num + " " + menu.get((item - 1)).getName() + "(s) added to cart");
					cart.add(menu.get((item - 1)));
					cart.get((cart.size() - 1)).setPrice((cart.get((cart.size() - 1)).getPrice() * num));
				}
				ans = Validator.getString(scnr, "Would you like to order anything else? y/n: ");
			} while (ans.equalsIgnoreCase("y"));
			System.out.println("Thank you for your order! Here is your bill:");
			System.out.println();
			for (int i = 0; i < cart.size(); i++) {
				System.out.println(cart.get(i).getName() + "    " + cart.get(i).getPrice());
				subtotal += cart.get(i).getPrice();
			}
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
	 * @param scnr
	 * @return
	 */
	private static String modifyAdminAccess(Scanner scnr) {
		String ans;
		ans = Validator.getString(scnr, "How would you like to modify management permissions? Add/Delete/Exit: ");
		if (ans.equalsIgnoreCase("add")) {
			do {
				ans = grantAdminAccess(scnr);
			} while (ans.equalsIgnoreCase("y"));
			System.out.println("Exiting program.");
		} else if (ans.equalsIgnoreCase("delete")) {
			EmployeeFileMethods.printEmployeeList();
			do {
				ans = revokeAdminAccess(scnr);
			} while (ans.equalsIgnoreCase("y"));
			System.out.println("Exiting program.");
		} else if (ans.equalsIgnoreCase("exit")) {
			System.out.println("Exiting program.");
		} else {
			System.out.println("Please select valid answer, or 'exit' to terminate program.\n");
			ans = "1";
		}
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static String revokeAdminAccess(Scanner scnr) {
		String ans;
		System.out.println();
		ans = Validator.getString(scnr, "Which manager would you like to remove? ");
		EmployeeFileMethods.deleteEmployeeFromFile(ans);
		System.out.println();
		System.out.println("Updated manager list:");
		EmployeeFileMethods.printEmployeeList();
		System.out.println();
		ans = Validator.getString(scnr, "Would you like to delete another manager? y/n ");
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	private static String grantAdminAccess(Scanner scnr) {
		String ans;
		String newEmp;
		String idNum;
		newEmp = Validator.getString(scnr, "Enter name of new manager: ");
		idNum = Validator.getString(scnr, "Enter ID number for new manager: ");
		Employee e1 = new Employee(idNum, newEmp);
		EmployeeFileMethods.writeToFile(e1);
		System.out.println();
		EmployeeFileMethods.printEmployeeList();
		ans = Validator.getString(scnr,
				newEmp + " has been given management permissions. Would you like to add another manager? y/n ");
		return ans;
	}

	/**
	 * @param payment
	 * @param subtotal
	 * @return
	 */
	private static double calculateTotal(double subtotal) {
		double total;
		double tax;
		System.out.println("Subtotal: " + subtotal);
		tax = subtotal * 0.06;
		System.out.println("Tax: " + tax);
		total = subtotal + tax;
		System.out.println("Grand total: " + total + "\n");
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
		choice = Validator.getInt(scnr, "Please select method of payment:\n1. Cash\n2. Card\n3. Check");
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
