package io.github.benjohnde.ankeader;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MinimalisticUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File retValue = fileChooser.showOpenDialog(primaryStage);
        Platform.exit();
    }
}
