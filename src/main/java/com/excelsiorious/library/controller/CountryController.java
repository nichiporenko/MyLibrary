package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.Country;
import com.excelsiorious.library.service.CountryService;
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

public class CountryController implements Initializable {
    private static final String PROPERTY_ID = "id";
    private static final String PROPERTY_NAME = "name";
    public TableView<Country> countryTable;
    public TableColumn<Country, String> idColumn;
    public TableColumn<Country, String> nameColumn;
    public TextField txtName;

    private CountryService countryService;
    private List<Country> countries;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        countryService = Launcher.getContext().getBean(CountryService.class);

        showData();
    }

    public void addCountry(ActionEvent actionEvent) {
        String text = txtName.getText();
        if (!text.isEmpty()) {
            int nextId = countries.size() + 1;
            Country country = new Country();
            country.setId(nextId);
            country.setName(text);
            countryService.create(country);
            showData();
        }
    }

    private void showData() {
        countries = countryService.findAll();
        ObservableList<Country> observableList = FXCollections.observableArrayList(countries);

        idColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_NAME));
        countryTable.setItems(observableList);
    }
}
