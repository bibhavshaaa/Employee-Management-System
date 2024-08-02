package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class DeleteEmployee extends JFrame implements ActionListener {

    JTextField tfEmployeeID;
    JButton delete, cancel;
    private String employeeID;

    DeleteEmployee(String enteredEmployeeID) {
        setLayout(null);

        JLabel lblEmployeeID = new JLabel("Employee ID");
        lblEmployeeID.setBounds(60, 30, 120, 30);
        lblEmployeeID.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblEmployeeID);

        tfEmployeeID = new JTextField();
        tfEmployeeID.setBounds(200, 30, 150, 30);
        add(tfEmployeeID);

        delete = new JButton("Delete");
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.setBounds(60, 80, 150, 30);
        delete.addActionListener(this);
        add(delete);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(260, 80, 150, 30);
        cancel.addActionListener(this);
        add(cancel);

        // Image on the right side
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon deleteImage = new ImageIcon(image);
        JLabel imageLabel = new JLabel(deleteImage);
        imageLabel.setBounds(400, 30, 250, 250);
        add(imageLabel);

        this.employeeID = enteredEmployeeID;

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 700, 300);
        setVisible(true);
    }

    private boolean deleteEmployeeFromDatabase(String employeeID) {
        // Code to delete the employee from the database based on the employee ID
        try {
            Conn conn = new Conn();
            String query = "DELETE FROM employee WHERE employee_id = ?";
            PreparedStatement pstmt = conn.c.prepareStatement(query);
            pstmt.setString(1, employeeID);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Employee with Employee ID " + employeeID + " deleted successfully.");
                return true;
            } else {
                System.out.println("Employee with Employee ID " + employeeID + " not found.");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting employee from the database.");
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delete) {
            employeeID = tfEmployeeID.getText();
            boolean deleted = deleteEmployeeFromDatabase(employeeID);
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Employee successfully deleted");
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found or error occurred");
            }
        } else if (e.getSource() == cancel) {
            dispose(); // Close the frame
        }

        // After performing delete or cancel, return to the dashboard
        disposeAndOpenDashboard();
    }

    private void disposeAndOpenDashboard() {
        // Close the current DeleteEmployee frame and open Dashboard frame
        dispose();
        new Dashboard(); // Assuming you have a constructor that initializes Dashboard
    }

    public static void main(String[] args) {
        // Corrected the syntax to pass the employee ID as a string argument
        new DeleteEmployee("exampleEmployeeID");
    }
}
