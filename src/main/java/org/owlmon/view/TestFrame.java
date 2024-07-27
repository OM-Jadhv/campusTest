package org.owlmon.view;

import org.owlmon.controller.QuestionController;
import org.owlmon.model.question.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFrame extends JFrame {
    private JLabel timerLabel;
    private JLabel testNameLabel;
    private JPanel questionPanel;
    private JButton submitButton;
    private Timer timer;
    private int timeRemaining;
    private List<Question> questions;
    private QuestionController questionController;
    private Map<Integer, JComponent> answerComponents;

    public TestFrame(String testName, int durationInSeconds, QuestionController questionController) {
        this.questionController = questionController;
        this.questions = questionController.getQuestions();
        this.timeRemaining = durationInSeconds;
        this.answerComponents = new HashMap<>();

        setTitle("Test: " + testName);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add gaps between components

        // Create main panel to hold all components
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Create test name panel
        JPanel testNamePanel = new JPanel(new BorderLayout());
        testNamePanel.setBackground(new Color(70, 130, 180)); // Steel blue background
        testNameLabel = new JLabel(testName, SwingConstants.CENTER);
        testNameLabel.setForeground(Color.WHITE); // White text
        testNameLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Increase font size
        testNamePanel.add(testNameLabel, BorderLayout.CENTER);

        // Create timer panel
        JPanel timerPanel = new JPanel(new BorderLayout());
        timerPanel.setBackground(new Color(70, 130, 180)); // Steel blue background
        timerLabel = new JLabel("Time remaining: " + formatTime(timeRemaining), SwingConstants.CENTER);
        timerLabel.setForeground(Color.WHITE); // White text
        timerLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        timerPanel.add(timerLabel, BorderLayout.CENTER);

        // Combine test name and timer panels
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(testNamePanel, BorderLayout.NORTH);
        topPanel.add(timerPanel, BorderLayout.SOUTH);

        // Create question panel
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(questionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create submit button
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(0, 128, 0)); // Green background
        submitButton.setForeground(Color.WHITE); // White text
        submitButton.setFont(new Font("Arial", Font.BOLD, 18)); // Increase font size
        submitButton.addActionListener(e -> handleSubmit());

        // Create bottom panel for submit button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(submitButton);

        // Add components to main panel
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        displayQuestions();
        startTimer();

        pack(); // Adjust frame size to fit components
        setLocationRelativeTo(null); // Center the frame on screen
    }

    private void displayQuestions() {
        for (Question question : questions) {
            JPanel questionContainer = new JPanel();
            questionContainer.setLayout(new BoxLayout(questionContainer, BoxLayout.Y_AXIS));
            questionContainer.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder("Question " + question.getQuestionNo()),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            questionContainer.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel questionLabel = new JLabel("<html><body style='width: 500px;'>" + question.getQuestionText() + "</body></html>");
            questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            questionLabel.setForeground(new Color(25, 25, 112)); // Midnight blue text
            questionLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size

            questionContainer.add(questionLabel);
            questionContainer.add(Box.createVerticalStrut(10)); // Add vertical spacing

            JComponent answerComponent = null;

            switch (question.getType()) {
                case "SingleCorrect":
                    answerComponent = createRadioButtonPanel(question.getOptions());
                    break;
                case "MultipleChoice":
                    answerComponent = createCheckBoxPanel(question.getOptions());
                    break;
                case "Descriptive":
                    answerComponent = createDescriptivePanel();
                    break;
                case "Numerical":
                    answerComponent = createNumericalPanel();
                    break;
            }

            if (answerComponent != null) {
                answerComponent.setAlignmentX(Component.LEFT_ALIGNMENT);
                questionContainer.add(answerComponent);
            }

            answerComponents.put(question.getQuestionNo(), answerComponent);
            questionPanel.add(questionContainer);
            questionPanel.add(Box.createVerticalStrut(20)); // Add spacing between questions
        }

        questionPanel.revalidate();
        questionPanel.repaint();
    }

    private JPanel createRadioButtonPanel(List<String> options) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setFont(new Font("Arial", Font.PLAIN, 16));
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            buttonGroup.add(radioButton);
            panel.add(radioButton);
        }
        return panel;
    }

    private JPanel createCheckBoxPanel(List<String> options) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 16));
            checkBox.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(checkBox);
        }
        return panel;
    }

    private JPanel createDescriptivePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea descriptiveArea = new JTextArea(5, 30);
        descriptiveArea.setLineWrap(true);
        descriptiveArea.setWrapStyleWord(true);
        descriptiveArea.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(descriptiveArea);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createNumericalPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField numericalField = new JTextField(10);
        numericalField.setFont(new Font("Arial", Font.PLAIN, 16));
        panel.add(numericalField);
        return panel;
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                timerLabel.setText("Time remaining: " + formatTime(timeRemaining));

                if (timeRemaining <= 0) {
                    timer.stop();
                    handleSubmit();
                }
            }
        });
        timer.start();
    }

    private String formatTime(int seconds) {
        int hrs = seconds / 3600;
        int mins = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hrs, mins, secs);
    }

    private void handleSubmit() {
        int totalMarks = 0;

        for (Question question : questions) {
            JComponent component = answerComponents.get(question.getQuestionNo());
            if (component == null) continue;

            boolean isCorrect = false;
            String userAnswer = "";

            switch (question.getType()) {
                case "SingleCorrect":
                    for (Component comp : ((Container) component).getComponents()) {
                        if (comp instanceof JRadioButton) {
                            JRadioButton radioButton = (JRadioButton) comp;
                            if (radioButton.isSelected()) {
                                userAnswer = radioButton.getText();
                                isCorrect = question.isCorrect(userAnswer);
                                break;
                            }
                        }
                    }
                    break;

                case "MultipleChoice":
                    JPanel checkboxPanel = (JPanel) component;
                    StringBuilder selectedOptions = new StringBuilder();
                    for (Component c : checkboxPanel.getComponents()) {
                        JCheckBox checkBox = (JCheckBox) c;
                        if (checkBox.isSelected()) {
                            if (selectedOptions.length() > 0) selectedOptions.append(",");
                            selectedOptions.append(checkBox.getText());
                        }
                    }
                    userAnswer = selectedOptions.toString();
                    isCorrect = question.isCorrect(userAnswer);
                    break;

                case "Descriptive":
                    JTextArea descriptiveField = (JTextArea) ((JScrollPane) component.getComponent(0)).getViewport().getView();
                    userAnswer = descriptiveField.getText();
                    isCorrect = question.isCorrect(userAnswer);
                    break;

                case "Numerical":
                    JTextField numericalField = (JTextField) ((JPanel) component).getComponent(0);
                    userAnswer = numericalField.getText();
                    isCorrect = question.isCorrect(userAnswer);
                    break;
            }

            if (isCorrect) {
                totalMarks += question.getMarks();
            }
        }

        JOptionPane.showMessageDialog(this, "Test submitted! Total marks: " + totalMarks, "Information", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}