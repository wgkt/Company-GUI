package edu.iup.cosc210.company.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import come.javera.ui.layout.JvGridLayout;
import come.javera.ui.layout.JvGridLocation;
import edu.iup.cosc210.company.bo.Company;
import edu.iup.cosc210.company.bo.Department;
import edu.iup.cosc210.company.bo.Employee;

public class EmployeeDialog extends JDialog {

	private Employee employee;
	private JTextField empIdField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JComboBox departmentField;
	private JTextField salaryField;
	private JTextField hireDateField;
	private JTextField vacationDaysField;
	private boolean okPressed = false;
	
	private JCheckBox orientationField;
	private JCheckBox administrativeField;
	private JCheckBox managementField;
	private JCheckBox qualityField;
	private JCheckBox techField;
	private JCheckBox salesField;
	private JCheckBox operationField;
	private JCheckBox safetyField;
	
	
	
	private JRadioButton salaryButton;
	private JRadioButton expButton;
	private JRadioButton hourlyButton;

	public EmployeeDialog(EmployeeFrame employeeFrame, Employee employee,
			String title) {
		this.employee = employee;

		JPanel dataPanel = new JPanel(new JvGridLayout(5,5));

		dataPanel.add(new JLabel("Emp ID"), new JvGridLocation(0, 0));
		empIdField = new JTextField(3);
		empIdField.setEditable(false);
		empIdField.setBackground(Color.LIGHT_GRAY);
		
		JPanel empPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		empPanel.add(empIdField);
		dataPanel.add(empPanel, new JvGridLocation(0, 1));
		
	
		dataPanel.add(new JLabel("First Name"), new JvGridLocation(1, 0));
		firstNameField = new JTextField(10);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		namePanel.add(firstNameField);
		dataPanel.add(namePanel, new JvGridLocation(1,1));

		
		dataPanel.add(new JLabel("Last Name"), new JvGridLocation(2, 0));
		lastNameField = new JTextField(15);
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		lastNamePanel.add(lastNameField);
		dataPanel.add(lastNamePanel, new JvGridLocation(2,1));

		

		Company company = new Company();
        JPanel departmentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		dataPanel.add(new JLabel("Department"), new JvGridLocation(3, 0));
		
		departmentField = new JComboBox();
		departmentField.addItem("Administration");
		departmentField.addItem("Engineering");
		departmentField.addItem("Executive");          //create a loop and addDepartment [ not hardcode ] 
		departmentField.addItem("Sales");
		departmentField.addItem("Finance");
		departmentField.addItem("Marketing");
		departmentField.addItem("Manufacturing");
		departmentField.setBackground(Color.white);
		
//		for (String element : company.getDeparment(i)) {
//			JCheckBox departmentField = new JCheckBox(element);
//			departmentPanel.add(departmentField);
//		}
		dataPanel.add(departmentField, new JvGridLocation(3,1));

		salaryButton = new JRadioButton("Salary");
		expButton = new JRadioButton("Exempt");
		hourlyButton = new JRadioButton("Hourly");
		dataPanel.add(salaryButton, new JvGridLocation(0, 2));
		dataPanel.add(expButton, new JvGridLocation(0, 3));
        dataPanel.add(hourlyButton, new JvGridLocation(0,4));
        
		dataPanel.add(new JLabel("Pay Amount"), new JvGridLocation(1, 2));
		salaryField = new JTextField(6);
		JPanel salaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		salaryPanel.add(salaryField);
		dataPanel.add(salaryPanel, new JvGridLocation(1,3));

		dataPanel.add(new JLabel("Hire Date"), new JvGridLocation(2,2));
		hireDateField = new JTextField(10);
		dataPanel.add(hireDateField, new JvGridLocation(2,3));
		
		dataPanel.add(new JLabel("Vac Days"), new JvGridLocation(3,2));
		vacationDaysField = new JTextField(3);
		JPanel vacPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0,0));
		vacPanel.add(vacationDaysField);
		dataPanel.add(vacPanel, new JvGridLocation(3,3));

		
		JPanel trainingPanel = new JPanel(new JvGridLayout(2,4));
		trainingPanel.setBorder(BorderFactory.createTitledBorder("Training"));

		orientationField = new JCheckBox("Orientation");
		trainingPanel.add(orientationField, new JvGridLocation(0,0));
		administrativeField = new JCheckBox("Administrative");
		trainingPanel.add(administrativeField, new JvGridLocation(1,0));
		managementField = new JCheckBox("Management");
		trainingPanel.add(managementField, new JvGridLocation(0,1));
		qualityField = new JCheckBox("Quality Control");
		trainingPanel.add(qualityField, new JvGridLocation(1,1));
		techField = new JCheckBox("Technical");
		trainingPanel.add(techField, new JvGridLocation(0,2));
		salesField = new JCheckBox("Sales");
		trainingPanel.add(salesField, new JvGridLocation(1,2));
		operationField = new JCheckBox("Operations");
		trainingPanel.add(operationField, new JvGridLocation(0,3));
		safetyField = new JCheckBox("Safety");
		trainingPanel.add(safetyField, new JvGridLocation(1,3));
		

		JPanel buttonPanel = new JPanel(new GridLayout(1,2, 10, 10));
		JButton okButton = new JButton("OK");
	    okButton.addActionListener(new ActionListener() {         //anonymous inner class [behind the scenes class]
				public void actionPerformed(ActionEvent e) {
					getFields();
					okPressed = true;
					setVisible(false);  
		
				}});       
		    
			buttonPanel.add(okButton);
			
		
		buttonPanel.add(okButton);
		
		getContentPane().add(dataPanel);

		getContentPane().add(trainingPanel, BorderLayout.SOUTH);
		
		pack();

		
		setLocation(employeeFrame.getX()
				+ (employeeFrame.getWidth() - getWidth()) / 2,
				employeeFrame.getY()
						+ (employeeFrame.getHeight() - getHeight()) / 2);
		
		
		setFields();

	}

	private void setFields() {
		empIdField.setText(String.format("%s",employee.getEmployeeNumber()));
		firstNameField.setText(employee.getFirstName());
		lastNameField.setText(employee.getLastName());
		departmentField.setSelectedItem(employee.getDepartment());
	    salaryButton.setSelected(employee.getEmployeeType() == 'S');
		expButton.setSelected(employee.getEmployeeType() == 'E');
		hourlyButton.setSelected(employee.getEmployeeType() == 'H');
		if (employee.getEmployeeType() == 'S') {
		salaryField.setText(String.format("%9.2f",employee.getSalary()));
		} else if (employee.getEmployeeType() == 'E') {
			salaryField.setText("Exempt");
		} else if (employee.getEmployeeType() == 'H') {
			salaryField.setText(String.format("%9.2f", employee.getHourlyRate()));
		}
		hireDateField.setText(String.format("%td/%tm/%tY", employee.getHireDate()));
		
		vacationDaysField.setText(String.format("%d",employee.getVacationDays()));
		//trainingField.setSelected(employee.getTraining());
	}

	public boolean isOkPressed() {
		return okPressed;
	}
	
	private void getFields() {
		employee.setFirstName(firstNameField.getText());
		employee.setLastName(lastNameField.getText());
		employee.setDepartment((Department) departmentField.getSelectedItem());
        
		employee.setSalary(Double.parseDouble(salaryField.getText()));
	//	employee.setHireDate(hireDateField.getText());
		employee.setVacationDays(Short.parseShort(vacationDaysField.getText()));
	}
}
