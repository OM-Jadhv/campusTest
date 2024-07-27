package org.owlmon.view;

import org.owlmon.model.user.User;
import org.owlmon.controller.MainController;
import org.owlmon.controller.TeacherDashboardController;
import org.owlmon.model.Test;
import org.owlmon.model.question.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TeacherDashboard extends JFrame {
    private User user;
    private MainController mainController;
    private TeacherDashboardController teacherDashboardController;
    private JPanel testListPanel;

    public TeacherDashboard(User user, MainController mainController) {
        this.user = user;
        this.mainController = mainController;
        this.teacherDashboardController = new TeacherDashboardController();

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Teacher Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addTestButton = new JButton("Add New Test");
        addTestButton.addActionListener(e -> showAddTestDialog());
        mainPanel.add(addTestButton, BorderLayout.NORTH);

        testListPanel = new JPanel(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane(testListPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        refreshTestList();
        setLocationRelativeTo(null);
    }

    private void showAddTestDialog() {
        JTextField nameField = new JTextField(20);
        JTextField durationField = new JTextField(10);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Test Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Duration (HH:MM:SS):"));
        panel.add(durationField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add New Test",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String duration = durationField.getText();
            Test newTest = teacherDashboardController.addTest(name, duration);
            if (newTest != null) {
                refreshTestList();
                showAddQuestionsDialog(newTest);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add test", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAddQuestionsDialog(Test test) {
        AddQuestionsDialog addQuestionsDialog = new AddQuestionsDialog(this, test, teacherDashboardController);
        addQuestionsDialog.setVisible(true);
    }

    private void refreshTestList() {
        testListPanel.removeAll();
        List<Test> tests = teacherDashboardController.loadTestsFromDatabase();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        for (Test test : tests) {
            JPanel testItemPanel = createTestItemPanel(test);
            testListPanel.add(testItemPanel, gbc);
        }

        testListPanel.revalidate();
        testListPanel.repaint();
    }

    private JPanel createTestItemPanel(Test test) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(test.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(nameLabel, BorderLayout.WEST);

        JLabel timerLabel = new JLabel("Duration: " + formatDuration(test.getDurationInSeconds()));
        panel.add(timerLabel, BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> removeTest(test));
        panel.add(removeButton, BorderLayout.EAST);

        return panel;
    }

    private void removeTest(Test test) {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to remove this test?",
                "Confirm Removal",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            teacherDashboardController.removeTest(test.getId());
            refreshTestList();
        }
    }

    private String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}