package com.model;

import java.util.List;

public class Question {

    private int id;
    private String description;
    private String answer;
    private String help;
    private List<String> choices;

    public Question() {
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    
  
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getChooses() {
        return choices;
    }

    public void setChooses(List<String> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", description=" + description + ", answer=" + answer + ", help=" + help + ", choices=" + choices + '}';
    }



}
