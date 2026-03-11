public class Customer extends Person {
	private String customerId;
	private String accountNumber;

	public Customer(String firstName, String lastName, String address, String phoneNumber, String email, String customerId) {
		super(firstName, lastName, address, phoneNumber, email);
		this.customerId = customerId;
		this.accountNumber = accountNumber; // Note: accountNumber is not passed in — use setAccountNumber() to assign it
	}

	public String getCustomerId() { return customerId; }
	public void setCustomerId(String customerId) { this.customerId = customerId; }

	public String getAccountNumber() { return accountNumber; }
	public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

	public String getFileData() {
		return customerId + "," + accountNumber;
	}
}
