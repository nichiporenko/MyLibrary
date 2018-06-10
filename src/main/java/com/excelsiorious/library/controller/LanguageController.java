package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.Language;
import com.excelsiorious.library.service.LanguageService;
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

public class LanguageController implements Initializable {
    public TableView<Language> languageTable;
    public TableColumn<Language, String> idColumn;
    public TableColumn<Language, String> nameColumn;
    public TextField txtName;

    private LanguageService languageService;
    private List<Language> languages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        languageService = Launcher.getContext().getBean(LanguageService.class);

        showData();
    }

    public void addLanguage(ActionEvent actionEvent) {
        String text = txtName.getText();
        if (!text.isEmpty()) {
            int nextId = languages.size() + 1;
            Language language = new Language();
            language.setId(nextId);
            language.setName(text);
            languageService.create(language);
            showData();
        }
    }

    private void showData() {
        languages = languageService.findAll();
        ObservableList<Language> observableList = FXCollections.observableArrayList(languages);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        languageTable.setItems(observableList);
    }
}
