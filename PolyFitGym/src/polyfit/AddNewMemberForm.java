package polyfit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Form for registering new Gym Members (Staff / Students).
 * Features a dynamic field card listener layout.
 * @author huzaifasuhail
 */
public class AddNewMemberForm extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private GymSystem gym;
    private MainMenu mainMenu;

    private JTextField fNameTxt, lNameTxt, addressTxt, phoneTxt, dobTxt;
    private JComboBox<String> genderBox, typeBox;
    private JLabel extraLabel1, extraLabel2;
    private JTextField extraTxt1, extraTxt2;
    private JButton addBtn, clearBtn, backBtn;

    public AddNewMemberForm(GymSystem gym, MainMenu mainMenu) {
        this.gym = gym;
        this.mainMenu = mainMenu;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit System - Register Member");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(480, 580);

        JPanel contentPanel = new JPanel(new BorderLayout(10, 15));
        contentPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        contentPanel.setBackground(new Color(245, 247, 250));

        JLabel titleLabel = new JLabel("REGISTER NEW GYM MEMBER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(63, 81, 181));
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel formGrid = new JPanel(new GridLayout(9, 2, 10, 12));
        formGrid.setOpaque(false);
        Font lblFont = new Font("Segoe UI", Font.BOLD, 13);
        JLabel label;

        label = new JLabel("Member Type:");
        label.setFont(lblFont);
        formGrid.add(label);
        typeBox = new JComboBox<>(new String[]{"Student", "Staff"});
        formGrid.add(typeBox);

        label = new JLabel("First Name *:");
        label.setFont(lblFont);
        formGrid.add(label);
        fNameTxt = new JTextField();
        formGrid.add(fNameTxt);

        label = new JLabel("Last Name *:");
        label.setFont(lblFont);
        formGrid.add(label);
        lNameTxt = new JTextField();
        formGrid.add(lNameTxt);

        label = new JLabel("Date of Birth *:");
        label.setFont(lblFont);
        formGrid.add(label);
        dobTxt = new JTextField("DD/MM/YYYY");
        formGrid.add(dobTxt);

        label = new JLabel("Gender:");
        label.setFont(lblFont);
        formGrid.add(label);
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        formGrid.add(genderBox);

        label = new JLabel("Address:");
        label.setFont(lblFont);
        formGrid.add(label);
        addressTxt = new JTextField();
        formGrid.add(addressTxt);

        label = new JLabel("Phone Number:");
        label.setFont(lblFont);
        formGrid.add(label);
        phoneTxt = new JTextField();
        formGrid.add(phoneTxt);

        // --- Dynamic Custom Input Row Targets ---
        extraLabel1 = new JLabel("Course *:");
        extraLabel1.setFont(lblFont);
        extraTxt1 = new JTextField();
        formGrid.add(extraLabel1);
        formGrid.add(extraTxt1);

        extraLabel2 = new JLabel("Sports Team Name:");
        extraLabel2.setFont(lblFont);
        extraTxt2 = new JTextField();
        formGrid.add(extraLabel2);
        formGrid.add(extraTxt2);

        contentPanel.add(formGrid, BorderLayout.CENTER);

        // --- Dropdown Mode Change Trigger ---
        typeBox.addActionListener(e -> {
            if (typeBox.getSelectedItem().equals("Staff")) {
                extraLabel1.setText("Job Position *:");
                extraLabel2.setText("Department *:");
            } else {
                extraLabel1.setText("Course *:");
                extraLabel2.setText("Sports Team Name:");
            }
        });

        // --- Footer Buttons ---
        JPanel actionsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        actionsPanel.setOpaque(false);

        addBtn = new JButton("Add Member");
        clearBtn = new JButton("Clear Form");
        backBtn = new JButton("Back");

        JButton[] memberButtons = {addBtn, clearBtn, backBtn};
        Font memberBtnFont = new Font("Segoe UI", Font.BOLD, 13);
        for (JButton btn : memberButtons) {
            btn.setFont(memberBtnFont);
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

        // --- Form Processing ---
        addBtn.addActionListener(e -> processAddMember());
        clearBtn.addActionListener(e -> clearFields());
        backBtn.addActionListener(e -> {
            mainMenu.refreshStatisticsSummary();
            mainMenu.setVisible(true);
            this.dispose();
        });
    }

    private void clearFields() {
        fNameTxt.setText(""); lNameTxt.setText(""); addressTxt.setText("");
        phoneTxt.setText(""); dobTxt.setText("DD/MM/YYYY"); extraTxt1.setText(""); extraTxt2.setText("");
    }

    private void processAddMember() {
        try {
            String fn = fNameTxt.getText().trim();
            String ln = lNameTxt.getText().trim();
            String dob = dobTxt.getText().trim();
            String gender = (String) genderBox.getSelectedItem();
            String addr = addressTxt.getText().trim();
            String ph = phoneTxt.getText().trim();
            String ex1 = extraTxt1.getText().trim();
            String ex2 = extraTxt2.getText().trim();

            if (fn.isEmpty() || ln.isEmpty() || dob.isEmpty() || ex1.isEmpty()) {
                throw new IllegalArgumentException("Please populate all fields marked with an asterisk (*).");
            }

            int id = gym.getNextMemberId();
            if (typeBox.getSelectedItem().equals("Student")) {
                gym.addPolyStudent(id, dob, gender, ex1, ex2, fn, ln, addr, ph);
            } else {
                if (ex2.isEmpty()) throw new IllegalArgumentException("Department field is required for Staff.");
                gym.addPolyStaff(id, dob, gender, ex1, ex2, fn, ln, addr, ph);
            }

            JOptionPane.showMessageDialog(this, "Member added successfully! Assigned ID: " + id);
            backBtn.getActionListeners()[0].actionPerformed(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Validation Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}