package edu.iup.cosc210.company.iu;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import edu.iup.cosc210.company.bo.Company;
import edu.iup.cosc210.company.bo.Department;
import edu.iup.cosc210.company.bo.Employee;
import edu.iup.cosc210.company.bo.EmployeeManager;

public class EmployeeTableModelAdapter extends AbstractTableModel implements
		TableModel {

	private EmployeeManager em;

	public EmployeeTableModelAdapter(EmployeeManager em) {
		super();
		this.em = em;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		}
		return null;
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "First Name";
		case 1:
			return "Last Name";
		case 2:
			return "Department";
		}
		return null;
	}

	public int getRowCount() {
		return em.getNoEmployees();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = em.getEmployee(rowIndex);

		switch (columnIndex) {
		case 0:
			return employee.getFirstName();
		case 1:
			return employee.getLastName();
		case 2:
			String name;
			String deptCode = employee.getDeptCode();
			//if()
			return employee.getDeptCode();
		}

		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}