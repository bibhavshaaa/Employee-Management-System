package first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
    JMenuItem home, dashboard, about, contact, logout;

    Home() {
        setTitle("Home");
        setBounds(0, 0, 1550, 1000);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Home.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1500, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1500, 1000);
        add(image);

        JMenuBar mb = new JMenuBar();
        mb.setBounds(0, 10, 1550, 60);
        mb.setBackground(Color.BLACK);
        image.add(mb);

        home = createMenuItem("HOME");
        mb.add(home);

        dashboard = createMenuItem("DASHBOARD");
        mb.add(dashboard);

        about = createMenuItem("ABOUT");
        mb.add(about);

        contact = createMenuItem("CONTACT");
        mb.add(contact);

        logout = createMenuItem("LOGOUT");
        mb.add(logout);

        setVisible(true);
    }

    private JMenuItem createMenuItem(String title) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.setForeground(Color.WHITE);
        menuItem.setBackground(Color.BLACK);
        menuItem.setFont(new Font("Tahoma", Font.PLAIN, 25));
        menuItem.addActionListener(this);
        return menuItem;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == home) {
            // You are already in the Home frame, so no action needed
        } else if (ae.getSource() == dashboard) {
            disposeAndOpenNewFrame(new Dashboard());
        } else if (ae.getSource() == about) {
            disposeAndOpenNewFrame(new About(getTitle()));
        } else if (ae.getSource() == contact) {
            disposeAndOpenNewFrame(new Contact());
        } else if (ae.getSource() == logout) {
            disposeAndOpenNewFrame(new EmployeeManagementSystem());
        }
    }

    private void disposeAndOpenNewFrame(JFrame newFrame) {
        dispose();
        newFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home());
    }
}
