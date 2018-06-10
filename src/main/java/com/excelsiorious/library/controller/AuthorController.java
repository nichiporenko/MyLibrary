package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.Author;
import com.excelsiorious.library.service.AuthorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {
    public TableView<Author> authorTable;
    public TableColumn<Author, String> idColumn;
    public TableColumn<Author, String> firstNameColumn;
    public TableColumn<Author, String> lastNameColumn;
    public TableColumn<Author, String> dateOfBirthColumn;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtDateOfBirth;

    private AuthorService authorService;
    private List<Author> authors;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authorService = Launcher.getContext().getBean(AuthorService.class);

        showData();
    }

    public void addAuthor(ActionEvent actionEvent) {
        String textFirstName = txtFirstName.getText();
        String textLastName = txtLastName.getText();
        String textDOB = txtDateOfBirth.getText();
        if (!textFirstName.isEmpty() && !textLastName.isEmpty() && !textDOB.isEmpty()) {
            int nextId = authors.size() + 1;
            Author author = new Author();
            author.setId(nextId);
            author.setFirstName(textFirstName);
            author.setLastName(textLastName);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            author.setDateOfBirth(LocalDate.parse(textDOB, formatter));
            authorService.create(author);
            showData();
        }
    }

    private void showData() {
        authors = authorService.findAll();
        ObservableList<Author> observableList = FXCollections.observableArrayList(authors);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        authorTable.setItems(observableList);
    }
}
