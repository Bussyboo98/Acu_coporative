/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.acu_cooperative;

/**
 *
 * @author BUSAYO
 */
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class Member {
    private String name;
    private String nin;
    private String address;
    private int yearOfBirth;
    private double totalContributions;

    public Member(String name, String nin, String address, int yearOfBirth) {
        this.name = name;
        this.nin = nin;
        this.address = address;
        this.yearOfBirth = yearOfBirth;
        this.totalContributions = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getNin() {
        return nin;
    }

    public String getAddress() {
        return address;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getAge() {
        return java.time.LocalDate.now().getYear() - yearOfBirth;
    }

    public double getTotalContributions() {
        return totalContributions;
    }

    public void addContribution(double amount) {
        this.totalContributions += amount;
    }

    @Override
    public String toString() {
        return "Member{name='" + name + "', nin='" + nin + "', address='" + address + "', yearOfBirth=" + yearOfBirth + ", totalContributions=" + totalContributions + "}";
    }
}

class Contribution {
    private Map<String, Double> contributions;

    public Contribution() {
        contributions = new HashMap<>();
    }

    public void payDues(Member member, double amount) {
        member.addContribution(amount);
        contributions.put(member.getNin(), member.getTotalContributions());
    }

    public double getTotalContributions(String nin) {
        return contributions.getOrDefault(nin, 0.0);
    }
}

class Loan {
    public double calculateEligibility(Member member) {
        return member.getTotalContributions() * 2; // Example: can get up to twice the contribution amount
    }

    public boolean applyForLoan(Member member, double loanAmount) {
        double eligibility = calculateEligibility(member);
        if (loanAmount <= eligibility) {
            System.out.println("Loan approved for " + member.getName());
            return true;
        } else {
            System.out.println("Loan denied for " + member.getName() + ". Eligible amount: " + eligibility);
            return false;
        }
    }
}

public class Acu_Cooperative extends Frame implements ActionListener {
    private static Map<String, Member> memberMap = new HashMap<>();
    private Contribution contribution = new Contribution();
    private Loan loan = new Loan();

    private CardLayout cardLayout = new CardLayout();
    private Panel mainPanel = new Panel(cardLayout);

    private TextField nameField = new TextField(20);
    private TextField ninFieldRegister = new TextField(20);
    private TextField ninFieldPay = new TextField(20);
    private TextField ninFieldLoan = new TextField(20);
    private TextField ninFieldDetails = new TextField(20);
    private TextField addressField = new TextField(20);
    private TextField yearOfBirthField = new TextField(20);
    private TextField amountField = new TextField(20);
    private TextField loanAmountField = new TextField(20);

    public Acu_Cooperative() {
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        Panel menuPanel = new Panel();
        menuPanel.setLayout(new GridLayout(5, 1));
        add(menuPanel, BorderLayout.WEST);

        // Adding titles
        Panel titlePanel = new Panel();
        titlePanel.setLayout(new GridLayout(6, 1));
        titlePanel.add(new Label("GROUP 9", Label.CENTER));
        titlePanel.add(new Label("ACU COOPERATIVE SOCIETY", Label.CENTER));
        titlePanel.add(new Label("COMPUTER SCIENCE", Label.CENTER));
        titlePanel.add(new Label("200 LEVEL", Label.CENTER));
        titlePanel.add(new Label("CSC2206", Label.CENTER));
        titlePanel.add(new Label("----------------------------------------------------------------------------------------------------------------------------------------------------------------"));
        add(titlePanel, BorderLayout.NORTH);

        Button registerButton = new Button("Register Member");
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "Register"));
        menuPanel.add(registerButton);

        Button payDuesButton = new Button("Pay Dues");
        payDuesButton.addActionListener(e -> cardLayout.show(mainPanel, "PayDues"));
        menuPanel.add(payDuesButton);

        Button applyLoanButton = new Button("Apply for Loan");
        applyLoanButton.addActionListener(e -> cardLayout.show(mainPanel, "ApplyLoan"));
        menuPanel.add(applyLoanButton);

        Button viewDetailsButton = new Button("View Total Dues and Loan Eligibility");
        viewDetailsButton.addActionListener(e -> cardLayout.show(mainPanel, "ViewDetails"));
        menuPanel.add(viewDetailsButton);

        Button exitButton = new Button("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        menuPanel.add(exitButton);

        setupRegisterPanel();
        setupPayDuesPanel();
        setupApplyLoanPanel();
        setupViewDetailsPanel();

        setTitle("Cooperative Society");
        setSize(600, 400);
        setVisible(true);
    }

    private void setupRegisterPanel() {
        Panel registerPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        registerPanel.add(new Label("Name:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        registerPanel.add(new Label("NIN:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(ninFieldRegister, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        registerPanel.add(new Label("Address:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(addressField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        registerPanel.add(new Label("Year of Birth:"), gbc);
        gbc.gridx = 1;
        registerPanel.add(yearOfBirthField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        Button register = new Button("Register");
        register.addActionListener(this);
        registerPanel.add(register, gbc);

        mainPanel.add(registerPanel, "Register");
    }

    private void setupPayDuesPanel() {
        Panel payDuesPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        payDuesPanel.add(new Label("NIN:"), gbc);
        gbc.gridx = 1;
        payDuesPanel.add(ninFieldPay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        payDuesPanel.add(new Label("Amount:"), gbc);
        gbc.gridx = 1;
        payDuesPanel.add(amountField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        Button payDues = new Button("Pay Dues");
        payDues.addActionListener(this);
        payDuesPanel.add(payDues, gbc);

        mainPanel.add(payDuesPanel, "PayDues");
    }

    private void setupApplyLoanPanel() {
        Panel applyLoanPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        applyLoanPanel.add(new Label("NIN:"), gbc);
        gbc.gridx = 1;
        applyLoanPanel.add(ninFieldLoan, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        applyLoanPanel.add(new Label("Loan Amount:"), gbc);
        gbc.gridx = 1;
        applyLoanPanel.add(loanAmountField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        Button applyLoan = new Button("Apply for Loan");
        applyLoan.addActionListener(this);
        applyLoanPanel.add(applyLoan, gbc);

        mainPanel.add(applyLoanPanel, "ApplyLoan");
    }

    private void setupViewDetailsPanel() {
        Panel viewDetailsPanel = new Panel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        viewDetailsPanel.add(new Label("NIN:"), gbc);
        gbc.gridx = 1;
        viewDetailsPanel.add(ninFieldDetails, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        Button viewDetails = new Button("View Details");
        viewDetails.addActionListener(this);
        viewDetailsPanel.add(viewDetails, gbc);

        mainPanel.add(viewDetailsPanel, "ViewDetails");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("Register")) {
        String name = nameField.getText();
        String nin = ninFieldRegister.getText();
        String address = addressField.getText();
        int yearOfBirth = Integer.parseInt(yearOfBirthField.getText());
        int age = java.time.LocalDate.now().getYear() - yearOfBirth;
        if (age < 18) {
            System.out.println(name + "," + "Too young to register.");
            return;
        }
        Member newMember = new Member(name, nin, address, yearOfBirth);
        memberMap.put(nin, newMember);
        System.out.println("Member registered: " + newMember);
    } else if (command.equals("Pay Dues")) {
        String nin = ninFieldPay.getText();
        Member existingMember = memberMap.get(nin);
        if (existingMember == null) {
            System.out.println("Member not found.");
            return;
        }
        double amount = Double.parseDouble(amountField.getText());
        contribution.payDues(existingMember, amount);
        System.out.println("Dues paid. Total contributions: " + existingMember.getTotalContributions());
    } else if (command.equals("Apply for Loan")) {
        String nin = ninFieldLoan.getText();
        Member existingMember = memberMap.get(nin);
        if (existingMember == null) {
            System.out.println("Member not found.");
            return;
        }
        if (existingMember.getTotalContributions() < 100000) {
            System.out.println("Total contributions less than 100,000. Not eligible for a loan.");
            return;
        }
        double loanAmount = Double.parseDouble(loanAmountField.getText());
        loan.applyForLoan(existingMember, loanAmount);
    } else if (command.equals("View Details")) {
        String nin = ninFieldDetails.getText();
        Member existingMember = memberMap.get(nin);
        if (existingMember == null) {
            System.out.println("Member not found.");
            return;
        }
        String name = existingMember.getName();
        double totalContributions = existingMember.getTotalContributions();
        double loanEligibility = loan.calculateEligibility(existingMember);
        System.out.println("Name: " + name);
        System.out.println("Total Contributions: " + totalContributions);
        System.out.println("Loan Eligibility: " + loanEligibility);
    }
}
    public static void main(String[] args) {
        new Acu_Cooperative();
    }
}
