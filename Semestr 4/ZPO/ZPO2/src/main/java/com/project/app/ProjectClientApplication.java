package com.project.app;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ProjectClientApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(ProjectClientApplication.class);

    private Parent root;
    private FXMLLoader fxmlLoader;

    public static void main(String[] args) {
        launch(ProjectClientApplication.class, args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        fxmlLoader = new FXMLLoader(ProjectClientApplication.class.getResource("fxml/ProjectFrame.fxml"));
        root = fxmlLoader.load();
        primaryStage.setTitle("Projekty");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

}
