# Work Order Generator

A Java program that reads employee and ticket data from CSV files and automatically generates work orders based on employee tier level. Built for COP3503 at UNF.

## How to run

**Compile:**
```bash
javac *.java
```

**Run:**
```bash
java Project3
```

Sample input files are included. Output is written to `workorder_data.csv` and `log.txt`.

## Input files

- `employee_data.csv` — employee records (Tier 1 and Tier 2)
- `tier1_ticket_data.csv` — standard support tickets
- `tier2_ticket_data.csv` — specialized support tickets

## Output files

- `workorder_data.csv` — generated work orders
- `log.txt` — timestamped log of all operations

---

## Design Highlights

- **Inheritance** — `Customer` and `Employee` both extend `Person`, avoiding duplicated fields. `Tier2Employee` extends `Employee` to add certification without modifying the base class.
- **Polymorphism** — `WorkOrder` uses `instanceof` to conditionally include certification data, and `getFileData()` is overridden at each level of the hierarchy.
- **Interface** — `Printable` enforces a consistent `getFileData()` contract across `Ticket` and `WorkOrder`.
- **Queue-based processing** — Tickets are stored in a `LinkedList<Queue>` ensuring FIFO assignment order.
- **Logging** — Every significant operation is timestamped and appended to `log.txt` via `FileHandler.logger()`.
