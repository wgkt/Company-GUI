package edu.iup.cosc210.company.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import edu.iup.cosc210.company.io.DepartmentReader;
import edu.iup.cosc210.company.io.EmployeeInputStream;

/**
 * Test printing of the company report.
 * 
 * @author  Meghan J. Cowan
 */
public class CompanyReport {
	private Company company = new Company();
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");	
    private static DecimalFormat decformatter = new DecimalFormat("#,###,###.00");
	/**
	 * Main method to print the company report: Creates a company Loads
	 * Departments from the file name given in the first command line argument
	 * Loads Employees from the file name given in the last command line
	 * argument
	 * 
	 * @param args - the command line arguments.
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: java edu.iup.cosc210.company <department file> <employee file>");
			System.exit(-100);
		}

		CompanyReport companyReport = new CompanyReport();
		
		try {
			companyReport.loadDepts(args[0]);
			companyReport.loadEmployees(args[1]);
			companyReport.printCompanyReport();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to run: " + e.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Load departments from a text file.
	 * @param fileName - the filename of the file that contains the departments.
	 * @throws IOException - in the event the file cannot be opened or read.
	 */
	public void loadDepts(String fileName) throws NumberFormatException,
			IOException {
		DepartmentReader in = new DepartmentReader(fileName);
		Department department;

		while ((department = in.readDepartment()) != null) {
			company.addDepartment(department);
		}
	}
	
	/**
	 * Load Employees from a binary file.  The employees are added to the list of employees
	 * for their respective Department as indicated by deptCode.
	 * @param fileName - the name of the file that contains the employees.
	 * @throws Exception - catches an Exception.
	 */
	public void loadEmployees(String fileName) throws Exception {
		EmployeeInputStream in = new EmployeeInputStream(fileName);
		
		Employee employee;

		while ((employee = in.readEmployee()) != null) {
			company.addEmployee(employee);
		}
	}
	
	/**
	 * Prints a company report.  Report include information on the department
	 * and a list of all employees.
	 */
	public void printCompanyReport() {
		// loop over all departments
		for (int i = 0; i < company.getNoDepts(); i++) {
			Department department = company.getDeparment(i);
			
			// print the department header
			System.out.println(department.getDeptName() + " Department");
			System.out.println();
			System.out.printf("%-20s%-20s\n", "Manager: ", department.getManager().getFirstName() + " " + department.getManager().getLastName());
			System.out.printf("%-20s%-20s\n", "Staff Size: ", department.getNoEmployees());
			System.out.printf("%-20s%d\n", "Vacation Days: ",department.getTotalVacationDays());
			System.out.println();
			
			// print the column labels for employees
			System.out.printf("%-5s  %-26s  %-10s  %-3s  %-8s    %-6s   %-3s\n", "ID",
					"Employee Name", "Hire Date", "Typ", "Salary", "Rate", "Vac");
			
			// loop over all employees in the department
			for (int j = 0; j < department.getNoEmployees(); j++) {
				Employee emp = department.getEmployee(j);
				System.out.printf("%5d  %-26s  %s   %c   %10s  %6s   %3d\n",
						emp.getEmployeeNumber(),
						emp.getFirstName() + " " +
						emp.getLastName(), 
						dateFormatter.format(emp.getHireDate()),
						emp.getEmployeeType(),
						emp.getSalary() == 0 ? "" : decformatter.format(emp.getSalary()),
						emp.getHourlyRate() == 0 ? "" : String.format("%6.2f", emp.getHourlyRate()),
						emp.getVacationDays());
			}
			
			System.out.println();
			System.out.println();
		}
	}
}
