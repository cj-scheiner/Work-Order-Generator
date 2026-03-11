# Work Order Generator

A Java application that automates the creation of work orders by matching employees to support tickets based on employee tier level. Built as a demonstration of object-oriented design principles including inheritance, polymorphism, interfaces, and file I/O.

---

## Overview

The system reads employee and ticket data from CSV files, processes them through a tiered assignment system, and outputs a completed work order file with a timestamped log of all operations.

- **Tier 1 employees** are assigned to standard (Tier 1) tickets
- **Tier 2 employees** handle specialized (Tier 2) tickets requiring certification
- Work orders are generated automatically and written to a CSV output file

---

## Project Structure

```
WorkOrderGenerator/
├── Project3.java         # Main entry point — orchestrates data loading and work order creation
├── Person.java           # Base class for all individuals in the system
├── Customer.java         # Extends Person — represents a customer with an account
├── Employee.java         # Extends Person — represents a Tier 1 employee
├── Tier2Employee.java    # Extends Employee — adds certification field for specialized staff
├── Ticket.java           # Represents a support ticket associated with a customer
├── WorkOrder.java        # Associates an employee with a ticket and timestamps the assignment
├── FileHandler.java      # Handles all CSV reading/writing and logging
└── Printable.java        # Interface defining the getFileData() contract
```

---

## Class Hierarchy

```
Person
├── Customer
└── Employee
    └── Tier2Employee

Ticket      (implements Printable)
WorkOrder   (implements Printable)
```

---

## Getting Started

### Requirements
- Java 8 or higher
- Input CSV files (see below)

### Compile
```bash
javac *.java
```

### Run
```bash
java Project3
```

The program expects these files in the same directory:
- `employee_data.csv`
- `tier1_ticket_data.csv`
- `tier2_ticket_data.csv`

Output is written to:
- `workorder_data.csv` — generated work orders
- `log.txt` — timestamped log of all operations

---

## Input File Formats

### `employee_data.csv`
```
id,first_name,last_name,address,phone,email,employee_id,clocked_in,hired_date,tier,[certification]
```
- Tier 1 employees have `tier1` in the tier field
- Tier 2 employees have `tier2` and an additional certification field

### `tier1_ticket_data.csv` / `tier2_ticket_data.csv`
```
customer_id,first_name,last_name,address,phone,email,ticket_id,created_at
```

---

## Output

### `workorder_data.csv`
```
customer_id,customer_first_name,customer_last_name,ticket_id,ticket_createdAt,
workorder_createdAt,employee_id,employee_first_name,employee_last_name,
clocked_in,certification
```

Tier 1 work orders display `N/A` in the certification column. Tier 2 work orders display the assigned employee's certification.

---

## Design Highlights

- **Inheritance** — `Customer` and `Employee` both extend `Person`, avoiding duplicated fields. `Tier2Employee` extends `Employee` to add certification without modifying the base class.
- **Polymorphism** — `WorkOrder` uses `instanceof` to conditionally include certification data, and `getFileData()` is overridden at each level of the hierarchy.
- **Interface** — `Printable` enforces a consistent `getFileData()` contract across `Ticket` and `WorkOrder`.
- **Queue-based processing** — Tickets are stored in a `LinkedList<Queue>` ensuring FIFO assignment order.
- **Logging** — Every significant operation is timestamped and appended to `log.txt` via `FileHandler.logger()`.
