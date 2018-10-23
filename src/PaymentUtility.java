import java.util.List;
import java.util.Scanner;

public class PaymentUtility {
	/**
	 * @param cart
	 * @param subtotal
	 * @return
	 */
	public static double displayBill(List<FoodItem> cart, double subtotal) {
		System.out.println("Thank you for your order! Here is your bill: \n");
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + "\t" + cart.get(i).getFormattedPrice());
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
		System.out.println("Subtotal: $" + Payment.formatAmount(subtotal));
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
		int choice = 0;
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
