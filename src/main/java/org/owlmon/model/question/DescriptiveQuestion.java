package org.owlmon.model.question;

public class DescriptiveQuestion extends Question {
    public DescriptiveQuestion(int questionSetId, int questionNo, String questionText, String correctOption, int marks) {
        super(questionSetId, questionNo, questionText, null, correctOption, marks);
    }

    @Override
    public String getType() {
        return "Descriptive";
    }

    @Override
    public boolean isCorrect(String answer) {
        return getCorrectOption().equals(answer);
    }
}
