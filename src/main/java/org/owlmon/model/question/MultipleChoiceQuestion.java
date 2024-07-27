package org.owlmon.model.question;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<String> correctOptions;

    public MultipleChoiceQuestion(int questionSetId, int questionNo, String questionText, List<String> options, List<String> correctOptions, int marks) {
        super(questionSetId, questionNo, questionText, options, null, marks);
        this.correctOptions = correctOptions;
    }

    @Override
    public String getType() {
        return "MultipleChoice";
    }

    @Override
    public boolean isCorrect(String answer) {
        return correctOptions.contains(answer);
    }
}
