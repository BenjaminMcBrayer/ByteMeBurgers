import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author allison.farr; edited by benjamin.mcbrayer
 *
 */
public class CreditCardPayment extends Payment {
	private Scanner scnr;

	private String cardNumber;
	private String expiry;
	private String cvv;
	private String cardCompany;

	public CreditCardPayment() {
		scnr = new Scanner(System.in);
	}

	public CreditCardPayment(double amount) {
		super(amount);
	}

	public CreditCardPayment(Double amount, String cardNumber, String expiry, String cvv) {
		super(amount);
		this.cardNumber = cardNumber;
		this.expiry = expiry;
		this.cvv = cvv;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = Long.toString(Validator.getLong(scnr, "Please, enter the card number: "));
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = Validator.getString(scnr, "Please, enter the expiration date: ");
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = Integer.toString(Validator.getInt(scnr, "Please, enter the security code: "));
	}

	/**
	 * @return the cardCompany
	 */
	public String getCardCompany() {
		return cardCompany;
	}

	/**
	 * @param cardCompany the cardCompany to set
	 */
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}

	/* (non-Javadoc)
	 * @see Payment#acceptPayment()
	 */
	@Override
	public boolean acceptPayment() {
		CreditCardPayment creditCardPayment = new CreditCardPayment();
		creditCardPayment.setCardNumber(cardNumber);
		creditCardPayment.setCvv(cvv);
		creditCardPayment.setExpiry(expiry);
		String cardCompany = CreditCard.getMatchingCreditCard(cardNumber, cvv);
		if (cardCompany != null) {
			if (isValidLuhn(cardNumber)) {
				if (isValidDate(expiry)) {
					return true;
				}
			}
			System.out.println("Payment method: " + cardCompany + ".\nApproved.");
		} else {
			System.out.println("This is not a valid card");
			return false;
		}
		return false;
	}

	/**
	 * @param String cardNumber
	 * @return boolean
	 */
	// Luhn algorithm.
	public static boolean isValidLuhn(String cardNumber) {
		int cardSum = 0;
		int i;
		int digit = 0;
		boolean isValid = false;

		String[] digits = cardNumber.split("");
		for (i = 0; i < digits.length; i++) {

			// Getting numbers in reverse order.
			digit = Integer.parseInt(digits[digits.length - i - 1]);

			// Multiply by 2 if odd.
			if (i % 2 == 1) {
				digit *= 2;

				// If sum has 2 digits, subtract 9.
				if (digit > 9) {
					digit = digit - 9;
				}
			}
			cardSum += digit;
		}

		// To be valid, the sum of the digits must be divisible by 10.
		if (cardSum % 10 == 0) {
			isValid = true;
		}
		return isValid;
	}

	/**
	 * @param String expirationDate
	 * @return boolean
	 */
	public static boolean isValidDate(String expirationDate) {
		boolean isValid = false;

		// Separate user input into month and year.
		String[] date = expirationDate.split("/");
		int userMonth = Integer.parseInt(date[0]);
		int userYear = Integer.parseInt(date[1]);

		// Get current month and year.
		LocalDate todaysDate = LocalDate.now();
		int monthNow = todaysDate.getMonthValue();
		String fullYearNow = Integer.toString(todaysDate.getYear());
		int yearNow = Integer.parseInt(fullYearNow.substring(2, 4));

		if (userYear >= yearNow && userMonth >= monthNow) {
			isValid = true;
		}
		return isValid;
	}

}