package payment;

/**
 * @author camila.brasil; edited by benjamin.mcbrayer The first regex verifies
 *         the card number; the second validates the security code.
 */
public enum CreditCard {

	MASTERCARD("^([5]{1})([1-5]{1})[0-9]{14}", "^([0-9]{3})"), VISA("^([4]{1})([0-9]{13})", "^([0-9]{3})"),
	DISCOVER("^([6011]{4})([0-9]{12})", "^([0-9]{3})"),
	DINERSCARTEBLANCHE("^([30]{2})([0-5]{1})([0-9]{11})", "^([0-9]{4})"),
	DINERSINTERNATIONAL("^([36]{2})([0-9]{12})", "^([0-9]{4})"),
	DINERSUSACANADA("^([54]{2})([0-9]{14})", "^([0-9]{4})"), AMEX("^([3]{1})([47]{1})([0-9]{13})", "^([0-9]{4})");

	private final String cardRegex;
	private final String codeRegex;

	private CreditCard(String cardRegex, String codeRegex) {
		this.cardRegex = cardRegex;
		this.codeRegex = codeRegex;
	}

	/**
	 * @param cardNumber
	 * @param securityCode
	 * @return
	 */
	// Finding a matching card
	public static String getMatchingCreditCard(String cardNumber, String securityCode) {
		String cardCompany = null;
		System.out.println("\n" + cardNumber);

		if (cardNumber.matches(MASTERCARD.cardRegex) && securityCode.matches(MASTERCARD.codeRegex)) {
			cardCompany = "MasterCard";
		} else if (cardNumber.matches(VISA.cardRegex) && securityCode.matches(VISA.codeRegex)) {
			cardCompany = "Visa";
		} else if (cardNumber.matches(DISCOVER.cardRegex) && securityCode.matches(DISCOVER.codeRegex)) {
			cardCompany = "Discover";
		} else if (cardNumber.matches(DINERSCARTEBLANCHE.cardRegex)
				&& securityCode.matches(DINERSCARTEBLANCHE.codeRegex)) {
			cardCompany = "Diners Carte Blanche";
		} else if (cardNumber.matches(DINERSINTERNATIONAL.cardRegex)
				&& securityCode.matches(DINERSINTERNATIONAL.codeRegex)) {
			cardCompany = "Diners International";
		} else if (cardNumber.matches(DINERSUSACANADA.cardRegex) && securityCode.matches(DINERSUSACANADA.codeRegex)) {
			cardCompany = "Diners USA & Canada";
		} else if (cardNumber.matches(AMEX.cardRegex) && securityCode.matches(AMEX.codeRegex)) {
			cardCompany = "American Express";
		}
		return cardCompany;
	}
}
