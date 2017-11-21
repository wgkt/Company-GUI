package edu.iup.cosc210.company.iu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import edu.iup.cosc210.company.bo.Employee;
import edu.iup.cosc210.company.bo.EmployeeManager;


public class EmployeeFrame extends JFrame {
	
	private EmployeeManager em;
	private JTable employeeTable;	
	private EmployeeTableModelAdapter employeeModel;

	
	public EmployeeFrame(final EmployeeManager em) {
		super("Company Employees");
		this.em = em;
	    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);       //doesn't just hide the windows, actually closes the program
		
		setSize(600, 400);
			
		setLocation((size.width - getWidth())/ 2, (size.height - getHeight()) / 2);
		
		employeeModel = new EmployeeTableModelAdapter(em);
		employeeTable = new JTable(employeeModel);

		getContentPane().add(new JScrollPane(employeeTable));
		
		
		
		Action newAction = new AbstractAction("New...", new ImageIcon("images/New.gif")) {
			public void actionPerformed(ActionEvent arg0) {
			
					Employee employee = new Employee(Employee.getNextAvailableEmployeeNumber(), "", "",
							"", new Date(), '\0', 0.0,
							0.0, (short) 0, (byte) '\0');
					
					EmployeeDialog employeeDialog = new EmployeeDialog(EmployeeFrame.this, employee, "Edit Video");
					
					employeeDialog.setVisible(true);
					
					if (employeeDialog.isOkPressed()) {
		
						em.addEmployee(employee);
						int i = em.getNoEmployees() - 1;
						employeeModel.fireTableRowsInserted(i,i);
					}
					
					//employeeModel.fireTableRowsUpdated(i, i);   //notifies table that something has changed
				}
			};
			
		Action openAction = new AbstractAction("Open...", new ImageIcon("images/Open.gif")) {
				public void actionPerformed(ActionEvent e) {
		            int i = employeeTable.getSelectedRow();
					
					if (i >= 0) {
						Employee employee = EmployeeFrame.this.em.getEmployee(i);
						
						EmployeeDialog employeeDialog = new EmployeeDialog(EmployeeFrame.this, employee, "Edit Employee");
						
						employeeDialog.setVisible(true);
						
						employeeModel.fireTableRowsUpdated(i, i);   //notifies table that something has changed
					}
				}
				
			};
		

	
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(openAction);
		fileMenu.add(newAction);
		
		menuBar.add(fileMenu);
		JToolBar toolBar = new JToolBar();
		toolBar.add(openAction);
		toolBar.add(newAction);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(menuBar, BorderLayout.NORTH);
		topPanel.add(toolBar, BorderLayout.SOUTH);
		
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		
//		openButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				int i = employeeTable.getSelectedRow();
//				
//				if (i >= 0) {
//					Employee employee = EmployeeFrame.this.em.getEmployee(i);
//					
//					EmployeeDialog employeeDialog = new EmployeeDialog(EmployeeFrame.this, employee, "Edit Employee");
//					
//					employeeDialog.setVisible(true);
//					
//					employeeModel.fireTableRowsUpdated(i, i);   //notifies table that something has changed
//				}
//			}});
		
	

		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out
					.println("Usage: java edu.iup.cosc210.bo.EmployeeManager [employee file]");
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

		new EmployeeFrame(em).setVisible(true);

	}	
}
	
