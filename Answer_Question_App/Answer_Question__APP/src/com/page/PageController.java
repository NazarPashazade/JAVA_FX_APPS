package com.page;

import com.DAO.DAOImpl;
import com.model.Question;
import com.model.Quiz;
import com.util.Notification;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PageController implements Initializable {

    DAOImpl dao = new DAOImpl();
    Quiz quiz;

    @FXML
    private Label descriptionLbl;
    @FXML
    private Button asnwer1Btn;
    @FXML
    private Button asnwer2Btn;
    @FXML
    private Button asnwer4Btn;
    @FXML
    private Button asnwer3Btn;
    @FXML
    private Button skipBtn;
    @FXML
    private Label questionCountLbl;
    @FXML
    private Label scoreLbl;
    @FXML
    private PieChart mainPieChart;
    @FXML
    private Button helpMeBtn;
    @FXML
    private Label questionAboutLbl;
    @FXML
    private Button helpAboutBtn;
    @FXML
    private Button nextBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        loadQuestion();
        fillInPiechart();
    }

    String selectedAnswer = "";

    @FXML
    private void asnwer1BtnonAction(ActionEvent event) {

        if (selectedAnswer.equalsIgnoreCase("")) {
            selectedAnswer = asnwer1Btn.getText();
            if (selectedAnswer.equalsIgnoreCase(quiz.getQuestion().getAnswer())) {
                asnwer1Btn.setStyle("-fx-background-color: green");
            } else {
                asnwer1Btn.setStyle("-fx-background-color: red");
            }
        }

    }

    @FXML
    private void asnwer2BtnonAction(ActionEvent event) {

        if (selectedAnswer.equalsIgnoreCase("")) {
            selectedAnswer = asnwer2Btn.getText();
            if (selectedAnswer.equalsIgnoreCase(quiz.getQuestion().getAnswer())) {
                asnwer2Btn.setStyle("-fx-background-color: green");
            } else {
                asnwer2Btn.setStyle("-fx-background-color: red");
            }
        }

    }

    @FXML
    private void asnwer4BtnonAction(ActionEvent event) {

        if (selectedAnswer.equalsIgnoreCase("")) {
            selectedAnswer = asnwer4Btn.getText();
            if (selectedAnswer.equalsIgnoreCase(quiz.getQuestion().getAnswer())) {
                asnwer4Btn.setStyle("-fx-background-color: green");
            } else {
                asnwer4Btn.setStyle("-fx-background-color: red");
            }
        }

    }

    @FXML
    private void asnwer3BtnonAction(ActionEvent event) {

        if (selectedAnswer.equalsIgnoreCase("")) {
            selectedAnswer = asnwer3Btn.getText();
            if (selectedAnswer.equalsIgnoreCase(quiz.getQuestion().getAnswer())) {
                asnwer3Btn.setStyle("-fx-background-color: green");
            } else {
                asnwer3Btn.setStyle("-fx-background-color: red");
            }
        }

    }

    @FXML
    private void skipBtnOnAction(ActionEvent event) {
        quiz.setIndex(quiz.getIndex() + 1);
        loadQuestion();
    }

    private void loadQuestion() {

        nextBtn.setText("Next");
        selectedAnswer = "";

        asnwer1Btn.setStyle("-fx-background-color: yellow");
        asnwer2Btn.setStyle("-fx-background-color: yellow");
        asnwer3Btn.setStyle("-fx-background-color: yellow");
        asnwer4Btn.setStyle("-fx-background-color: yellow");

        questionAboutLbl.setText("");
        if (helpMeCount == 2) {
            helpMeBtn.setVisible(false);
        } else {
            helpMeBtn.setVisible(true);
        }

        asnwer1Btn.setVisible(true);
        asnwer2Btn.setVisible(true);
        asnwer3Btn.setVisible(true);
        asnwer4Btn.setVisible(true);

        Question currentQ = quiz.getQuestion();

        descriptionLbl.setText(currentQ.getDescription());
        asnwer1Btn.setText(currentQ.getChoices().get(0));
        asnwer2Btn.setText(currentQ.getChoices().get(1));
        asnwer3Btn.setText(currentQ.getChoices().get(2));
        asnwer4Btn.setText(currentQ.getChoices().get(3));

        scoreLbl.setText("SCORE : " + quiz.getScore());
        questionCountLbl.setText((quiz.getIndex() + 1) + "/" + quiz.getQuestions().size());

    }

    private void loadData() {
        quiz = new Quiz(dao.getAllQuestions());

        for (Question question : quiz.getQuestions()) {
            question.setChoices(dao.getChoicesBy_QuestionId(question.getId()));
        }
    }

    public void clearAllFileds() {
        quiz.setIndex(0);
        quiz.setScore(0);
        loadQuestion();
        helpMeCount = 0;
    }

    private void fillInPiechart() {  //  5=sual   score=2

        double correctAnswerCount = quiz.getScore();
        double wrongAnswerCount = quiz.getQuestions().size() - quiz.getScore();

        ObservableList<PieChart.Data> datas = FXCollections.observableArrayList();

        datas.add(new PieChart.Data("Correct Answers ", correctAnswerCount));
        datas.add(new PieChart.Data("Wrong Answers ", wrongAnswerCount));

        mainPieChart.setData(datas);

    }

    int helpMeCount = 0;

    @FXML
    private void helpMeBtnOnAction(ActionEvent event) {
        Question currentQ = quiz.getQuestion();

        helpMeCount++;

        helpMeBtn.setVisible(false);

        List<Integer> wrongAnswersIndex = new ArrayList<>();  // 0 2 3 

        int correntAnswerIndex = 0;

        for (int i = 0; i < 4; i++) { // 0 1 2 3
            if (!currentQ.getAnswer().equalsIgnoreCase(currentQ.getChoices().get(i))) {
                wrongAnswersIndex.add(i);
            } else {
                correntAnswerIndex = i;
            }
        }

        for (int i = 0; i < 2; i++) { // 0 1
            switch (wrongAnswersIndex.get(i)) {
                case 0:
                    asnwer1Btn.setVisible(false);
                    break;
                case 1:
                    asnwer2Btn.setVisible(false);
                    break;
                case 2:
                    asnwer3Btn.setVisible(false);
                    break;
                case 3:
                    asnwer4Btn.setVisible(false);
                    break;
            }
        }

    }

    @FXML
    private void helpAboutBtnBtnOnAction(ActionEvent event) {
        Question currentQ = quiz.getQuestion();

        helpAboutBtn.setVisible(false);

        questionAboutLbl.setText(currentQ.getHelp());

    }

    @FXML
    private void nextBtnOnAction(ActionEvent event) {

        if (nextBtn.getText().equalsIgnoreCase("Next")) {

            if (!selectedAnswer.equalsIgnoreCase("")) {
                quiz.nextQuestion(selectedAnswer);
                if (quiz.isFinished()) {
                    scoreLbl.setText("SCORE : " + quiz.getScore());
                    Notification.callNotification("All Score = " + quiz.getScore(), "Test Completed");
                    nextBtn.setText("Try Again");
                } else {
                    loadQuestion();
                }
                fillInPiechart();
            }

        } else {
            clearAllFileds();
            helpAboutBtn.setVisible(true);
            helpMeBtn.setVisible(true);
        }
    }

}
