package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.Genre;
import com.excelsiorious.library.service.GenreService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GenreController implements Initializable {
    private static final String PROPERTY_ID = "id";
    private static final String PROPERTY_NAME = "name";
    public TableView<Genre> genreTable;
    public TableColumn<Genre, String> idColumn;
    public TableColumn<Genre, String> nameColumn;
    public TextField txtName;

    private GenreService genreService;
    private List<Genre> genres;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genreService = Launcher.getContext().getBean(GenreService.class);

        showData();
    }

    public void addGenre(ActionEvent actionEvent) {
        String text = txtName.getText();
        if (!text.isEmpty()) {
            int nextId = genres.size() + 1;
            Genre genre = new Genre();
            genre.setId(nextId);
            genre.setName(text);
            genreService.create(genre);
            showData();
        }
    }

    private void showData() {
        genres = genreService.findAll();
        ObservableList<Genre> observableList = FXCollections.observableArrayList(genres);

        idColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_NAME));
        genreTable.setItems(observableList);
    }
}
