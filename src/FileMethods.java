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

public class FileMethods {


	public static ArrayList<FoodItem> readFromFile2() {
		Path read = Paths.get("CompanyInfo/Menu");
		File file = read.toFile();
		ArrayList<FoodItem> foodItems = new ArrayList<>();

		FileReader fr;
		try {
			fr = new FileReader(file);

			BufferedReader reader = new BufferedReader(fr);

			String line = reader.readLine();

			while (line != null && line.isEmpty() == false) {
				String[] words = line.split("\t");
				foodItems.add(new FoodItem(words[0], words[1], words[2], Double.parseDouble(words[3])));
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}

		return foodItems;
	}

	public static void addToFile(String fileName, ArrayList<FoodItem> foodItems) {
		ArrayList<FoodItem> data = readFromFile2();
		data.addAll(foodItems);
		writeToFile(fileName, data);

		return;
	}

	private static void writeToFile(String fileName, ArrayList<FoodItem> data) {
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
		
		
		



	
		
		
		
		
	
	
	
	public static void displayMenu(ArrayList<FoodItem> foodItems) {
		
		System.out.println("                            Welcome to the Byte Me Burger Menu!");
		System.out.println("\n=============================================================================================");
		for(int i = 0; i < foodItems.size(); ++i) {
			
			System.out.println("    Name:         " + foodItems.get(i).getName());
			System.out.println("    Category:     " + foodItems.get(i).getCategory());
			System.out.println("    Description:  " + foodItems.get(i).getDescription());
			System.out.println("    Price:        " + foodItems.get(i).getPrice());
			System.out.println();
		}
	}
	
	
	
	
	public static ArrayList<FoodItem> addItem(String fileName, FoodItem item) {
		ArrayList<FoodItem> temp = FileMethods.readFromFile2();
		temp.add(item);
		return temp;

	} // end of 2nd method
		// void does not return a value (I'm doing something, but not getting anything
		// back

	public static void addMenu(String fileName, ArrayList<FoodItem> foodItems) {
		FileMethods.addToFile(fileName, foodItems); // No left side because not returning any value
		return; // not returning any value
	}
}