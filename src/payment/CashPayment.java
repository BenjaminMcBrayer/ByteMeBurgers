package payment;

import java.util.Scanner;

import menu.FoodItem;
import utility.Validator;

public class CashPayment extends Payment {

	private static Scanner scnr;

	public CashPayment() {
		scnr = new Scanner(System.in);
	}

	public CashPayment(Double amount) {
		super(amount);
	}

	@Override
	public boolean acceptPayment() {
		setAmount(Validator.getDouble(CashPayment.scnr,
				"Please enter cash amount: $", 0.01, 999.99));
		if (getDecimalLength(getAmount()) > 2) {
			System.out.println("The amount you entered is not valid. Please try again.");
			return false;
		} else {
			return true;
		}
	}

	public int getDecimalLength(Double payment) {
		String[] splitter = payment.toString().split("\\.");
		int decimalLength = splitter[1].length();
		return decimalLength;
	}

	public static boolean countCash(Double total, Payment cashPayment, int choice) {
		Double cash = cashPayment.getAmount();
		if (total > cash) {
			total -= cash;
			System.out.println("You still owe " + total + ". How would you like to pay for the remainder?");
			//PaymentUtility.choosePaymentMethod(scnr, payment, total);
			return false;
		} else if (total < cash) {
			System.out.println("Thank you! Your change is $" + getChange(cash, total));
			choice = 0;
			return true;
		} else {
			System.out.println("Perfect change!");
			choice = 0;
			return true;
		}
	}

	public static Double getChange(Double cash, Double total) {
		Double change = 0.0;
		change = Math.abs(total - cash);
		change = Double.parseDouble(FoodItem.formatNumber(change));
		return change;
	}
}
