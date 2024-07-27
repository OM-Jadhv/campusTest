package org.owlmon.controller;

import org.owlmon.model.question.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionController {
    private static final String DB_URL = "jdbc:sqlite:quiz.db";
    private List<Question> questions;

    public QuestionController() {
        this.questions = new ArrayList<>();
    }

    public void loadQuestions(int testId) {
        String sql = "SELECT * FROM question_set WHERE question_set_id = " + testId;

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int questionSetId = rs.getInt("question_set_id");
                int questionNo = rs.getInt("question_no");
                String questionText = rs.getString("question");
                String optionsStr = rs.getString("options");
                String correctOptionStr = rs.getString("correct_option");
                String questionType = rs.getString("question_type");
                int marks = rs.getInt("marks");

                List<String> options = parseOptions(optionsStr);
                List<String> correctOptions = parseOptions(correctOptionStr);

                Question question;
                switch (questionType) {
                    case "SingleCorrect":
                        question = new SingleCorrectQuestion(questionSetId, questionNo, questionText, options, correctOptions.get(0), marks);
                        break;
                    case "MultipleChoice":
                        question = new MultipleChoiceQuestion(questionSetId, questionNo, questionText, options, correctOptions, marks);
                        break;
                    case "Numerical":
                        question = new NumericalQuestion(questionSetId, questionNo, questionText, correctOptionStr, marks);
                        break;
                    case "Descriptive":
                        question = new DescriptiveQuestion(questionSetId, questionNo, questionText, correctOptionStr, marks);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown question type: " + questionType);
                }

                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<String> parseOptions(String optionsStr) {
        List<String> options = new ArrayList<>();
        if (optionsStr != null && !optionsStr.isEmpty()) {
            String[] splitOptions = optionsStr.split("},\\{");
            for (String option : splitOptions) {
                options.add(option.replace("{", "").replace("}", "").trim());
            }
        }
        return options;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int questionNo) {
        return questions.stream()
                .filter(question -> question.getQuestionNo() == questionNo)
                .findFirst()
                .orElse(null);
    }
}
