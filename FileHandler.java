import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandler {

	public static void writeData(String workOrderFileName, ArrayList<WorkOrder> workOrderList) {
		try (FileWriter fw = new FileWriter(workOrderFileName);
				PrintWriter out = new PrintWriter(fw)) {
			out.println("customer_id,customer_first_name,customer_last_name,ticket_id,ticket_createdAt,workorder_createdAt,employee_id,employee_first_name,employee_last_name,clocked_in,certification");

			for (WorkOrder workOrder : workOrderList) {
				out.println(workOrder.getFileData());
				logger(workOrder.getFileData());
			}
		} catch (IOException e) {
			System.out.println("Input/Output error:" + e);
		}
	}

	public static Queue<Ticket> readTicketData(String ticketFileName) {
		Queue<Ticket> tickets = new LinkedList<>();
		try (Scanner scanner = new Scanner(new File(ticketFileName))) {
			scanner.nextLine(); // skip header
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(",");

				String customerId = data[0];
				String firstName = data[1];
				String lastName = data[2];
				String address = data[3];
				String phoneNumber = data[4];
				String email = data[5];
				String ticketId = data[6];
				String createdAt = data[7];

				Customer customer = new Customer(customerId, firstName, lastName, address, phoneNumber, email);
				tickets.add(new Ticket(customer, createdAt, ticketId));
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error:" + e);
		}
		return tickets;
	}

	public static void readEmployeeData(String employeeFileName, Project3 project3) {
		try (Scanner scanner = new Scanner(new File(employeeFileName))) {
			if (scanner.hasNextLine()) {
				scanner.nextLine(); // skip header
			}
			while (scanner.hasNextLine()) {
				String[] data = scanner.nextLine().split(",");

				boolean clockedIn = (data[7] != null && data[7].trim().equalsIgnoreCase("true"));
				String tier = data[data.length - 2].trim();

				if (tier.equalsIgnoreCase("tier1")) {
					project3.employeeList.add(new Employee(data[1], data[2], data[3], data[4], data[5], data[6], clockedIn, data[8]));
				} else if (tier.equalsIgnoreCase("tier2")) {
					project3.employeeList.add(new Tier2Employee(data[1], data[2], data[3], data[4], data[5], data[6], clockedIn, data[8], data[9]));
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error:" + e);
		}
	}

	public static void logger(String log) {
		String date = new SimpleDateFormat("MM/dd HH:mm:ss").format(new Date());
		try (FileWriter fw = new FileWriter("log.txt", true);
				PrintWriter out = new PrintWriter(fw)) {
			out.println("log : " + date + " : " + log);
		} catch (IOException e) {
			System.out.println("Input/Output error:" + e);
		}
	}
}
