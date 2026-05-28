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
 * Graphical Form tracking registration procedures for fresh Employee profiles.
 * @author huzaifasuhail
 */
public class AddNewEmployeeForm extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private GymSystem gym;
    private MainMenu mainMenu;

    // GUI Form Controls
    private JTextField fNameTxt;
    private JTextField lNameTxt;
    private JTextField addressTxt;
    private JTextField phoneTxt;
    private JTextField salaryTxt;
    private JRadioButton ptYesRadio;
    private JRadioButton ptNoRadio;
    private ButtonGroup trainerGroup;
    private JButton addBtn;
    private JButton clearBtn;
    private JButton backBtn;

    public AddNewEmployeeForm(GymSystem gym, MainMenu mainMenu) {
        this.gym = gym;
        this.mainMenu = mainMenu;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit Administration System - Add New Employee");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(480, 520);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 15));
        contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        contentPanel.setBackground(new Color(245, 247, 250));

        JLabel titleLabel = new JLabel("REGISTER NEW EMPLOYEE PROFILE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(63, 81, 181));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // --- Grid Data Inputs Panel Layout Section ---
        JPanel formGrid = new JPanel(new GridLayout(6, 2, 10, 15));
        formGrid.setOpaque(false);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 13);
        
        formGrid.add(createStyledLabel("First Name *:", labelFont));
        fNameTxt = new JTextField();
        formGrid.add(fNameTxt);

        formGrid.add(createStyledLabel("Last Name *:", labelFont));
        lNameTxt = new JTextField();
        formGrid.add(lNameTxt);

        formGrid.add(createStyledLabel("Contact Address:", labelFont));
        addressTxt = new JTextField();
        formGrid.add(addressTxt);

        formGrid.add(createStyledLabel("Phone Number:", labelFont));
        phoneTxt = new JTextField();
        formGrid.add(phoneTxt);

        formGrid.add(createStyledLabel("Base Salary ($) *:", labelFont));
        salaryTxt = new JTextField();
        formGrid.add(salaryTxt);

        formGrid.add(createStyledLabel("Personal Trainer Category:", labelFont));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioPanel.setOpaque(false);
        ptYesRadio = new JRadioButton("Yes");
        ptNoRadio = new JRadioButton("No", true);
        trainerGroup = new ButtonGroup();
        trainerGroup.add(ptYesRadio);
        trainerGroup.add(ptNoRadio);
        radioPanel.add(ptYesRadio);
        radioPanel.add(ptNoRadio);
        formGrid.add(radioPanel);

        contentPanel.add(formGrid, BorderLayout.CENTER);

        // --- Footer Navigation Controls Button Row ---
        JPanel actionsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        actionsPanel.setOpaque(false);

        addBtn = new JButton("Add Record");
        clearBtn = new JButton("Clear Form");
        backBtn = new JButton("Back to Main");

        JButton[] actionButtons = {addBtn, clearBtn, backBtn};
        Font btnFont = new Font("Segoe UI", Font.BOLD, 13);
        for (JButton btn : actionButtons) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setPreferredSize(new Dimension(0, 38));
            
            // THE ULTIMATE CROSS-PLATFORM COLOR FIX:
            btn.setBorderPainted(false);
            btn.setOpaque(true);
            btn.setContentAreaFilled(true);
        }
        
        addBtn.setBackground(new Color(46, 125, 50));
        addBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(new Color(230, 81, 0));
        clearBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(117, 117, 117));
        backBtn.setForeground(Color.WHITE);

        actionsPanel.add(addBtn);
        actionsPanel.add(clearBtn);
        actionsPanel.add(backBtn);
        contentPanel.add(actionsPanel, BorderLayout.SOUTH);

        add(contentPanel);

        // --- Action Listeners Triggers Configuration ---
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                processAddEmployeeAction();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                clearFormFields();
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                mainMenu.refreshStatisticsSummary();
                mainMenu.setVisible(true);
                AddNewEmployeeForm.this.dispose();
            }
        });
    }

    private JLabel createStyledLabel(String text, Font font) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(font);
        lbl.setForeground(new Color(55, 71, 79));
        return lbl;
    }

    private void clearFormFields() {
        fNameTxt.setText("");
        lNameTxt.setText("");
        addressTxt.setText("");
        phoneTxt.setText("");
        salaryTxt.setText("");
        ptNoRadio.setSelected(true);
    }

    private void processAddEmployeeAction() {
        try {
            String firstName = fNameTxt.getText().trim();
            String lastName = lNameTxt.getText().trim();
            String address = addressTxt.getText().trim();
            String phone = phoneTxt.getText().trim();
            String salaryStr = salaryTxt.getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || salaryStr.isEmpty()) {
                throw new IllegalArgumentException("Fields tagged with asterisks (*) cannot remain blank.");
            }

            double salary = Double.parseDouble(salaryStr);
            int id = gym.getNextStaffId();
            boolean isTrainer = ptYesRadio.isSelected();

            // Run verification calls safely through domain engine mappings
            gym.addEmployee(id, firstName, lastName, address, phone, salary, isTrainer);

            JOptionPane.showMessageDialog(this, 
                "Profile logged successfully! Allocated Staff Track ID: " + id, 
                "Execution Complete", JOptionPane.INFORMATION_MESSAGE);
                
            backBtn.getActionListeners()[0].actionPerformed(null);

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Base Salary parameters must strictly contain numerical inputs.", "Format Exception", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Invalidation Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}