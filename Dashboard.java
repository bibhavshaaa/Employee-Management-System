package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard extends JFrame implements ActionListener {

    JMenuItem home, dashboard, about, contact, logout;
    JTextArea displayArea;

    Dashboard() {
        setBounds(0, 0, 1550, 1000);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Black.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1500, 1000);
        add(image);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 15, 1550, 60);
        mb.setBackground(Color.BLACK);
        image.add(mb);

        home = new JMenuItem("HOME");
        home.setForeground(Color.WHITE);
        home.setBackground(Color.BLACK);
        home.setFont(new Font("Tahoma", Font.PLAIN, 25));
        home.addActionListener(this);
        mb.add(home);

        dashboard = new JMenuItem("DASHBOARD");
        dashboard.setForeground(Color.WHITE);
        dashboard.setBackground(Color.BLACK);
        dashboard.setFont(new Font("Tahoma", Font.PLAIN, 25));
        dashboard.addActionListener(this);
        mb.add(dashboard);

        about = new JMenuItem("ABOUT");
        about.setForeground(Color.WHITE);
        about.setBackground(Color.BLACK);
        about.setFont(new Font("Tahoma", Font.PLAIN, 25));
        about.addActionListener(this);
        mb.add(about);

        contact = new JMenuItem("CONTACT");
        contact.setForeground(Color.WHITE);
        contact.setBackground(Color.BLACK);
        contact.setFont(new Font("Tahoma", Font.PLAIN, 25));
        contact.addActionListener(this);
        mb.add(contact);

        logout = new JMenuItem("LOGOUT");
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.BLACK);
        logout.setFont(new Font("Tahoma", Font.PLAIN, 25));
        logout.addActionListener(this);
        mb.add(logout);

        JButton button1 = new JButton("Add Employee");
        button1.setBounds(40, 150, 150, 30);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        image.add(button1);

        JButton button2 = new JButton("Modify Employee");
        button2.setBounds(40, 250, 150, 30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);
        image.add(button2);

        JButton button3 = new JButton("Delete Employee");
        button3.setBounds(40, 350, 150, 30);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button3.addActionListener(this);
        image.add(button3);

        JButton button4 = new JButton("View Employees");
        button4.setBounds(40, 450, 150, 30);
        button4.setBackground(Color.BLACK);
        button4.setForeground(Color.WHITE);
        button4.addActionListener(this);
        image.add(button4);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            disposeAndOpenNewFrame(new Home());
        } else if (ae.getSource() == dashboard) {
            disposeAndOpenNewFrame(new Dashboard());
        } else if (ae.getSource() == about) {
            disposeAndOpenNewFrame(new About(getTitle()));
        } else if (ae.getSource() == contact) {
            disposeAndOpenNewFrame(new Contact());
        } else if (ae.getSource() == logout) {
            disposeAndOpenNewFrame(new EmployeeManagementSystem());
        } else if (ae.getActionCommand().equals("Add Employee")) {
            new AddEmployee();
        } else if (ae.getActionCommand().equals("Modify Employee")) {
            String enteredEmployeeID = JOptionPane.showInputDialog("Enter Employee ID to Modify:");

            if (enteredEmployeeID != null && !enteredEmployeeID.isEmpty()) {
                new ModifyEmployee();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Employee ID. Please enter a valid Employee ID.");
            }
        } else if (ae.getActionCommand().equals("Delete Employee")) {
            String enteredEmployeeID = JOptionPane.showInputDialog("Enter Employee ID to Delete:");

            if (enteredEmployeeID != null && !enteredEmployeeID.isEmpty()) {
                new DeleteEmployee(enteredEmployeeID);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Employee ID. Please enter a valid Employee ID.");
            }
        } else if (ae.getActionCommand().equals("View Employees")) {
            new ViewEmployee();
        }
    }

    private void disposeAndOpenNewFrame(JFrame newFrame) {
        if (isVisible()) {
            dispose(); // Close the current frame
        }
        newFrame.setVisible(true); // Open the new frame
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
