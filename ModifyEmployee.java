package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ModifyEmployee extends JFrame implements ActionListener {

    JTextField tfEmployeeID, tfEmployeeName, tfEmployeeRole, tfEmployeeSalary, tfEmployeeAddress, tfEmployeeContact;
    JButton fetchDetails, modify, cancel;

    ModifyEmployee() {
        setTitle("Modify Employee Details");
        setLayout(null);

        JLabel lblEmployeeID = new JLabel("Employee ID:");
        lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeID.setBounds(50, 30, 100, 30);
        add(lblEmployeeID);

        tfEmployeeID = new JTextField();
        tfEmployeeID.setBounds(160, 30, 150, 30);
        add(tfEmployeeID);

        fetchDetails = new JButton("Fetch Details");
        fetchDetails.setBackground(Color.BLACK);
        fetchDetails.setForeground(Color.WHITE);
        fetchDetails.setBounds(320, 30, 120, 30);
        fetchDetails.addActionListener(this);
        add(fetchDetails);

        JLabel lblEmployeeName = new JLabel("Name:");
        lblEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeName.setBounds(50, 80, 100, 30);
        add(lblEmployeeName);

        tfEmployeeName = new JTextField();
        tfEmployeeName.setBounds(160, 80, 200, 30);
        add(tfEmployeeName);

        JLabel lblEmployeeRole = new JLabel("Position:");
        lblEmployeeRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeRole.setBounds(50, 130, 100, 30);
        add(lblEmployeeRole);

        tfEmployeeRole = new JTextField();
        tfEmployeeRole.setBounds(160, 130, 200, 30);
        add(tfEmployeeRole);

        JLabel lblEmployeeSalary = new JLabel("Salary:");
        lblEmployeeSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeSalary.setBounds(50, 180, 100, 30);
        add(lblEmployeeSalary);

        tfEmployeeSalary = new JTextField();
        tfEmployeeSalary.setBounds(160, 180, 150, 30);
        add(tfEmployeeSalary);

        JLabel lblEmployeeAddress = new JLabel("Department:");
        lblEmployeeAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeAddress.setBounds(50, 230, 100, 30);
        add(lblEmployeeAddress);

        tfEmployeeAddress = new JTextField();
        tfEmployeeAddress.setBounds(160, 230, 250, 30);
        add(tfEmployeeAddress);

        JLabel lblEmployeeContact = new JLabel("Phone:");
        lblEmployeeContact.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmployeeContact.setBounds(50, 280, 100, 30);
        add(lblEmployeeContact);

        tfEmployeeContact = new JTextField();
        tfEmployeeContact.setBounds(160, 280, 150, 30);
        add(tfEmployeeContact);

        modify = new JButton("Modify");
        modify.setBackground(Color.BLACK);
        modify.setForeground(Color.WHITE);
        modify.setBounds(100, 340, 100, 30);
        modify.addActionListener(this);
        add(modify);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(250, 340, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        getContentPane().setBackground(Color.WHITE);
        setSize(500, 430);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void fetchEmployeeDetails(String employeeID) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM employee WHERE employee_id = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, employeeID);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                tfEmployeeName.setText(rs.getString("first_name") + " " + rs.getString("last_name"));
                tfEmployeeRole.setText(rs.getString("position"));
                tfEmployeeSalary.setText(rs.getString("salary"));
                tfEmployeeAddress.setText(rs.getString("department"));
                tfEmployeeContact.setText(rs.getString("phone"));
            } else {
                JOptionPane.showMessageDialog(this, "Employee with ID " + employeeID + " not found.");
                clearFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching employee details.");
            clearFields();
        }
    }

    private void clearFields() {
        tfEmployeeName.setText("");
        tfEmployeeRole.setText("");
        tfEmployeeSalary.setText("");
        tfEmployeeAddress.setText("");
        tfEmployeeContact.setText("");
    }

    private boolean saveModifiedData() {
        try {
            Conn conn = new Conn();
            String updateQuery = "UPDATE employee SET first_name=?, last_name=?, position=?, salary=?, department=?, phone=? WHERE employee_id=?";
            PreparedStatement pstmt = conn.c.prepareStatement(updateQuery);

            // Split the full name into first_name and last_name
            String[] fullName = tfEmployeeName.getText().split(" ");
            if (fullName.length > 1) {
                pstmt.setString(1, fullName[0]);
                pstmt.setString(2, fullName[1]);
            } else {
                pstmt.setString(1, fullName[0]);
                pstmt.setString(2, "");
            }

            pstmt.setString(3, tfEmployeeRole.getText());
            pstmt.setString(4, tfEmployeeSalary.getText());
            pstmt.setString(5, tfEmployeeAddress.getText());
            pstmt.setString(6, tfEmployeeContact.getText());
            pstmt.setString(7, tfEmployeeID.getText());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Employee details updated successfully.");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving modified data.");
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetchDetails) {
            String employeeID = tfEmployeeID.getText().trim();
            if (!employeeID.isEmpty()) {
                fetchEmployeeDetails(employeeID);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter Employee ID.");
            }
        } else if (e.getSource() == modify) {
            if (saveModifiedData()) {
                clearFields();
                dispose(); // Close the current frame after saving
                new Dashboard(); // Open the dashboard
            }
        } else if (e.getSource() == cancel) {
            dispose(); // Close the current frame
        }
    }

    public static void main(String[] args) {
        new ModifyEmployee();
    }
}
