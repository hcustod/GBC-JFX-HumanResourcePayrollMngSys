package org.live.humanresourcemangandpayrollsys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/live/humanresourcemangandpayrollsys/views/MainView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Human Resource MangandPayroll System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
