import java.util.ArrayList;

public class FoodItem {

	private String name;
	private Category category;
	private String description;
	private double price;

	public FoodItem() {

	}

	public FoodItem(String name, Category category, String description, double price) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public static ArrayList<FoodItem> createMenu() {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		// added initial menu items
		foodItems.add(new FoodItem("Byte Burger", Category.Burgers, "2 Angus Beef Patties, Cheese, Lettuce, Pickles, Special Byte Sauce.", 6.99));
		foodItems.add(new FoodItem("Binary Burger", Category.Burgers, "Angus Beef Patty, Cheese, Lettuce, Tomato, Onion, Pickles, Mustard, Mayo, Ketchup, Brioche Bun", 5.99));
		foodItems.add(new FoodItem("Short Burger", Category.Burgers, "Small Patty, Mustard, Ketchup, Pickles", 3.99));
		foodItems.add(new FoodItem("Decimal Burger", Category.Burgers, "Angus Patty, Swiss Cheese, Grilled Onions, Mushrooms", 3.99));
		foodItems.add(new FoodItem("Chirpus Burger", Category.Burgers, "Chicken Breast, Lettuce, Mayo, Pickles, Brioche Buns", 7.99));
		foodItems.add(new FoodItem("CharAt(0) Burger", Category.Burgers, "Flame Broiled Patty, Cheese, Lettuce, Tomato, Onion, Pickles, Mustard, Mayo, Ketchup, Brioche Bun", 6.99));
		foodItems.add(new FoodItem("Binary Fries", Category.Sides, "Hand Cut Fries Shaped Like Binary 1's", 2.99));
		foodItems.add(new FoodItem("Binary Rings", Category.Sides, "Hand Cut Onion Rings Shaped Like O's", 3.99));
		foodItems.add(new FoodItem("String Cheesesticks", Category.Sides, "Deep Fried Mozzarella Cheesesticks with Ranch or Marinara", 5.99));
		foodItems.add(new FoodItem("Sys Out Salad", Category.Salads, "Greens, Chicken Breast, Cheddar, Bacon, Choice of Dressing", 9.99));
		foodItems.add(new FoodItem("Array Salad", Category.Salads, "An Array of Delicious Veggies, Choice of Dressing", 8.99));
		foodItems.add(new FoodItem("Super Class Salad", Category.Salads, "All The Fixins' and More!", 10.99));
		foodItems.add(new FoodItem("Concatenation Cola", Category.Drinks, "Ice Code Soda", 1.99));
		foodItems.add(new FoodItem("Diet Concatenation Cola", Category.Drinks, "Ice Code Soda", 1.99));
		foodItems.add(new FoodItem("Byte Bottled Water", Category.Drinks, "Delicious burger!", 1.99));
		foodItems.add(new FoodItem("Java Shake", Category.Sweets, "Coffee Flavored Ice Cream Shake", 3.99));
		foodItems.add(new FoodItem("Apple PI", Category.Sweets, "Hot Slice of Apple Pie With Vanilla Ice Cream", 5.99));	
		foodItems.add(new FoodItem("Ice Code Tea", Category.Drinks, "Delicious Sweet Tea With Lemon", 3.99));
		
		
		return foodItems;

	}

	@Override
	public String toString() {
		return name + "," + category + "," + description + "," + price;

	}

}