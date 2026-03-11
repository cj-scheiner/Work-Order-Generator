/**
 * Author: Christopher Scheiner
 * Course: COP3503
 * Project #: 3
 * Title: Work Order Generator
 * Due Date: 11/17/2024
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Project3 {
	private static String employeeFileName = "employee_data.csv";
	private static String tier1TicketFileName = "tier1_ticket_data.csv";
	private static String tier2TicketFileName = "tier2_ticket_data.csv";
	private static String workOrderFileName = "workorder_data.csv";

	static ArrayList<Employee> employeeList = new ArrayList<>();
	private static Queue<Ticket> tier1TicketFile = new LinkedList<>();
	private static Queue<Ticket> tier2TicketFile = new LinkedList<>();
	private static ArrayList<WorkOrder> workOrderList = new ArrayList<>();

	public static void main(String[] args) {
		Project3 project3 = new Project3();
		System.out.println("Project 3 Work Order Generator\n");

		project3.loadEmployeeData();

		System.out.println("Loading Ticket Data");
		FileHandler.logger("Loading Ticket Data");
		tier1TicketFile = FileHandler.readTicketData(tier1TicketFileName);
		tier2TicketFile = FileHandler.readTicketData(tier2TicketFileName);

		project3.createWorkOrders();

		System.out.println("Writing Work Order Data to File");
		FileHandler.logger("Writing Work Order Data to File");
		FileHandler.writeData(workOrderFileName, workOrderList);

		System.out.println("Work Orders created. Program Exiting");
		FileHandler.logger("Work Orders created. Program Exiting");
	}

	private void loadEmployeeData() {
		System.out.println("Loading Employee Data");
		FileHandler.logger("Loading Employee Data");
		FileHandler.readEmployeeData(employeeFileName, this);
	}

	public void createWorkOrders() {
		System.out.println("Creating Work Orders");
		FileHandler.logger("Creating Work Orders");

		// Assign tier 1 employees to tier 1 tickets
		for (Employee employee : getEmployeeList()) {
			if (employee instanceof Tier2Employee) continue;
			if (!tier1TicketFile.isEmpty()) {
				Ticket ticket = tier1TicketFile.poll();
				WorkOrder workOrder = new WorkOrder(employee, ticket);
				workOrderList.add(workOrder);
				FileHandler.logger("Work Order created: " + workOrder.getFileData());
			}
		}

		// Assign tier 2 employees to tier 2 tickets
		for (Employee employee : getEmployeeList()) {
			if (!(employee instanceof Tier2Employee)) continue;
			if (!tier2TicketFile.isEmpty()) {
				Ticket ticket = tier2TicketFile.poll();
				WorkOrder workOrder = new WorkOrder(employee, ticket);
				workOrderList.add(workOrder);
				FileHandler.logger("Work Order created: " + workOrder.getFileData());
			}
		}
	}

	public Project3() {
		employeeList = new ArrayList<>();
	}

	public void setEmployeeList(ArrayList<Employee> employees) { this.employeeList = employees; }
	public static ArrayList<Employee> getEmployeeList() { return employeeList; }
}
