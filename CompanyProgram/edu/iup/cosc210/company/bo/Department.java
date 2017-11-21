package edu.iup.cosc210.company.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * A Department of a Company.
 * 
 * @author  Meghan J. Cowan
 * 
 */
public class Department {
	private String deptCode;
	private String deptName;
	private int mgrEmpId;
	private List<Employee> employees = new ArrayList<Employee>();
	private Employee manager;

	/**
	 * Constructor for Department.
	 * 
	 * @param deptCode -
	 *            the department code
	 * @param deptName -
	 *            the department name
	 * @param mgrEmpId -
	 *            the manager's employee id for the department
	 */
	public Department(String deptCode, String deptName, int mgrEmpId) {
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.mgrEmpId = mgrEmpId;
	}

	/**
	 * Add an employee to the department's list of employees.  In
	 * addition sets the employee's department and department code.
	 * 
	 * @param employee -
	 *            employee to add to the department
	 */

	public void addEmployee(Employee employee) {
		if (!equals(employee.getDepartment())) {
			employee.setDepartment(this);
		}
		
		if (!employees.contains(employee)) {
			employees.add(employee);
		}
	}
	
	/**
	 * Remove an employee from the department's list of employees
	 * 
	 * @param employee - employee to be removed
	 */
	public void removeEmployee(Employee employee) {
		if (employees.contains(employee)) {
			employees.remove(employee);
			employee.setDepartment(null);	
		}
	}

	/**
	 * Get the code for the department
	 * 
	 * @return - the code for the department
	 */

	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Get the name of the department
	 * 
	 * @return - the name of the department
	 */

	public String getDeptName() {
		return deptName;
	}

	/**
	 * Get the manager of the department. The manager is indicated by the
	 * mgrEmpId passed on the constructor. The manager must be an employee of
	 * the department, otherwise null is returned.
	 * 
	 * @return - the department manager.
	 */
	public Employee getManager() {
		if (manager == null) {
			for (Employee emp : employees) {
				if (emp.getEmployeeNumber() == mgrEmpId) {
					manager = emp;
					break;
				}
			}
		}

		return manager;
	}

	/**
	 * Get the number of employees in the department
	 * 
	 * @return - the number of employees in the department
	 */
	public int getNoEmployees() {
		return employees.size();
	}

	/**
	 * Get an employee given an index position
	 * 
	 * @param emp -
	 *            the employee to return
	 * @return - the employee at the given position
	 * @throws IndexOutOfBoundsException
	 *             if the index is less that 0 or greater than or equal to the
	 *             number of employees.
	 */
	public Employee getEmployee(int index) {
		return employees.get(index);
	}

	/**
	 * Get total number of vacation days of all employees in the department
	 * 
	 * @return - the total number of vacation days
	 */
	public int getTotalVacationDays() {
		int totalVacationDays = 0;
		for (Employee emp : employees) {
			totalVacationDays += emp.getVacationDays();
		}

		return totalVacationDays;
	}
	
	public boolean equals(Object object){
		if (object instanceof Department) {
			return deptCode.equals(((Department) object).getDeptCode());
		}
		return false;
	}

}
