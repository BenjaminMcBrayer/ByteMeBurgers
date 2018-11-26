package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import menu.FoodItem;

public class MenuUtility {

	/**
	 * @param foodItems
	 */
	public static void displayMenu(ArrayList<FoodItem> foodItems) {

		System.out.println("                            Welcome to the Byte Me Burger Menu!");
		System.out.println(
				"\n=============================================================================================");
		for (int i = 0; i < foodItems.size(); ++i) {

			System.out.println("    #:            " + foodItems.get(i).getId());
			System.out.println("    Name:         " + foodItems.get(i).getName());
			System.out.println("    Category:     " + foodItems.get(i).getCategory());
			System.out.println("    Description:  " + foodItems.get(i).getDescription());
			System.out.println("    Price:        " + foodItems.get(i).getPrice() + "\n");
		}
	}

	/**
	 * @return
	 */
	public static ArrayList<FoodItem> generateCurrentMenu() {
		ArrayList<FoodItem> menu;
		menu = readFromFileToArrayList("CompanyInfo/Menu");
		displayMenu(menu);
		return menu;
	}

	/**
	 * @param cart
	 * @param menu
	 * @param num
	 * @param item
	 */
	public static void addItemToOrder(ArrayList<FoodItem> cart, ArrayList<FoodItem> menu, int num, int item) {
		System.out.println(num + " " + menu.get((item - 1)).getName() + "(s) added to cart");
		for (int i = 0; i < num; ++i) {
			cart.add(menu.get((item - 1)));
		}
		// cart.get((cart.size() - 1)).setPrice((cart.get((cart.size() - 1)).getPrice()
		// * num));
	}

	public static ArrayList<FoodItem> readFromFileToArrayList(String path) {
		File file = Paths.get(path).toFile();
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while (line != null && line.isEmpty() == false) {
				String[] temp = line.split("\t");
				foodItems.add(
						new FoodItem(Long.parseLong(temp[0]), temp[1], temp[2], temp[3], Double.parseDouble(temp[4])));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return foodItems;
	}

	public static void printItems(String path) {
		ArrayList<FoodItem> foodItems = new ArrayList<>();
		foodItems = readFromFileToArrayList(path);

		for (FoodItem f : foodItems) {
			System.out.println(f.getName());
		}
	}

	public static void writeToFile(String fileName, ArrayList<FoodItem> data) {
		Path writeFile = Paths.get(fileName);
		File file = writeFile.toFile();

		try {
			PrintWriter outW = new PrintWriter(new FileOutputStream(file, true));

			for (FoodItem f : data) {
				outW.println(f);
			}
			outW.close();

		} catch (FileNotFoundException e) {
			System.out.println("File was not found");
		}
	}
}
