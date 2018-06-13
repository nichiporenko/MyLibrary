package com.excelsiorious.library.controller;

import com.excelsiorious.library.entity.*;
import com.excelsiorious.library.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.support.AbstractApplicationContext;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookController implements Initializable {
    private static final String SPLITTER_NAME = " ";
    private static final String PROPERTY_ID = "id";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_AUTHOR = "author";
    private static final String PROPERTY_COUNTRY = "country";
    private static final String PROPERTY_GENRE = "genre";
    private static final String PROPERTY_LANGUAGE = "language";
    private static final String PROPERTY_PUBLISHING_HOUSE = "publishingHouse";
    private static final String PROPERTY_CIRCULATION = "circulation";
    public TableView<Book> bookTable;
    public TableColumn<Book, String> idColumn;
    public TableColumn<Book, String> nameColumn;
    public TableColumn<Book, String> authorColumn;
    public TableColumn<Book, String> countryColumn;
    public TableColumn<Book, String> genreColumn;
    public TableColumn<Book, String> languageColumn;
    public TableColumn<Book, String> publishingHouseColumn;
    public TableColumn<Book, String> circulationColumn;
    public TextField txtName;
    public TextField txtAuthor;
    public TextField txtCountry;
    public TextField txtGenre;
    public TextField txtLanguage;
    public TextField txtPublishingHouse;
    public TextField txtCirculation;
    private BookService bookService;
    private AuthorService authorService;
    private CountryService countryService;
    private GenreService genreService;
    private LanguageService languageService;
    private PublishingHouseService publishingHouseService;
    private List<Book> books;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AbstractApplicationContext context = Launcher.getContext();
        bookService = context.getBean(BookService.class);
        authorService = context.getBean(AuthorService.class);
        countryService = context.getBean(CountryService.class);
        genreService = context.getBean(GenreService.class);
        languageService = context.getBean(LanguageService.class);
        publishingHouseService = context.getBean(PublishingHouseService.class);

        showData();
    }

    public void addBook(ActionEvent actionEvent) {
        String textName = txtName.getText();
        String textAuthor = txtAuthor.getText();
        String textCountry = txtCountry.getText();
        String textGenre = txtGenre.getText();
        String textLanguage = txtLanguage.getText();
        String textPublishingHouse = txtPublishingHouse.getText();
        String textCirculation = txtCirculation.getText();

        if (!textName.isEmpty() && !textAuthor.isEmpty() && !textCountry.isEmpty() && !textGenre.isEmpty()
                && !textLanguage.isEmpty() && !textPublishingHouse.isEmpty() && !textCirculation.isEmpty()) {
            try {
                int nextId = books.size() + 1;
                Book book = new Book();
                book.setId(nextId);
                book.setName(textName);
                String[] names = textAuthor.split(SPLITTER_NAME);
                Optional<Author> authorOptional = authorService.findByName(names[0], names[1]);
                if (!authorOptional.isPresent()) {
                    return;
                }
                book.setAuthor(authorOptional.get());
                Optional<Country> countryOptional = countryService.findByName(textCountry);
                if (!countryOptional.isPresent()) {
                    return;
                }
                book.setCountry(countryOptional.get());
                Optional<Genre> genreOptional = genreService.findByName(textGenre);
                if (!genreOptional.isPresent()) {
                    return;
                }
                book.setGenre(genreOptional.get());
                Optional<Language> languageOptional = languageService.findByName(textLanguage);
                if (!languageOptional.isPresent()) {
                    return;
                }
                book.setLanguage(languageOptional.get());

                Optional<PublishingHouse> publishingHouseOptional = publishingHouseService.findByName(textPublishingHouse);
                if (!publishingHouseOptional.isPresent()) {
                    return;
                }
                book.setPublishingHouse(publishingHouseOptional.get());
                book.setCirculation(Integer.parseInt(textCirculation));
                bookService.create(book);
            } catch (NumberFormatException exception) {
                return;
            }
            showData();
        }
    }

    private void showData() {
        books = bookService.findAll();
        ObservableList<Book> observableList = FXCollections.observableArrayList(books);

        idColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_ID));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_NAME));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_AUTHOR));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_COUNTRY));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_GENRE));
        languageColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_LANGUAGE));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_PUBLISHING_HOUSE));
        circulationColumn.setCellValueFactory(new PropertyValueFactory<>(PROPERTY_CIRCULATION));
        bookTable.setItems(observableList);
    }
}
