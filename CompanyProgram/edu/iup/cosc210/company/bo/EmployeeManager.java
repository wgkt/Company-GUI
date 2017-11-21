package edu.iup.cosc210.company.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.iup.cosc210.company.io.EmployeeInputStream;


public class EmployeeManager {
	List<Employee> employees = new ArrayList<Employee>();

	public void loadFile(String fileName) throws IOException {
		EmployeeInputStream in = new EmployeeInputStream(fileName);

		Employee employee;

		while ((employee = in.readEmployee()) != null) {
			addEmployee(employee);
		}

		in.close();
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

	public void removeEmployee(int i) {
		employees.remove(i);
	}

	public int getNoEmployees() {
		return employees.size();
	}

	public Employee getEmployee(int i) {
		return employees.get(i);
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out
					.println("Usage: java edu.iup.cosc210.bo.VideoManager [video file]");
			System.exit(-1);
		}
		EmployeeManager em = new EmployeeManager();
		try {
			em.loadFile(args[0]);

		} catch (FileNotFoundException e) {
			System.out.println("File " + args[0] + " not found");
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
