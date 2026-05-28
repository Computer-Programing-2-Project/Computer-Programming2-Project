/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package polyfit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Administrative Form managing entity verification updates and record purge commands.
 * @author huzaifasuhail
 */
public class EditEmployeeDetailsForm extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private GymSystem gym;
    private MainMenu mainMenu;
    private Employee activeTarget = null;

    // Interface Form Elements
    private JTextField searchIdTxt;
    private JTextField fNameTxt;
    private JTextField lNameTxt;
    private JTextField addressTxt;
    private JTextField phoneTxt;
    private JTextField salaryTxt;
    private JLabel typeStatusLbl;
    private JButton searchBtn;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JButton backBtn;

    public EditEmployeeDetailsForm(GymSystem gym, MainMenu mainMenu) {
        this.gym = gym;
        this.mainMenu = mainMenu;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit Administration System - Edit Employee Records");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 560);

        JPanel outerPanel = new JPanel(new BorderLayout(12, 15));
        outerPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        outerPanel.setBackground(new Color(245, 247, 250));

        // --- Top Row Entry Lookup Control Region ---
        JPanel searchContainer = new JPanel(new BorderLayout(10, 0));
        searchContainer.setBackground(Color.WHITE);
        searchContainer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 12, 10, 12)
        ));

        JLabel searchPrompt = new JLabel("Enter Staff Track ID:");
        searchPrompt.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchIdTxt = new JTextField();
        searchBtn = new JButton("Fetch Profile");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchBtn.setFocusPainted(false);

        // Fix for Search Button near the top
        searchBtn.setBorderPainted(false);
        searchBtn.setOpaque(true);
        searchBtn.setContentAreaFilled(true);
        searchBtn.setBackground(new Color(63, 81, 181));
        searchBtn.setForeground(Color.WHITE);

        searchContainer.add(searchPrompt, BorderLayout.WEST);
        searchContainer.add(searchIdTxt, BorderLayout.CENTER);
        searchContainer.add(searchBtn, BorderLayout.EAST);
        outerPanel.add(searchContainer, BorderLayout.NORTH);

        // --- Core Context Data Registry Updates Grid ---
        JPanel fieldsGrid = new JPanel(new GridLayout(6, 2, 10, 15));
        fieldsGrid.setOpaque(false);
        Font lblFont = new Font("Segoe UI", Font.BOLD, 13);

        JLabel typeLabel = new JLabel("Classification System Type:");
        typeLabel.setFont(lblFont);
        fieldsGrid.add(typeLabel);
        typeStatusLbl = new JLabel("[No Profile Context Loaded]");
        typeStatusLbl.setFont(new Font("Segoe UI", Font.ITALIC | Font.BOLD, 13));
        typeStatusLbl.setForeground(Color.GRAY);
        fieldsGrid.add(typeStatusLbl);

        JLabel firstNameLabel = new JLabel("Modify First Name:");
        firstNameLabel.setFont(lblFont);
        fieldsGrid.add(firstNameLabel);
        fNameTxt = new JTextField();
        fieldsGrid.add(fNameTxt);

        JLabel lastNameLabel = new JLabel("Modify Last Name:");
        lastNameLabel.setFont(lblFont);
        fieldsGrid.add(lastNameLabel);
        lNameTxt = new JTextField();
        fieldsGrid.add(lNameTxt);

        JLabel addressLabel = new JLabel("Modify Home Address:");
        addressLabel.setFont(lblFont);
        fieldsGrid.add(addressLabel);
        addressTxt = new JTextField();
        fieldsGrid.add(addressTxt);

        JLabel phoneLabel = new JLabel("Modify Phone Line:");
        phoneLabel.setFont(lblFont);
        fieldsGrid.add(phoneLabel);
        phoneTxt = new JTextField();
        fieldsGrid.add(phoneTxt);

        JLabel salaryLabel = new JLabel("Modify Salary Rates ($):");
        salaryLabel.setFont(lblFont);
        fieldsGrid.add(salaryLabel);
        salaryTxt = new JTextField();
        fieldsGrid.add(salaryTxt);

        outerPanel.add(fieldsGrid, BorderLayout.CENTER);

        // --- Bottom Actions Control Rows Menu ---
        JPanel bottomBar = new JPanel(new GridLayout(1, 3, 10, 0));
        bottomBar.setOpaque(false);

        saveBtn = new JButton("Commit Changes");
        deleteBtn = new JButton("Purge Profile");
        backBtn = new JButton("Back to Dashboard");

        JButton[] operationalRow = {saveBtn, deleteBtn, backBtn};
        for (JButton button : operationalRow) {
            button.setFont(new Font("Segoe UI", Font.BOLD, 13));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(0, 40));
            
            // THE ULTIMATE CROSS-PLATFORM COLOR FIX:
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
        }

        saveBtn.setBackground(new Color(46, 125, 50));
        saveBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(198, 40, 40));
        deleteBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(117, 117, 117));
        backBtn.setForeground(Color.WHITE);

        bottomBar.add(saveBtn);
        bottomBar.add(deleteBtn);
        bottomBar.add(backBtn);
        outerPanel.add(bottomBar, BorderLayout.SOUTH);

        add(outerPanel);

        // Toggle state fields closed on layout construction pass
        toggleInputState(false);

        // --- Application Action Mappings Configuration ---
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                executeProfileLookup();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                executeDataUpdates();
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                executePurgeSequence();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mainMenu.refreshStatisticsSummary();
                mainMenu.setVisible(true);
                EditEmployeeDetailsForm.this.dispose();
            }
        });
    }

    private void toggleInputState(boolean state) {
        fNameTxt.setEnabled(state);
        lNameTxt.setEnabled(state);
        addressTxt.setEnabled(state);
        phoneTxt.setEnabled(state);
        salaryTxt.setEnabled(state);
        saveBtn.setEnabled(state);
        deleteBtn.setEnabled(state);
    }

    private void executeProfileLookup() {
        try {
            int lookupId = Integer.parseInt(searchIdTxt.getText().trim());
            Employee emp = gym.findEmployeeById(lookupId);

            if (emp == null) {
                typeStatusLbl.setText("[No Profile Context Loaded]");
                typeStatusLbl.setForeground(Color.GRAY);
                toggleInputState(false);
                JOptionPane.showMessageDialog(this, "No profile located matching that Staff Track ID.", "Zero Matches", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            activeTarget = emp;
            fNameTxt.setText(emp.getFirstName());
            lNameTxt.setText(emp.getLastName());
            addressTxt.setText(emp.getAddress());
            phoneTxt.setText(emp.getPhone());
            salaryTxt.setText(String.valueOf(emp.getSalary()));

            if (emp instanceof PersonalTrainer) {
                typeStatusLbl.setText("PERSONAL TRAINER");
                typeStatusLbl.setForeground(new Color(21, 101, 192));
            } else {
                typeStatusLbl.setText("STANDARD STAFF EMPLOYEE");
                typeStatusLbl.setForeground(new Color(55, 71, 79));
            }

            toggleInputState(true);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "The Lookup reference index must remain a valid integer string value.", "Parser Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void executeDataUpdates() {
        if (activeTarget == null) return;
        try {
            String fName = fNameTxt.getText().trim();
            String lName = lNameTxt.getText().trim();
            double salaryValue = Double.parseDouble(salaryTxt.getText().trim());

            if (fName.isEmpty() || lName.isEmpty()) {
                throw new IllegalArgumentException("Name registration values cannot consist of empty parameters.");
            }
            if (salaryValue < 0) {
                throw new IllegalArgumentException("Salary scales cannot navigate into negative numeric values.");
            }

            activeTarget.setFirstName(fName);
            activeTarget.setLastName(lName);
            activeTarget.setAddress(addressTxt.getText().trim());
            activeTarget.setPhone(phoneTxt.getText().trim());
            activeTarget.setSalary(salaryValue);

            JOptionPane.showMessageDialog(this, "Modifications synchronized into active data runtime context successfully.", "System Updated", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Salary inputs require valid primitive floating digit definitions.", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Data Synchronization Halt", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void executePurgeSequence() {
        if (activeTarget == null) return;
        try {
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you entirely certain you want to purge employee profile tracking record index " + activeTarget.getStaffId() + "?", 
                "Confirm Delete Request", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmation == JOptionPane.YES_OPTION) {
                gym.deleteEmployee(activeTarget.getStaffId());
                JOptionPane.showMessageDialog(this, "Entity storage completely removed from active tracking maps context.");
                activeTarget = null;
                searchIdTxt.setText("");
                fNameTxt.setText("");
                lNameTxt.setText("");
                addressTxt.setText("");
                phoneTxt.setText("");
                salaryTxt.setText("");
                typeStatusLbl.setText("[No Profile Context Loaded]");
                typeStatusLbl.setForeground(Color.GRAY);
                toggleInputState(false);
            }
        } catch (IllegalStateException ise) {
            JOptionPane.showMessageDialog(this, ise.getMessage(), "Referential Integrity Conflict", JOptionPane.ERROR_MESSAGE);
        }
    }
}