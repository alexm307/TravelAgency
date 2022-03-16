package controller;

import model.Destination;
import model.Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class AgencyView extends JFrame{

        private AgencyController agencyController;

        private JButton createProductButton;    //buttons for doing CRUD on products :)
        private JButton readProductButton;
        private JButton updateProductButton;
        private JButton deleteProductButton;
        private JButton backButton;

        private JLabel nameLabel;
        private JTextField nameField;

        private JLabel priceLabel;
        private JTextField priceField;

        private JLabel detailsLabel;
        private JTextField detailsField;

        private JLabel spotsLabel;
        private JTextField spotsField;

        private JLabel endDateLabel;
        private JTextField endDateField;

        private JLabel startDateLabel;
        private JTextField startDateField;

        private JLabel fromDayLabel;
        private JTextField fromDayField;

        private JLabel fromMonthLabel;
        private JTextField fromMonthField;

        private JLabel toDayLabel;
        private JTextField toDayField;

        private JLabel toMonthLabel;
        private JTextField toMonthField;

        private JButton createDestination;

        private JButton deleteDestButton;

        private JButton reportsButton;

        private JList<Package> packageJList;

        private JList<Destination> destinationJList;

        JTextArea packageList;

        public AgencyView (AgencyController agencyController) {
            this.agencyController = agencyController;

            this.setTitle("Admin");
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 150, 1200, 1000);
            this.getContentPane().setLayout(new FlowLayout());
            this.getContentPane().setBackground(new Color(220,220,250));

            deleteDestButton = new JButton("Delete Destination");
            deleteDestButton.setBounds(300, 880, 200, 50);

            createDestination = new JButton("Create Destination");
            createDestination.setBounds(10, 890, 200, 50);

            createProductButton = new JButton("Create Product");
            createProductButton.setBounds(10, 430, 150, 40);

            readProductButton = new JButton("See Packages/Dest");
            readProductButton.setBounds(10, 480, 150, 40);

            updateProductButton = new JButton("Update Package");
            updateProductButton.setBounds(10, 530, 150, 40);

            deleteProductButton = new JButton("Delete Package");
            deleteProductButton.setBounds(10, 580, 150, 40);

            nameLabel = new JLabel("Name:");
            nameLabel.setBounds(10, 20, 50, 20);
            nameField = new JTextField(20);

            detailsLabel = new JLabel("Details:");
            detailsLabel.setBounds(10, 80, 50, 20);
            detailsField = new JTextField(20);

            spotsLabel = new JLabel("Spot Nr.:");
            spotsLabel.setBounds(10, 140, 50, 20);
            spotsField = new JTextField(20);

            fromDayLabel = new JLabel("From day:");
            fromDayLabel.setBounds(10, 200, 50, 20);
            fromDayField = new JTextField(20);

            fromMonthLabel = new JLabel("From month:");
            fromMonthLabel.setBounds(10, 230, 50, 20);
            fromMonthField = new JTextField(20);

            toDayLabel = new JLabel("To day:");
            toDayLabel.setBounds(10, 200, 50, 20);
            toDayField = new JTextField(20);

            toMonthLabel = new JLabel("To month:");
            toMonthLabel.setBounds(10, 230, 50, 20);
            toMonthField = new JTextField(20);

            priceLabel = new JLabel("Price:");
            priceLabel.setBounds(10, 260, 200, 20);
            priceField = new JTextField(20);

            packageList = new JTextArea();
            packageList.setBounds(250, 10, 700, 700);
            packageList.setEditable(false);

            backButton = new JButton("BACK");

            packageJList = new JList<Package>();
            packageJList.setBounds(300, 30, 900, 400);
            packageJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
            JPanel panel = new JPanel();
            panel.setBounds(300, 100, 200, 200);
            panel.setBackground(Color.blue);
            JScrollPane scrollPane = new JScrollPane(packageJList);
            scrollPane.setPreferredSize(new Dimension(900, 400));

            destinationJList = new JList<Destination>();
            destinationJList.setBounds(300, 430, 900, 400);
            destinationJList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);   //trebuie apasat CTRL sa selectezi mai multe
            JPanel panel2 = new JPanel();
            panel2.setBounds(300, 500, 200, 200);
            panel2.setBackground(Color.blue);
            JScrollPane scrollPane2 = new JScrollPane(destinationJList);
            scrollPane2.setPreferredSize(new Dimension(900, 400));

            reportsButton = new JButton("Reports");

            this.getContentPane().add(createProductButton);
            this.getContentPane().add(readProductButton);
            this.getContentPane().add(deleteProductButton);
            this.getContentPane().add(updateProductButton);
            this.getContentPane().add(createDestination);
            this.getContentPane().add(nameLabel);
            this.getContentPane().add(nameField);
            this.getContentPane().add(spotsLabel);
            this.getContentPane().add(spotsField);
            //this.getContentPane().add(endDateLabel);
            //this.getContentPane().add(endDateField);
            //this.getContentPane().add(startDateLabel);
            //this.getContentPane().add(startDateField);
            this.getContentPane().add(fromDayLabel);
            this.getContentPane().add(fromDayField);
            this.getContentPane().add(fromMonthLabel);
            this.getContentPane().add(fromMonthField);

            this.getContentPane().add(toDayLabel);
            this.getContentPane().add(toDayField);
            this.getContentPane().add(toMonthLabel);
            this.getContentPane().add(toMonthField);

            this.getContentPane().add(priceLabel);
            this.getContentPane().add(priceField);
            this.getContentPane().add(detailsLabel);
            this.getContentPane().add(detailsField);
            this.getContentPane().add(deleteDestButton);
            this.getContentPane().add(scrollPane);
            this.getContentPane().add(scrollPane2);
            this.getContentPane().add(reportsButton);
            this.getContentPane().add(backButton);

            this.addActionListeners();
        }

        public void addList(JList<Package> packageList) {
            this.setVisible(false);
            this.getContentPane().add(packageList);
            this.setVisible(true);
        }

        /**
         * Method for action listeners.
         */
        public void addActionListeners() {

            createProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if (nameField.getText().isEmpty() || priceField.getText().isEmpty() || detailsField.getText().isEmpty()
                    || spotsField.getText().isEmpty() || destinationJList.getSelectedValue() == null || fromDayField.getText().isEmpty()
                    || fromMonthField.getText().isEmpty() || toDayField.getText().isEmpty() || toMonthField.getText().isEmpty())
                        JOptionPane.showMessageDialog(null, "COULDN'T CREATE PACKAGE!");

                    else {
                        Date fromDate = new Date();
                        Date toDate = new Date();
                        fromDate.setDate(Integer.parseInt(fromDayField.getText()));//ziua lunii
                        fromDate.setMonth(Integer.parseInt(fromMonthField.getText()) - 1);//luna ca numar
                        toDate.setDate(Integer.parseInt(toDayField.getText()));//ziua lunii
                        toDate.setMonth(Integer.parseInt(toMonthField.getText()) -1 );//luna

                        if (fromDate.before(new Date()) || toDate.before(new Date())) {
                            JOptionPane.showMessageDialog(null, "ILLEGAL TIMELINE - COULDN'T CREATE PACKAGE!");
                            return;
                        }
                        Package newPackage = new Package(nameField.getText(), Double.parseDouble(priceField.getText())
                                , fromDate, toDate, detailsField.getText(), Integer.parseInt(spotsField.getText())
                                , destinationJList.getSelectedValue());
                        agencyController.createPackage(newPackage);
                        JOptionPane.showMessageDialog(null, "Package created!");
                        packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    }
                    }
            });

            readProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    destinationJList.setListData(agencyController.viewDestinations().toArray(new Destination[0]));
                    System.out.println("hope it worked");
                }
            });

            deleteProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agencyController.deletePackage(packageJList.getSelectedValue());
                    packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    destinationJList.setListData(agencyController.viewDestinations().toArray(new Destination[0]));

                    JOptionPane.showMessageDialog(null, "Package deleted!");
                }
            });

            updateProductButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Package updatedPackage = packageJList.getSelectedValue();
                    if (!spotsField.getText().isEmpty())
                        updatedPackage.setCurrentSpots(Integer.parseInt(spotsField.getText()));
                    if (!detailsField.getText().isEmpty())
                        updatedPackage.setDetails(detailsField.getText());
                    if (!priceField.getText().isEmpty())
                        updatedPackage.setPrice(Double.parseDouble(priceField.getText()));
                    if (!nameField.getText().isEmpty())
                        updatedPackage.setName(nameField.getText());

                    /*
                    if (agencyController.editPackage(updatedPackage))
                        JOptionPane.showMessageDialog(null, "Package updated!");
                    else
                        JOptionPane.showMessageDialog(null, "Product not found!");
                    packageJList.setListData(deliveryService.getFullMenu().toArray(new MenuItem[0]));
                     */

                    agencyController.editPackage(updatedPackage);
                    packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    destinationJList.setListData(agencyController.viewDestinations().toArray(new Destination[0]));

                    JOptionPane.showMessageDialog(null, "Package updated!");
                }
            });

            createDestination.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Destination newDestination = new Destination(nameField.getText());
                    agencyController.createDestination(newDestination);
                    packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    destinationJList.setListData(agencyController.viewDestinations().toArray(new Destination[0]));

                    JOptionPane.showMessageDialog(null, "Destination created!");
                }
            });


            deleteDestButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agencyController.deleteDestination(destinationJList.getSelectedValue());
                    packageJList.setListData(agencyController.viewPackages().toArray(new Package[0]));
                    destinationJList.setListData(agencyController.viewDestinations().toArray(new Destination[0]));

                    JOptionPane.showMessageDialog(null, "Destination deleted!");
                }
            });
/*
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    deliveryService.goBack();
                }
            });
             */
        }

}
