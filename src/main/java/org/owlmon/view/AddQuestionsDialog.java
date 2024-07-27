package org.owlmon.view;

import org.owlmon.controller.TeacherDashboardController;
import org.owlmon.model.Test;
import org.owlmon.model.question.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AddQuestionsDialog extends JDialog {
    private Test test;
    private TeacherDashboardController controller;
    private JTextField questionTextField;
    private JTextField marksField;
    private JComboBox<String> questionTypeComboBox;
    private JTextArea optionsTextArea;
    private JTextField correctAnswerField;

    public AddQuestionsDialog(JFrame parent, Test test, TeacherDashboardController controller) {
        super(parent, "Add Questions to " + test.getName(), true);
        this.test = test;
        this.controller = controller;

        initializeUI();
    }

    private void initializeUI() {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        questionTextField = new JTextField(20);
        marksField = new JTextField(5);
        questionTypeComboBox = new JComboBox<>(new String[]{"SingleCorrect", "MultipleChoice", "Descriptive", "Numerical"});
        optionsTextArea = new JTextArea(5, 20);
        correctAnswerField = new JTextField(20);

        panel.add(new JLabel("Question:"), gbc);
        panel.add(questionTextField, gbc);
        panel.add(new JLabel("Marks:"), gbc);
        panel.add(marksField, gbc);
        panel.add(new JLabel("Question Type:"), gbc);
        panel.add(questionTypeComboBox, gbc);
        panel.add(new JLabel("Options (one per line, for SingleCorrect and MultipleChoice):"), gbc);
        panel.add(new JScrollPane(optionsTextArea), gbc);
        panel.add(new JLabel("Correct Answer:"), gbc);
        panel.add(correctAnswerField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add Question");
        JButton doneButton = new JButton("Done");

        addButton.addActionListener(e -> addQuestion());
        doneButton.addActionListener(e -> dispose());

        panel.add(addButton);
        panel.add(doneButton);

        return panel;
    }

    private void addQuestion() {
        String questionText = questionTextField.getText();
        int marks = Integer.parseInt(marksField.getText());
        String questionType = (String) questionTypeComboBox.getSelectedItem();
        List<String> options = new ArrayList<>();
        for (String option : optionsTextArea.getText().split("\n")) {
            options.add(option.trim());
        }
        String correctAnswer = correctAnswerField.getText();

        Question question;
        switch (questionType) {
            case "SingleCorrect":
                question = new SingleCorrectQuestion(test.getId(), getNextQuestionNo(), questionText, options, correctAnswer, marks);
                break;
            case "MultipleChoice":
                question = new MultipleChoiceQuestion(test.getId(), getNextQuestionNo(), questionText, options, List.of(correctAnswer.split(",")), marks);
                break;
            case "Descriptive":
                question = new DescriptiveQuestion(test.getId(), getNextQuestionNo(), questionText, correctAnswer, marks);
                break;
            case "Numerical":
                question = new NumericalQuestion(test.getId(), getNextQuestionNo(), questionText, correctAnswer, marks);
                break;
            default:
                throw new IllegalArgumentException("Unknown question type: " + questionType);
        }

        controller.addQuestion(question);
        clearInputFields();
        JOptionPane.showMessageDialog(this, "Question added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private int getNextQuestionNo() {
        // In a real application, you'd get this from the database
        // For now, we'll use a placeholder
        return 1;
    }

    private void clearInputFields() {
        questionTextField.setText("");
        marksField.setText("");
        questionTypeComboBox.setSelectedIndex(0);
        optionsTextArea.setText("");
        correctAnswerField.setText("");
    }
}