package org.owlmon.model.question;

import java.util.List;

public class SingleCorrectQuestion extends Question {
    public SingleCorrectQuestion(int questionSetId, int questionNo, String questionText, List<String> options, String correctOption, int marks) {
        super(questionSetId, questionNo, questionText, options, correctOption, marks);
    }

    @Override
    public String getType() {
        return "SingleCorrect";
    }

    @Override
    public boolean isCorrect(String answer) {
        return getCorrectOption().equals(answer);
    }
}
