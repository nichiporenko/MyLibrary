package com.excelsiorious.library.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static final String PATH_ICON = "view/image/book.png";
    private static final String FXML_COUNTRIES = "/view/fxml/countries.fxml";
    private static final String FXML_GENRES = "/view/fxml/genres.fxml";
    private static final String FXML_LANGUAGES = "/view/fxml/languages.fxml";
    private static final String FXML_HOUSES = "/view/fxml/publishingHouses.fxml";
    private static final String FXML_AUTHORS = "/view/fxml/authors.fxml";
    private static final String FXML_BOOKS = "/view/fxml/books.fxml";
    private static final String TITLE_COUNTRIES = "Страны";
    private static final String TITLE_GENRES = "Жанры";
    private static final String TITLE_LANGUAGES = "Языки";
    private static final String TITLE_HOUSES = "Издательства";
    private static final String TITLE_AUTHORS = "Авторы";
    private static final String TITLE_BOOKS = "Книги";
    public Button buttonGenres;
    public Button buttonCountries;
    public Button buttonLanguages;
    public Button buttonHouses;
    public Button buttonAuthors;
    public Button buttonExit;
    public Button buttonBooks;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickExit(MouseEvent mouseEvent) {
        Launcher.getPrimaryStage().close();
    }

    public void onClickCountries(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_COUNTRIES));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setTitle(TITLE_COUNTRIES);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Launcher.getPrimaryStage());
            stage.getIcons().add(new Image(PATH_ICON));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }

    public void onClickGenres(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_GENRES));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(TITLE_GENRES);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initOwner(Launcher.getPrimaryStage());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(PATH_ICON));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }

    public void onClickLanguages(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_LANGUAGES));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(TITLE_LANGUAGES);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.initOwner(Launcher.getPrimaryStage());
            stage.getIcons().add(new Image(PATH_ICON));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }

    public void onClickHouses(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_HOUSES));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(TITLE_HOUSES);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.getIcons().add(new Image(PATH_ICON));
            stage.initOwner(Launcher.getPrimaryStage());
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }

    public void onClickAuthors(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_AUTHORS));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(TITLE_AUTHORS);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initOwner(Launcher.getPrimaryStage());
            stage.getIcons().add(new Image(PATH_ICON));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }

    public void onClickBooks(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_BOOKS));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(TITLE_BOOKS);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Launcher.getPrimaryStage());
            stage.getIcons().add(new Image(PATH_ICON));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            LOGGER.error("Something went wrong!", exception);
        }
    }
}
