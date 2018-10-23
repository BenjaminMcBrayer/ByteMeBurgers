import java.util.Scanner;

public class AdministratorUtility {
	/**
	 * @param scnr
	 * @return
	 */
	public static String modifyAccess(Scanner scnr) {
		String ans;
		do {
			ans = Validator.getString(scnr,
					"How would you like to modify management permissions? \n1. Grant \n2. Revoke \n3. Exit\n");
			if (ans.equals("1")) {
				grantAccess(scnr);
			} else if (ans.equals("2")) {
				revokeAccess(scnr);
			} else if (ans.equals("3")) {
				System.out.println("Exiting program.");
				ans = "-1";
			} else {
				System.out.println("Please select a valid option.\n");
				ans = "-1";
			}
		} while (!(ans.equals("-1")));
		return ans;
	}

	/**
	 * @param scnr
	 * @return
	 */
	public static void grantAccess(Scanner scnr) {
		String ans;
		do {
			Employee e = createEmployee(scnr);
			EmployeeUtility.writeToFile(e);
			EmployeeUtility.printEmployeeList();
			ans = Validator.getString(scnr, e.getName()
					+ " has been given management permissions. Would you like to add another manager? y/n ");
		} while (ans.equalsIgnoreCase("y"));
	}

	/**
	 * @param scnr
	 * @return
	 */
	public static Employee createEmployee(Scanner scnr) {
		String name = Validator.getString(scnr, "Enter name of new manager: ");
		String id = Validator.getString(scnr, "Enter ID number for new manager: ");
		Employee e = new Employee(id, name);
		return e;
	}

	/**
	 * @param scnr
	 * @return
	 */
	public static void revokeAccess(Scanner scnr) {
		String ans;
		String name;
		EmployeeUtility.printEmployeeList();
		do {
			name = Validator.getString(scnr, "Please enter the name of the manager whom you'd like to remove. \n");
			EmployeeUtility.deleteEmployeeFromFile(name);
			System.out.println("Updated manager list:");
			EmployeeUtility.printEmployeeList();
			ans = Validator.getString(scnr, "Would you like to delete another manager? y/n ");
		} while (ans.equalsIgnoreCase("y"));
	}
}
