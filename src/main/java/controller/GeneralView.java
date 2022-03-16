package controller;

import model.User;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralView extends JFrame {      // will provide login window

    private JButton loginButton;
    private JButton registerButton;
    private JButton agencyButton;

    private JLabel usernameLabel;
    private JTextField usernameField;

    private JLabel mailLabel;
    private JTextField mailField;

    private JLabel passwordLabel;
    private JPasswordField passwordField;

    private UserController userController;
    private AgencyController agencyController;

    private User user;

    public GeneralView (AgencyController agencyController, EntityManagerFactory entityManagerFactory) {
        this.agencyController = agencyController;
        this.userController = new UserController(entityManagerFactory);
        this.setTitle("Log In");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 250, 370);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(220,220,250));

        usernameLabel = new JLabel("Please enter username:");
        usernameLabel.setBounds(10, 10, 200, 20);
        usernameField = new JTextField();
        usernameField.setBounds(10, 30, 200, 20);

        mailLabel = new JLabel("Please enter email:");
        mailLabel.setBounds(10, 60, 200, 20);
        mailField = new JTextField();
        mailField.setBounds(10, 90, 200, 20);

        passwordLabel = new JLabel("Please enter password:");
        passwordLabel.setBounds(10, 120, 200, 20);
        passwordField = new JPasswordField();
        passwordField.setBounds(10, 150, 200, 20);

        loginButton = new JButton("LOGIN");
        loginButton.setBounds(50, 180, 100, 50);

        registerButton = new JButton("REGISTER");
        registerButton.setBounds(50, 240, 100, 50);

        agencyButton = new JButton("AGENCY");
        agencyButton.setBounds(50, 300, 100, 50);

        this.getContentPane().add(usernameField);
        this.getContentPane().add(usernameLabel);
        this.getContentPane().add(passwordField);
        this.getContentPane().add(passwordLabel);
        this.getContentPane().add(loginButton);
        this.getContentPane().add(registerButton);
        this.getContentPane().add(agencyButton);
        this.getContentPane().add(mailField);
        this.getContentPane().add(mailLabel);

        this.addActionListeners();
        this.addRegisterActionListener();
    }

    /**
     * Methods for adding action listeners.
     */
    public void addRegisterActionListener() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                if (mailField.getText().contains("@") && mailField.getText().contains(".com")) {
                    userController.createUser(usernameField.getText(), mailField.getText(), password);
                    JOptionPane.showMessageDialog(null, "Succesfully created user!");
                }
                else
                    JOptionPane.showMessageDialog(null, "Email is NOT VALID!");
            }
        });
    }

    public void addActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                User loggedUser = userController.login(usernameField.getText(), password);
                if (loggedUser == null)
                    JOptionPane.showMessageDialog(null, "ACCOUNT NOT EXISTENT!");
                else {
                    setUser(loggedUser);
                    agencyController.login();
                }
            }
        });

        agencyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agencyController.startAgency();
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}