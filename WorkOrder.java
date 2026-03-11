import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkOrder implements Printable {
	private Employee employee;
	private Ticket ticket;
	private String createdAt;

	public WorkOrder(Employee employee, Ticket ticket) {
		this.employee = employee;
		this.ticket = ticket;
		this.createdAt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
	}

	@Override
	public String getFileData() {
		return ticket.getCustomer().getCustomerId() + "," +
				ticket.getCustomer().getFirstName() + "," +
				ticket.getCustomer().getLastName() + "," +
				ticket.getTicketId() + "," +
				ticket.getCreatedAt() + "," +
				createdAt + "," +
				employee.getEmployeeId() + "," +
				employee.getFirstName() + "," +
				employee.getLastName() + "," +
				employee.getClockedIn() + "," +
				(employee instanceof Tier2Employee ? ((Tier2Employee) employee).getCertification() : "N/A");
	}
}
