import java.util.LinkedList;

public class Employee {
    private String name;
    private String department;
    private double salary;
    private int ticketsSold;
    private double totalRevenue;

    private static LinkedList<Employee> employees = new LinkedList<>();

    // Constructor
    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.ticketsSold = 0;
        this.totalRevenue = 0.0;
    }
    // Method to add an employee to the list
    public void addEmployee() {
        employees.add(this);
    }

    // Method to remove an employee from the list
    public void removeEmployee() {
        employees.remove(this);
    }

    // Method to update employee information (e.g., name, department, salary, etc.)
    public void updateEmployee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Method to display all employees' details
    public static void displayAllEmployees() {
        for (Employee employee : employees) {
            employee.displayEmployeeDetails();
        }
    }

    // Method to update total revenue and ticket sales for an employee
    public void updateTicketsSoldAndRevenue(int ticketsSold, double ticketPrice) {
        this.ticketsSold += ticketsSold;
        double revenue = ticketsSold * ticketPrice;
        this.totalRevenue += revenue;
    }

    // Method to display employee details (including total revenue)
    public void displayEmployeeDetails() {
        System.out.println("Employee Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Salary: $" + salary);
        System.out.println("Tickets Sold: " + ticketsSold);
        System.out.println("Total Revenue: $" + totalRevenue);
    }
}
