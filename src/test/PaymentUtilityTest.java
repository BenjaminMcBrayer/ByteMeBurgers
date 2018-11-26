package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import menu.FoodItem;
import utility.PaymentUtility;

class PaymentUtilityTest {

	@Test
	void test1() {
		List<FoodItem> cart = new ArrayList<>(Arrays.asList(new FoodItem("Byte Me Burger", "Burgers",
				"Angus Beef Patty, Cheese, Lettuce, Pickles, Special Byte Sauce.", 6.99), new FoodItem("Binary Burger", "Burgers",
				"Angus Beef Patty, Cheese, Lettuce, Tomato, Onion, Pickles, Mustard, Mayo, Ketchup, Brioche Bun",
				5.99), new FoodItem("Short Burger", "Burgers", "Small Patty, Mustard, Ketchup, Pickles", 3.99)));
		double subtotal = 0;
		double expected = 16.97;
		double actual = PaymentUtility.displayBill(cart, subtotal);
		assertEquals(actual, expected, 0.0);
	}

}
