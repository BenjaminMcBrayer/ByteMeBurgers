import java.util.Scanner;

public class CashPayment extends Payment {

	private Scanner scnr;

	public CashPayment() {
		scnr = new Scanner(System.in);
	}

	public CashPayment(Double amount) {
		super(amount);
	}

	@Override
	public boolean acceptPayment() {
		CashPayment cashPayment = new CashPayment();
		cashPayment.setAmount(Validator.getDouble(cashPayment.scnr, "Please enter cash amount (in the format 000.00): $", 0.01,
				999.99));
		if (getDecimalLength(cashPayment.getAmount()) > 2) {
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

	public void countCash(Double total, Double cash, Payment payment) {
		if (total > cash) {
			total -= cash;
			System.out.println("You still owe " + total + ". How would you like to pay for the remainder?");
			CustomerInterface.choosePaymentMethod(scnr, payment, total);
		} else if (total < cash) {
			System.out.println("Thank you! Your change is $" + getChange(cash, total));
		} else {
			System.out.println("Perfect change!");
		}
	}

	public Double getChange(Double cash, Double total) {
		Double change = 0.0;
		change = total - cash; 
		formatAmount(change);
		return change;
	}
}
