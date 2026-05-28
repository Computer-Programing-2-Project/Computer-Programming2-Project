package polyfit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Interface coordinating Personal Trainer assignment connections.
 * @author huzaifasuhail
 */
public class AssignTrainerForm extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    private GymSystem gym;
    private MainMenu mainMenu;

    private JTextField memberIdTxt, trainerIdTxt;
    private JLabel relationshipStatusLbl;
    private JButton checkStatusBtn, assignBtn, removeBtn, backBtn;

    public AssignTrainerForm(GymSystem gym, MainMenu mainMenu) {
        this.gym = gym;
        this.mainMenu = mainMenu;
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("PolyFit System - Coach Assignments Panel");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(460, 420);

        JPanel panel = new JPanel(new BorderLayout(10, 15));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        panel.setBackground(new Color(245, 247, 250));

        JLabel header = new JLabel("MANAGE PERSONAL TRAINER ASSIGNMENTS", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setForeground(new Color(63, 81, 181));
        panel.add(header, BorderLayout.NORTH);

        JPanel centerGrid = new JPanel(new GridLayout(4, 1, 10, 12));
        centerGrid.setOpaque(false);

        JPanel row1 = new JPanel(new BorderLayout(10, 0)); row1.setOpaque(false);
        row1.add(new JLabel("Member Target ID:  "), BorderLayout.WEST);
        memberIdTxt = new JTextField(); row1.add(memberIdTxt, BorderLayout.CENTER);
        checkStatusBtn = new JButton("Check Current Assignment");
        checkStatusBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        checkStatusBtn.setFocusPainted(false);

        // Fix for Check Status Button
        checkStatusBtn.setBorderPainted(false);
        checkStatusBtn.setOpaque(true);
        checkStatusBtn.setContentAreaFilled(true);
        checkStatusBtn.setBackground(new Color(63, 81, 181));
        checkStatusBtn.setForeground(Color.WHITE);
        row1.add(checkStatusBtn, BorderLayout.EAST);

        JPanel row2 = new JPanel(new BorderLayout(10, 0)); row2.setOpaque(false);
        row2.add(new JLabel("Personal Trainer Staff ID:"), BorderLayout.WEST);
        trainerIdTxt = new JTextField(); row2.add(trainerIdTxt, BorderLayout.CENTER);

        JPanel row3 = new JPanel(new BorderLayout()); row3.setOpaque(false);
        relationshipStatusLbl = new JLabel("Enter a Member ID above to check active coaching status.", SwingConstants.CENTER);
        relationshipStatusLbl.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        relationshipStatusLbl.setForeground(Color.DARK_GRAY);
        row3.add(relationshipStatusLbl);

        centerGrid.add(row1);
        centerGrid.add(row2);
        centerGrid.add(row3);
        panel.add(centerGrid, BorderLayout.CENTER);

        // --- Action Operations Buttons Layout ---
        JPanel buttonRow = new JPanel(new GridLayout(1, 3, 10, 0));
        assignBtn = new JButton("Link Assignment");
        removeBtn = new JButton("Sever Connection");
        backBtn = new JButton("Back");

        JButton[] assignmentRow = {assignBtn, removeBtn, backBtn};
        for (JButton button : assignmentRow) {
            button.setFont(new Font("Segoe UI", Font.BOLD, 13));
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(0, 40));
            
            // THE ULTIMATE CROSS-PLATFORM COLOR FIX:
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.setContentAreaFilled(true);
        }

        assignBtn.setBackground(new Color(46, 125, 50)); assignBtn.setForeground(Color.WHITE);
        removeBtn.setBackground(new Color(230, 81, 0)); removeBtn.setForeground(Color.WHITE);
        backBtn.setBackground(new Color(117, 117, 117)); backBtn.setForeground(Color.WHITE);

        buttonRow.add(assignBtn); buttonRow.add(removeBtn); buttonRow.add(backBtn);
        panel.add(buttonRow, BorderLayout.SOUTH);

        add(panel);

        // --- Wire Event Handlers ---
        checkStatusBtn.addActionListener(e -> checkStatus());
        assignBtn.addActionListener(e -> processLink(true));
        removeBtn.addActionListener(e -> processLink(false));
        backBtn.addActionListener(e -> {
            mainMenu.refreshStatisticsSummary();
            mainMenu.setVisible(true);
            this.dispose();
        });
    }

    private void checkStatus() {
        try {
            int mId = Integer.parseInt(memberIdTxt.getText().trim());
            Member m = gym.findMemberById(mId);
            if (m == null) {
                relationshipStatusLbl.setText("No member matches that ID.");
                return;
            }
            if (m.getTrainer() == null) {
                relationshipStatusLbl.setText("Member: " + m.getFirstName() + " " + m.getLastName() + " -> [No Trainer Assigned]");
            } else {
                PersonalTrainer pt = m.getTrainer();
                relationshipStatusLbl.setText("Member: " + m.getFirstName() + " -> Trainer: " + pt.getFirstName() + " " + pt.getLastName() + " (Staff ID: " + pt.getStaffId() + ")");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please insert a valid numeric target Member ID.");
        }
    }

    private void processLink(boolean linkAction) {
        try {
            int mId = Integer.parseInt(memberIdTxt.getText().trim());
            if (!linkAction) {
                gym.removeTrainer(mId);
                JOptionPane.showMessageDialog(this, "Coaching assignment severed cleanly.");
                checkStatus();
                return;
            }

            int tId = Integer.parseInt(trainerIdTxt.getText().trim());
            gym.assignTrainer(mId, tId);
            JOptionPane.showMessageDialog(this, "Trainer assignment linked successfully!");
            checkStatus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Assignment Error Context", JOptionPane.ERROR_MESSAGE);
        }
    }
}