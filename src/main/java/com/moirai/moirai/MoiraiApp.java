package com.moirai.moirai;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MoiraiApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader yukleyici = new FXMLLoader(MoiraiApp.class.getResource("ana-pencere.fxml"));
        Scene sahne = new Scene(yukleyici.load());
        stage.setTitle("Moirai OBS");
        stage.setScene(sahne);
        stage.show();
    }
}