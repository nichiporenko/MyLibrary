package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.PublishingHouse;
import com.excelsiorious.library.service.PublishingHouseService;
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

public class PublishingHouseController implements Initializable {
    public TableView<PublishingHouse> publishingHouseTable;
    public TableColumn<PublishingHouse, String> idColumn;
    public TableColumn<PublishingHouse, String> nameColumn;
    public TextField txtName;

    private PublishingHouseService publishingHouseService;
    private List<PublishingHouse> publishingHouses;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        publishingHouseService = Launcher.getContext().getBean(PublishingHouseService.class);

        showData();
    }

    public void addPublishingHouse(ActionEvent actionEvent) {
        String text = txtName.getText();
        if (!text.isEmpty()) {
            int nextId = publishingHouses.size() + 1;
            PublishingHouse language = new PublishingHouse();
            language.setId(nextId);
            language.setName(text);
            publishingHouseService.create(language);
            showData();
        }
    }

    private void showData() {
        publishingHouses = publishingHouseService.findAll();
        ObservableList<PublishingHouse> observableList = FXCollections.observableArrayList(publishingHouses);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        publishingHouseTable.setItems(observableList);
    }
}
