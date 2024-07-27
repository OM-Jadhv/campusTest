package org.owlmon.view;

import org.owlmon.model.user.User;
import org.owlmon.controller.MainController;
import org.owlmon.controller.TestDashboardController;
import org.owlmon.model.Test;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestDashboard extends JFrame {
    private User user;
    private MainController mainController;
    private TestDashboardController testDashboardController;

    public TestDashboard(User user, MainController mainController) {
        this.user = user;
        this.mainController = mainController;
        this.testDashboardController = new TestDashboardController();

        initializeUI();
    }

    private void initializeUI() {
        setTitle("Test Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(welcomeLabel, BorderLayout.NORTH);

        JPanel testPanel = createTestPanel();
        JScrollPane scrollPane = new JScrollPane(testPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JPanel createTestPanel() {
        JPanel testPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        List<Test> tests = testDashboardController.loadTestsFromDatabase();
        for (Test test : tests) {
            JPanel testItemPanel = createTestItemPanel(test);
            testPanel.add(testItemPanel, gbc);
        }

        return testPanel;
    }

    private JPanel createTestItemPanel(Test test) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(test.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(nameLabel, BorderLayout.NORTH);

        JLabel idLabel = new JLabel("Test ID: " + test.getId());
        JLabel timerLabel = new JLabel("Duration: " + formatDuration(test.getDurationInSeconds()));

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.add(idLabel);
        infoPanel.add(timerLabel);
        panel.add(infoPanel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Test");
        startButton.addActionListener(e -> mainController.openTestFrame(test.getId(), test.getName(), test.getDurationInSeconds()));
        panel.add(startButton, BorderLayout.EAST);

        return panel;
    }

    private String formatDuration(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }
}