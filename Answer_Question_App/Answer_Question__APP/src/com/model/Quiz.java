package com.model;

import java.util.List;

public class Quiz {

    private List<Question> questions;
    private int index=0;
    private int score;

    public Question getQuestion() {
        Question currentQ = questions.get(index);
        return currentQ;
    }

    public void nextQuestion(String selectedAnwer) {

        Question currentQ = getQuestion();
        if (currentQ.getAnswer().equalsIgnoreCase(selectedAnwer)) {
            score++;
        }
        index++;  /// suali dayis

    }

    public boolean isFinished() {
        if (index == questions.size()) {
            return true;
        }
        return false;
    }

    //  a[1,3,6,7,9]
    public Quiz(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Quiz{" + "questions=" + questions + ", index=" + index + ", score=" + score + '}';
    }

    
    
    
}
