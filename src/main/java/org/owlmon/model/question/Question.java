package org.owlmon.model.question;

import java.util.List;

public abstract class Question {
    private int questionSetId;
    private int questionNo;
    private String questionText;
    private List<String> options;
    private String correctOption;
    private int marks;

    public Question(int questionSetId, int questionNo, String questionText, List<String> options, String correctOption, int marks) {
        this.questionSetId = questionSetId;
        this.questionNo = questionNo;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.marks = marks;
    }

    public int getQuestionSetId() {
        return questionSetId;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public int getMarks() {
        return marks;
    }

    public abstract String getType();

    public abstract boolean isCorrect(String answer);
}
