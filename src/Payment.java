import java.text.DecimalFormat;

public abstract class Payment {

	private Double amount;

	public Payment() {
	}

	public Payment(Double amount) {
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public abstract boolean acceptPayment();

	public String formatAmount(Double amount) {
		DecimalFormat format = new DecimalFormat("###.00");
		String numberAsString = format.format(amount);
		return numberAsString;	
	}

	@Override
	public String toString() {
		return "Please pay $" + formatAmount(amount);
	}
}
