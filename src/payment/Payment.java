package payment;

import menu.FoodItem;

public abstract class Payment {

	private Double amount;

	public Payment() {
		amount = 0.00;
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

	public String getFormattedAmount() {
		return FoodItem.formatNumber(getAmount());
	}

	@Override
	public String toString() {
		return "Please pay $" + this.getFormattedAmount();
	}
}
