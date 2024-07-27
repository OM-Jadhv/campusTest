package org.owlmon.controller;

import org.owlmon.model.question.Question;

import java.util.List;

public class CalculateMarksController {
    private QuestionController questionController;

    public CalculateMarksController(QuestionController questionController) {
        this.questionController = questionController;
    }

    public int calculateTotalMarks(List<String> userAnswers) {
        List<Question> questions = questionController.getQuestions();
        int totalMarks = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = userAnswers.get(i);

            if (question.isCorrect(userAnswer)) {
                totalMarks += question.getMarks();
            }
        }

        return totalMarks;
    }
}
