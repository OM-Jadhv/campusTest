package org.owlmon.model.question;

public class NumericalQuestion extends Question {
    public NumericalQuestion(int questionSetId, int questionNo, String questionText, String correctOption, int marks) {
        super(questionSetId, questionNo, questionText, null, correctOption, marks);
    }

    @Override
    public String getType() {
        return "Numerical";
    }

    @Override
    public boolean isCorrect(String answer) {
        return getCorrectOption().equals(answer);
    }
}
