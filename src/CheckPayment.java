import java.util.Scanner;

/**
 * @author allison.farr; edited by benjamin.mcbrayer
 *
 */
public class CheckPayment extends Payment {
	private Scanner scnr;

	private String checkNumber;

	public CheckPayment() {
		scnr = new Scanner(System.in);
	}

	public CheckPayment(Double amount) {
		super(amount);
	}

	public CheckPayment(Double amount, String checkNumber) {
		super(amount);
		this.checkNumber = checkNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = Validator.getString(scnr, "Please enter the 9-digit check number: ");
	}

	@Override
	public boolean acceptPayment() {
		CheckPayment checkPayment = new CheckPayment();
		checkPayment.setCheckNumber(checkNumber);
		if (checkNumber.matches("\\d{9}")) {
			String[] checkNumbers = checkNumber.split("");
			int[] checkDigits = new int[checkNumbers.length];
			for (int i = 0; i < checkNumbers.length; i++) {
				checkDigits[i] = Integer.parseInt(checkNumbers[i]);
			}
			int sumABA = 3 * (checkDigits[0] + checkDigits[3] + checkDigits[6])
					+ 7 * (checkDigits[1] + checkDigits[4] + checkDigits[7])
					+ (checkDigits[2] + checkDigits[5] + checkDigits[8]);
			if (sumABA % 10 == 0) {
				return true;
			}
		} else {
			System.out.println(checkNumber
					+ " is not a valid check number. Please try again or choose a different form of payment.");
			return false;
		}
		return false;
	}

}
