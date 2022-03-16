package controller;

import model.Package;
import model.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;




public class UserView extends JFrame {
    JButton viewPackagesButton;
    JButton searchButton;
    JButton bookPackageButton;
    JButton viewBookedButton;

    JComboBox<String> selectionCriteria;

    JLabel nameLabel;
    JTextField nameField;

    JLabel dateLabel;
    JTextField dateField;

    JLabel monthLabel;
    JTextField monthField;

    JList<Package> packageJList;

    private UserController userController;
    private User user;

    public UserView (EntityManagerFactory entityManagerFactory) {
        this.userController = new UserController(entityManagerFactory);
        this.setTitle("Client");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 1000, 1000);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(220,220,250));

        selectionCriteria = new JComboBox<>();
        selectionCriteria.addItem("Name");
        selectionCriteria.addItem("Price");
        selectionCriteria.addItem("Starting after");
        selectionCriteria.addItem("Ending before");

        nameLabel = new JLabel("Category:");
        nameLabel.setBounds(10, 20, 50, 20);
        nameField = new JTextField(20);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(10, 20, 50, 20);
        dateField = new JTextField(20);

        monthLabel = new JLabel("Month:");
        monthLabel.setBounds(10, 20, 50, 20);
        monthField = new JTextField(20);

        searchButton = new JButton("Search");
        searchButton.setBounds(55, 450, 100, 40);

        viewPackagesButton = new JButton("View Packages");
        viewPackagesButton.setBounds(10, 800, 200, 40);

        bookPackageButton = new JButton("Book selected package");

        packageJList = new JList<Package>();
        packageJList.setBounds(300, 30, 900, 700);
        packageJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
        JPanel panel = new JPanel();
        panel.setBounds(300, 100, 200, 200);
        panel.setBackground(Color.blue);
        JScrollPane scrollPane = new JScrollPane(packageJList);
        scrollPane.setPreferredSize(new Dimension(900, 800));

        viewBookedButton = new JButton("View Booked Packages");

        bookPackageButton = new JButton("Book Package");

        this.getContentPane().add(selectionCriteria);
        this.getContentPane().add(nameLabel);
        this.getContentPane().add(nameField);
        this.getContentPane().add(searchButton);

        this.getContentPane().add(dateLabel);
        this.getContentPane().add(dateField);
        this.getContentPane().add(monthLabel);
        this.getContentPane().add(monthField);
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(bookPackageButton);
        this.getContentPane().add(viewPackagesButton);
        this.getContentPane().add(viewBookedButton);

        this.addActionListeners();
    }


    public void setUser (User user) {
        this.user = user;
    }


    public void addActionListeners() {
        viewPackagesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageJList.setListData(userController.viewAllPackages().toArray(new Package[0]));
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Package> filteredPacks = userController.filterPackages(selectionCriteria.getSelectedIndex(), nameField.getText()
                        , Integer.parseInt(dateField.getText()), Integer.parseInt(monthField.getText()) - 1);
                if (filteredPacks != null && filteredPacks.size() > 0)
                    packageJList.setListData(filteredPacks.toArray(new Package[0]));
                else
                    JOptionPane.showMessageDialog(null, "PACKAGES NOT FOUND!");
            }
        });
        bookPackageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userController.bookPackage(getUser(), packageJList.getSelectedValue()))
                    JOptionPane.showMessageDialog(null, "SUCCESSFULLY BOOKED!");
                else
                    JOptionPane.showMessageDialog(null, "PACKAGE ALREADY FULLY BOOKED!");
            }
        });

        viewBookedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                packageJList.setListData(userController.viewBooked(getUser()).toArray(new Package[0]));
                //userController.viewBooked(getUser());
            }
        });
    }

    public User getUser() {
        return user;
    }
}