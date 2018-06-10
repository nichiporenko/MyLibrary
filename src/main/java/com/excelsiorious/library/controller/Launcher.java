package com.excelsiorious.library.controller;

import com.excelsiorious.library.configuration.JpaConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

public class Launcher extends Application {
    private static AnnotationConfigApplicationContext context;
    private static Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        context = new AnnotationConfigApplicationContext(JpaConfig.class);
        setPrimaryStage(stage);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launcher.class.getResource("/view/fxml/main.fxml"));
        Parent root = loader.load();

        stage.setTitle("Библиотека");
        stage.setScene(new Scene(root));
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.getIcons().add(new Image("view/image/book.png"));
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Launcher.pStage = pStage;
    }

    public static AbstractApplicationContext getContext() {
        return context;
    }
}
