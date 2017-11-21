package edu.iup.cosc210.company.bo;

import java.util.Date;

/**
 * An Employee of a Company.
 * 
 * @author  Meghan J. Cowan
 */
public class Employee {
	private int employeeNumber;
	private String firstName;
	private String lastName;
	private String deptCode;
	private Department department;
	private Date hireDate;
	private char employeeType;
	private double salary;
	private double hourlyRate;
	private short vacationDays;
	private byte training;
	
	private static int nextAvailableEmployeeNumber = 0;
	
	/**
	 * Constructor for Employee
	 * 
	 * @param employeeNumber
	 *            - the employee's id number
	 * @param firstName
	 *            - first name of the employee
	 * @param lastName
	 *            - last name of the employee
	 * @param deptCode
	 *            - department code of the employee's department
	 * @param hireDate
	 *            - the date the employee was hired
	 * @param employeeType
	 *            - indicates if an employee is Exempt ('E'), salaried ('S'),
	 *              or hourly ('H')
	 * @param salary           
	 *            - the employee's salary
	 * @param hourlyRate          
	 *            - the employee's hourlyRate
	 * @param vacationDays
	 *            - the number of vacation days
	 * @param training
	 *            - a byte using bits to indicated the training the employee has
	 *            received
	 */
	public Employee(int employeeNumber, String firstName, String lastName,
			String deptCode, Date hireDate, char employeeType, double salary,
			double hourlyRate, short vacationDays, byte training) {
		super();
		this.employeeNumber = employeeNumber;
		
		if (employeeNumber >= nextAvailableEmployeeNumber) {
			nextAvailableEmployeeNumber = employeeNumber + 1;
		}
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.deptCode = deptCode;
		this.hireDate = hireDate;
		this.employeeType = employeeType;
		this.salary = salary;
		this.hourlyRate = hourlyRate;
		this.vacationDays = vacationDays;
		this.training = training;
	}
	
	public Employee() {
		employeeNumber = nextAvailableEmployeeNumber++;
		firstName = "";
		lastName = "";
		deptCode = "";
		hireDate = new Date();
		
		salary = 0.0;
		hourlyRate = 0.0;
	    
	}

	/**
	 * Get the employee number
	 * 
	 * @return - the employee number
	 */
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	
	
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public void setEmployeeType(char employeeType) {
		this.employeeType = employeeType;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void setVacationDays(short vacationDays) {
		this.vacationDays = vacationDays;
	}

	public void setTraining(byte training) {
		this.training = training;
	}

	public static void setNextAvailableEmployeeNumber(
			int nextAvailableEmployeeNumber) {
		Employee.nextAvailableEmployeeNumber = nextAvailableEmployeeNumber;
	}

	/**
     * Get the next available employee number. This will be one greater
     * than the highest employee numbers of all Employee instances.
     * 
     * @return next available employee number
     */
	public static int getNextAvailableEmployeeNumber() {
		return nextAvailableEmployeeNumber;
	}

	/**
	 * Get the employee's first name
	 * 
	 * @return - the employee's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get the employee's last name
	 * 
	 * @return - the employee's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the employee's department code.
	 * 
	 * @return - the employee's department code
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Get the employee's department.
	 * 
	 * @return - the employee's department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * Set the employee's department.
	 * 
	 * @param department - the employee's department
	 */
	public void setDepartment(Department department) {
		if (this.department != null && !this.department.equals(department)) {
			this.department.removeEmployee(this);
		}
		
		if (department == null) {
			department = null;
			deptCode = "";
			return;
		}

		boolean doAdd = false;
		
		if (this.department == null || !this.department.equals(department)) {
			doAdd = true;
		}

		this.deptCode = department.getDeptCode();
		this.department = department;
		
		if (doAdd) {
			department.addEmployee(this);
		}
		
	}

	/**
	 * Get the employee's hire date as a string of the form mm/dd/yyyy.
	 * 
	 * @return - returns the employee's hire date
	 */
	public Date getHireDate() {
		return hireDate;
	}

	/**
	 * Get the employee's type E for  exempt, S for salaried, H for hourly
	 * 
	 * @return - the employee's type
	 */
	public char getEmployeeType() {
		return employeeType;
	}
	
	/**
	 * Get the employee's salary
	 * 
	 * @return - the employee's salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Get the employee's hourly rate
	 * 
	 * @return - the employee's hourly rate
	 */
	public double getHourlyRate() {
		return hourlyRate;
	}

	/**
	 * Get the number of vacation days for an employee
	 * 
	 * @return - the number of vacation days an employee has
	 */
	public short getVacationDays() {
		return vacationDays;
	}

	/**
	 * Get the encoded traning byte.
	 * 
	 * @return - a byte whose bits indicate traingin the employee has recieved
	 */
	public byte getTraining() {
		return training;
	}
	
	public boolean equals(Object object){
		if (object instanceof Employee) {
			return employeeNumber == ((Employee) object).getEmployeeNumber();
		}
		return false;
	}
}
