package utility;

import java.util.List;
import java.util.Scanner;

import menu.FoodItem;
import payment.CashPayment;
import payment.CheckPayment;
import payment.CreditCardPayment;
import payment.Payment;

/**
 * @author benjamin.mcbrayer
 *
 */
public class PaymentUtility {
	/**
	 * @param cart
	 * @param subtotal
	 * @return
	 */
	public static double displayBill(List<FoodItem> cart, double subtotal) {
		System.out.println("Thank you for your order! Here is your bill: \n");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + "\t$" + cart.get(i).getFormattedPrice());
			subtotal += cart.get(i).getPrice();
		}
		return subtotal;
	}

	/**
	 * @param payment
	 * @param subtotal
	 * @return
	 */
	public static double calculateTotal(double subtotal) {
		double total = 0;
		double tax = 0;
		System.out.println("Subtotal: $" + FoodItem.formatNumber(subtotal));
		tax = subtotal * 0.06;
		System.out.println("Tax: $" + FoodItem.formatNumber(tax));
		total = subtotal + tax;
		System.out.println("Grand total: $" + FoodItem.formatNumber(total) + "\n");
		return total;
	}

	public static int choosePaymentMethod(Scanner scnr, double total) {
		Payment payment;
		int choice = 0;
		choice = Validator.getInt(scnr, "Please select method of payment:\n1. Cash\n2. Card\n3. Check\n");
		if (choice == 1) {
			payment = new CashPayment();
			payment.acceptPayment();
			CashPayment.countCash(total, payment, choice);
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
