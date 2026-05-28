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
 * Completed Main Navigation Dashboard matching all system requirements.
 * @author huzaifasuhail
 */
public class MainMenu extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    
    private GymSystem gym;

    private JLabel titleLabel;
    private JLabel memberStatLabel;
    private JLabel employeeStatLabel;
    private JLabel trainerStatLabel;
    
    private JButton addEmployeeBtn;
    private JButton editEmployeeBtn;
    private JButton addMemberBtn;
    private JButton editMemberBtn;
    private JButton assignTrainerBtn;
    private JButton reportBtn;
    private JButton exitBtn;

    public MainMenu(GymSystem gym) {
        this.gym = gym;
        initComponents();
        refreshStatisticsSummary();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit Gym Management System - Main Menu");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(550, 580);
        
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
        mainPanel.setBackground(new Color(245, 247, 250));

        titleLabel = new JLabel("POLYFIT GYM SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(33, 43, 54));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // --- Statistics Dashboard Area ---
        JPanel statsPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(224, 224, 224), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));

        Font statFont = new Font("Segoe UI", Font.BOLD, 14);
        memberStatLabel = new JLabel("Total Registered Members: 0");
        employeeStatLabel = new JLabel("Total System Employees: 0");
        trainerStatLabel = new JLabel("Active Personal Trainers: 0");
        
        memberStatLabel.setFont(statFont);
        employeeStatLabel.setFont(statFont);
        trainerStatLabel.setFont(statFont);

        statsPanel.add(memberStatLabel);
        statsPanel.add(employeeStatLabel);
        statsPanel.add(trainerStatLabel);
        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // --- Grid Controls Navigation Menu ---
        JPanel controlsPanel = new JPanel(new GridLayout(7, 1, 0, 10));
        controlsPanel.setOpaque(false);

        addEmployeeBtn = new JButton("Add New Employee");
        editEmployeeBtn = new JButton("Edit Employee Details");
        addMemberBtn = new JButton("Add New Member");
        editMemberBtn = new JButton("Edit Member Details");
        assignTrainerBtn = new JButton("Assign / Remove Trainer");
        reportBtn = new JButton("Generate Marketing Report");
        exitBtn = new JButton("Exit & Save State");

        // Set matching layouts across operational triggers
        Font btnFont = new Font("Segoe UI", Font.BOLD, 14);
        JButton[] buttons = {addEmployeeBtn, editEmployeeBtn, addMemberBtn, editMemberBtn, assignTrainerBtn, reportBtn, exitBtn};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            
            // THE ULTIMATE CROSS-PLATFORM COLOR FIX:
            btn.setBorderPainted(false);
            btn.setOpaque(true);
            btn.setContentAreaFilled(true); 
            
            btn.setBackground(new Color(63, 81, 181));
            btn.setForeground(Color.WHITE);
        }
        
        // Accent highlights targeting exit operations
        exitBtn.setBackground(new Color(211, 47, 47));
        
        exitBtn.setBackground(new Color(211, 47, 47));

        controlsPanel.add(addEmployeeBtn);
        controlsPanel.add(editEmployeeBtn);
        controlsPanel.add(addMemberBtn);
        controlsPanel.add(editMemberBtn);
        controlsPanel.add(assignTrainerBtn);
        controlsPanel.add(reportBtn);
        controlsPanel.add(exitBtn);
        mainPanel.add(controlsPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // --- Navigation Actions ---
        addEmployeeBtn.addActionListener(e -> {
            new AddNewEmployeeForm(gym, this).setVisible(true);
            this.setVisible(false);
        });

        editEmployeeBtn.addActionListener(e -> {
            new EditEmployeeDetailsForm(gym, this).setVisible(true);
            this.setVisible(false);
        });

        addMemberBtn.addActionListener(e -> {
            new AddNewMemberForm(gym, this).setVisible(true);
            this.setVisible(false);
        });

        editMemberBtn.addActionListener(e -> {
            new EditMemberDetailsForm(gym, this).setVisible(true);
            this.setVisible(false);
        });

        assignTrainerBtn.addActionListener(e -> {
            new AssignTrainerForm(gym, this).setVisible(true);
            this.setVisible(false);
        });

        reportBtn.addActionListener(e -> {
            gym.generateMarketingReport();
            JOptionPane.showMessageDialog(this, "Report saved as marketingReport.txt successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        exitBtn.addActionListener(e -> {
            FileHandler.saveSystem(gym);
            System.exit(0);
        });
    }

    public void refreshStatisticsSummary() {
        memberStatLabel.setText("Total Registered Members: " + gym.getMembers().size());
        employeeStatLabel.setText("Total System Employees: " + gym.getEmployees().size());
        trainerStatLabel.setText("Active Personal Trainers: " + gym.getTrainers().size());
    }
}