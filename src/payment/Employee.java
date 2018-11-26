package payment;
/**
 * @author benjamin.mcbrayer
 *
 */
public class Employee {
	private String userID;
	private String name;

	public Employee() {
	}

	public Employee(String id, String name) {
		this.userID = id;
		this.name = name;
	}

	public String getId() {
		return userID;
	}

	public void setId(String id) {
		this.userID = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return userID + "," + name;
	}
}