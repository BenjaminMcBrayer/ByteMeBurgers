package menu;

/**
 * @author mike.decoopman; edited by benjamin.mcbrayer
 *
 */
/**
 * @author benja
 *
 */
/**
 * @author benja
 *
 */
public class FoodItem {

	private Long id;
	private String name;
	private String category;
	private String description;
	private Double price;

	public FoodItem() {
	}
	
	public FoodItem(Long id, String name, String category, String description, Double price) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
	}

	public FoodItem(String name, String category, String description, Double price) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
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
	
	public String getFormattedPrice() {
		return formatNumber(getPrice());
	}

	public static String formatNumber(double x) {
		return String.format("%.2f", x);
	}
	/*public static ArrayList<FoodItem> createMenu() {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		foodItems.add(new FoodItem("Byte Me Burger", "Burgers",
				"Angus Beef Patty, Cheese, Lettuce, Pickles, Special Byte Sauce.", 6.99));
		foodItems.add(new FoodItem("Binary Burger", "Burgers",
				"Angus Beef Patty, Cheese, Lettuce, Tomato, Onion, Pickles, Mustard, Mayo, Ketchup, Brioche Bun",
				5.99));
		foodItems.add(new FoodItem("Short Burger", "Burgers", "Small Patty, Mustard, Ketchup, Pickles", 3.99));
		foodItems.add(new FoodItem("Decimal Burger", "Burgers", "Angus Patty, Swiss Cheese, Grilled Onions, Mushrooms",
				3.99));
		foodItems.add(new FoodItem("Chirpus Burger", "Burgers", "Chicken Breast, Lettuce, Mayo, Pickles, Brioche Buns",
				7.99));
		foodItems.add(new FoodItem("CharAt(0) Burger", "Burgers",
				"Flame Broiled Patty, Cheese, Lettuce, Tomato, Onion, Pickles, Mustard, Mayo, Ketchup, Brioche Bun",
				6.99));
		foodItems.add(new FoodItem("Binary Fries", "Sides", "Hand Cut Fries Shaped Like Binary 1's", 2.99));
		foodItems.add(new FoodItem("Binary Rings", "Sides", "Hand Cut Onion Rings Shaped Like Binary O's", 3.99));
		foodItems.add(new FoodItem("String Cheesesticks", "Sides",
				"Deep Fried Mozzarella Cheesesticks with Ranch or Marinara", 5.99));
		foodItems.add(new FoodItem("Sys Out Salad", "Salads",
				"Greens, Chicken Breast, Cheddar, Bacon, Choice of Dressing", 9.99));
		foodItems.add(new FoodItem("Array Salad", "Salads", "An Array of Delicious Veggies, Choice of Dressing", 8.99));
		foodItems.add(new FoodItem("Super Class Salad", "Salads", "All The Fixins' and More!", 10.99));
		foodItems.add(new FoodItem("Concatenation Cola", "Drinks", "Ice Code Soda", 1.99));
		foodItems.add(new FoodItem("Diet Concatenation Cola", "Drinks", "Ice Code Soda", 1.99));
		foodItems.add(new FoodItem("Byte Bottled Water", "Drinks", "Delicious burger!", 1.99));
		foodItems.add(new FoodItem("Java Shake", "Sweets", "Coffee Flavored Ice Cream Shake", 3.99));
		foodItems.add(new FoodItem("Apple PI", "Sweets", "Warm Slice of Apple Pie With Vanilla Ice Cream", 5.99));
		foodItems.add(new FoodItem("Ice Code Tea", "Drinks", "Delicious Sweet Tea With Lemon", 3.99));
		foodItems.add(new FoodItem("Boo-LEAN Burger", "Burgers", "Lean Turkey, Veggies, Lite Special Sauce", 6.99));
		foodItems.add(new FoodItem("Double Byte Me Burger", "Burgers",
				"2 HUGE Angus Beef Patties, Cheese, Lettuce, Pickles, Special Byte Sauce.", 9.99));
		return foodItems;
	}*/

	@Override
	public String toString() {
		return name + "\t" + category + "\t" + description + "\t" + price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
