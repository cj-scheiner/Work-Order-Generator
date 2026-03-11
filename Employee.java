public class Employee extends Person {
	private String employeeId;
	private boolean clockedIn;
	private String hiredDate;

	public Employee(String firstName, String lastName, String address, String phoneNumber, String email, String employeeId, boolean clockedIn, String hiredDate) {
		super(firstName, lastName, address, phoneNumber, email);
		this.employeeId = employeeId;
		this.clockedIn = clockedIn;
		this.hiredDate = hiredDate;
	}

	public String getEmployeeId() { return employeeId; }
	public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

	public boolean getClockedIn() { return clockedIn; }
	public void setClockedIn(boolean clockedIn) { this.clockedIn = clockedIn; }

	public String getFileData() {
		return super.getFileData() + "," + employeeId + "," + clockedIn + "," + hiredDate;
	}
}
