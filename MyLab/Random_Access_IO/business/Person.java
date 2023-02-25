package business;

public class Person {
	public static final int FIRST_NAME_MAX_SIZE = 20;
	public static final int LAST_NAME_MAX_SIZE = 25;
	public static final int PHONE_MAX_SIZE = 10;

	private String firstName;
	private String lastName;
	private String phone;
	private int age;
	private String recordNumber;

	public Person(String recordNumber, String firstName, String lastName, String phone, int age) {
		this.recordNumber = recordNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
