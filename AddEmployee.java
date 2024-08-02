package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField tfEmployeeID, tffirstName, tflastName, tfemail, tfphone, tfdepartment, tfposition, tfsalary, tfdateHired;
    JButton submit, cancel;

    AddEmployee() {
        setLayout(null);

        JLabel lblEmployeeID = new JLabel("Employee ID");
        lblEmployeeID.setBounds(60, 30, 120, 30);
        lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblEmployeeID);

        tfEmployeeID = new JTextField();
        tfEmployeeID.setBounds(200, 30, 150, 30);
        add(tfEmployeeID);

        JLabel lblfirstName = new JLabel("First Name");
        lblfirstName.setBounds(60, 80, 120, 30);
        lblfirstName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblfirstName);

        tffirstName = new JTextField();
        tffirstName.setBounds(200, 80, 150, 30);
        add(tffirstName);

        JLabel lbllastName = new JLabel("Last Name");
        lbllastName.setBounds(60, 130, 120, 30);
        lbllastName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbllastName);

        tflastName = new JTextField();
        tflastName.setBounds(200, 130, 150, 30);
        add(tflastName);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(60, 180, 120, 30);
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 180, 150, 30);
        add(tfemail);

        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 230, 120, 30);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 230, 150, 30);
        add(tfphone);

        JLabel lbldepartment = new JLabel("Department");
        lbldepartment.setBounds(60, 280, 120, 30);
        lbldepartment.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbldepartment);

        tfdepartment = new JTextField();
        tfdepartment.setBounds(200, 280, 150, 30);
        add(tfdepartment);

        JLabel lblposition = new JLabel("Position");
        lblposition.setBounds(60, 330, 120, 30);
        lblposition.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblposition);

        tfposition = new JTextField();
        tfposition.setBounds(200, 330, 150, 30);
        add(tfposition);

        JLabel lblsalary = new JLabel("Salary");
        lblsalary.setBounds(60, 380, 120, 30);
        lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblsalary);

        tfsalary = new JTextField();
        tfsalary.setBounds(200, 380, 150, 30);
        add(tfsalary);

        JLabel lbldateHired = new JLabel("Date Hired");
        lbldateHired.setBounds(60, 430, 120, 30);
        lbldateHired.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lbldateHired);

        tfdateHired = new JTextField();
        tfdateHired.setBounds(200, 430, 150, 30);
        add(tfdateHired);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(60, 480, 150, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(260, 480, 150, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(350, 250, Image.SCALE_DEFAULT);
        ImageIcon employeeImage = new ImageIcon(image);
        JLabel imageLabel = new JLabel(employeeImage);
        imageLabel.setBounds(500, 30, 250, 250);
        add(imageLabel);

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            addEmployeeToDatabase();
        } else if (e.getSource() == cancel) {
            // Handle cancel button action if needed
        }
    }

    private void addEmployeeToDatabase() {
        String employeeID = tfEmployeeID.getText();
        String firstName = tffirstName.getText();
        String lastName = tflastName.getText();
        String email = tfemail.getText();
        String phone = tfphone.getText();
        String department = tfdepartment.getText();
        String position = tfposition.getText();
        String salary = tfsalary.getText();
        String dateHired = tfdateHired.getText();

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO employee (employee_id, first_name, last_name, email, phone, department, position, salary, date_hired) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, employeeID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);
            pstmt.setString(6, department);
            pstmt.setString(7, position);
            pstmt.setString(8, salary);
            pstmt.setString(9, dateHired);

            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Employee added successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding employee to the database");
        }
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
