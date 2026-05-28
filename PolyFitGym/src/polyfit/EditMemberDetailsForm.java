package polyfit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Form for updating or removing existing Gym Members.
 * @author huzaifasuhail
 */
public class EditMemberDetailsForm extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private GymSystem gym;
    private MainMenu mainMenu;
    private Member activeMember = null;

    private JTextField searchIdTxt, fNameTxt, lNameTxt, dobTxt, addressTxt, phoneTxt, ex1Txt, ex2Txt;
    private JLabel ex1Label, ex2Label, classificationLbl;
    private JButton searchBtn, saveBtn, deleteBtn, backBtn;

    public EditMemberDetailsForm(GymSystem gym, MainMenu mainMenu) {
        this.gym = gym;
        this.mainMenu = mainMenu;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit System - Edit Member Profile");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(500, 580);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 15));
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        mainPanel.setBackground(new Color(245, 247, 250));

        // --- Search Bar Header ---
        JPanel searchBar = new JPanel(new BorderLayout(10, 0));
        searchBar.setBackground(Color.WHITE);
        searchBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1), new EmptyBorder(8, 10, 8, 10)
        ));

        searchIdTxt = new JTextField();
        searchBtn = new JButton("Search ID");
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        searchBtn.setFocusPainted(false);

        // Fix for Search Button near the top
        searchBtn.setBorderPainted(false);
        searchBtn.setOpaque(true);
        searchBtn.setContentAreaFilled(true);
        searchBtn.setBackground(new Color(63, 81, 181));
        searchBtn.setForeground(Color.WHITE);
        searchBar.add(new JLabel("Member Track ID: "), BorderLayout.WEST);
        searchBar.add(searchIdTxt, BorderLayout.CENTER);
        searchBar.add(searchBtn, BorderLayout.EAST);
        mainPanel.add(searchBar, BorderLayout.NORTH);

        // --- Edit Form Inputs ---
        JPanel formGrid = new JPanel(new GridLayout(8, 2, 10, 12));
        formGrid.setOpaque(false);
        Font lblFont = new Font("Segoe UI", Font.BOLD, 13);

        JLabel accountCategoryLabel = new JLabel("Account Category:");
        accountCategoryLabel.setFont(lblFont);
        formGrid.add(accountCategoryLabel);
        classificationLbl = new JLabel("[No Member Loaded]");
        classificationLbl.setFont(new Font("Segoe UI", Font.ITALIC | Font.BOLD, 13));
        formGrid.add(classificationLbl);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(lblFont);
        formGrid.add(firstNameLabel);
        fNameTxt = new JTextField();
        formGrid.add(fNameTxt);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(lblFont);
        formGrid.add(lastNameLabel);
        lNameTxt = new JTextField();
        formGrid.add(lNameTxt);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(lblFont);
        formGrid.add(dobLabel);
        dobTxt = new JTextField();
        formGrid.add(dobTxt);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(lblFont);
        formGrid.add(addressLabel);
        addressTxt = new JTextField();
        formGrid.add(addressTxt);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setFont(lblFont);
        formGrid.add(phoneLabel);
        phoneTxt = new JTextField();
        formGrid.add(phoneTxt);

        ex1Label = new JLabel("Course / Position:");
        ex1Label.setFont(lblFont);
        ex1Txt = new JTextField();
        formGrid.add(ex1Label);
        formGrid.add(ex1Txt);

        ex2Label = new JLabel("Team / Department:");
        ex2Label.setFont(lblFont);
        ex2Txt = new JTextField();
        formGrid.add(ex2Label);
        formGrid.add(ex2Txt);

        mainPanel.add(formGrid, BorderLayout.CENTER);

        // --- Action Row Footer Buttons ---
        JPanel bottomBar = new JPanel(new GridLayout(1, 3, 10, 0));
        saveBtn = new JButton("Save Changes"); deleteBtn = new JButton("Delete Member"); backBtn = new JButton("Back");

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

        saveBtn.setBackground(new Color(46, 125, 50)); saveBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(198, 40, 40)); deleteBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(117, 117, 117)); backBtn.setForeground(Color.WHITE);

        bottomBar.add(saveBtn); bottomBar.add(deleteBtn); bottomBar.add(backBtn);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);

        toggleFields(false);

        add(mainPanel);

        // --- Listeners Trigger Routines ---
        searchBtn.addActionListener(e -> performSearch());
        saveBtn.addActionListener(e -> performUpdates());
        deleteBtn.addActionListener(e -> performDelete());
        backBtn.addActionListener(e -> {
            mainMenu.refreshStatisticsSummary();
            mainMenu.setVisible(true);
            this.dispose();
        });
    }

    private void toggleFields(boolean state) {
        fNameTxt.setEnabled(state); lNameTxt.setEnabled(state); dobTxt.setEnabled(state);
        addressTxt.setEnabled(state); phoneTxt.setEnabled(state); ex1Txt.setEnabled(state);
        ex2Txt.setEnabled(state); saveBtn.setEnabled(state); deleteBtn.setEnabled(state);
    }

    private void performSearch() {
        try {
            int id = Integer.parseInt(searchIdTxt.getText().trim());
            Member m = gym.findMemberById(id);

            if (m == null) {
                toggleFields(false);
                classificationLbl.setText("[No Member Loaded]");
                JOptionPane.showMessageDialog(this, "Member reference record not located.");
                return;
            }

            activeMember = m;
            fNameTxt.setText(m.getFirstName());
            lNameTxt.setText(m.getLastName());
            dobTxt.setText(m.getDateOfBirth());
            addressTxt.setText(m.getAddress());
            phoneTxt.setText(m.getPhone());

            if (m instanceof PolyStudent) {
                classificationLbl.setText("POLYTENCHNIC STUDENT");
                ex1Label.setText("Modify Course:"); ex2Label.setText("Modify Sports Team:");
                ex1Txt.setText(((PolyStudent) m).getCourse());
                ex2Txt.setText(((PolyStudent) m).getTeam());
            } else {
                classificationLbl.setText("POLYTECHNIC STAFF");
                ex1Label.setText("Modify Job Position:"); ex2Label.setText("Modify Department:");
                ex1Txt.setText(((PolyStaff) m).getPosition());
                ex2Txt.setText(((PolyStaff) m).getDepartment());
            }
            toggleFields(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please insert a valid numeric formatting target ID.");
        }
    }

    private void performUpdates() {
        if (activeMember == null) return;
        try {
            activeMember.setFirstName(fNameTxt.getText().trim());
            activeMember.setLastName(lNameTxt.getText().trim());
            activeMember.setDateOfBirth(dobTxt.getText().trim());
            activeMember.setAddress(addressTxt.getText().trim());
            activeMember.setPhone(phoneTxt.getText().trim());

            if (activeMember instanceof PolyStudent) {
                ((PolyStudent) activeMember).setCourse(ex1Txt.getText().trim());
                ((PolyStudent) activeMember).setTeam(ex2Txt.getText().trim());
            } else {
                ((PolyStaff) activeMember).setPosition(ex1Txt.getText().trim());
                ((PolyStaff) activeMember).setDepartment(ex2Txt.getText().trim());
            }
            JOptionPane.showMessageDialog(this, "Member configuration modifications saved successfully.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error processing changes: " + ex.getMessage());
        }
    }

    private void performDelete() {
        if (activeMember == null) return;
        int choice = JOptionPane.showConfirmDialog(this, "Permanently delete this member record?", "Verify Action", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            gym.deleteMember(activeMember.getMemberId());
            JOptionPane.showMessageDialog(this, "Member record removed cleanly.");
            backBtn.getActionListeners()[0].actionPerformed(null);
        }
    }
}