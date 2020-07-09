package com.balance;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Qeydiyyat Səhifəsi");
        stage.getIcons().add(new Image("/com/balance/images/download.jpg"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/balance/login/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
