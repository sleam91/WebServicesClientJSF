package fxml.controller;

import client.StarWarsClient;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.inject.Inject;
import model.StarWarsPlanet;

/**
 * FXML Controller class
 *
 * @author Niklas
 */
public class MainMenuController implements Initializable {

    @Inject
    StarWarsClient client;

    final Float[] progressValues = new Float[]{0f, 0.2f, 0.3f,};

    @FXML
    private TableColumn<String, StarWarsPlanet> col_name;
    @FXML
    private TableColumn<String, StarWarsPlanet> col_dia;
    @FXML
    private TableColumn<String, StarWarsPlanet> col_cli;
    @FXML
    private TableColumn<String, StarWarsPlanet> col_pop;
    @FXML
    private TableColumn<String, StarWarsPlanet> col_ter;

    private final ObservableList<StarWarsPlanet> allPlanets = FXCollections.observableArrayList();
    @FXML
    private TableView<StarWarsPlanet> table_planets;
    @FXML
    private TextField textbox_filter;
    @FXML
    private ProgressBar progressbar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_dia.setCellValueFactory(new PropertyValueFactory<>("diameter"));
        col_cli.setCellValueFactory(new PropertyValueFactory<>("climate"));
        col_pop.setCellValueFactory(new PropertyValueFactory<>("population"));
        col_ter.setCellValueFactory(new PropertyValueFactory<>("terrain"));
        table_planets.setItems(allPlanets);
    }

    @FXML
    private void searchWithId(ActionEvent event) {

        String id = textbox_filter.getText();
        StarWarsPlanet planet = client.findStarWarsPlanetById(id);
        table_planets.setItems(planet != null
                ? FXCollections.observableArrayList(planet)
                : FXCollections.observableArrayList());

    }

    @FXML
    private void searchWithName(ActionEvent event) {

        String search = textbox_filter.getText();
        List<StarWarsPlanet> planets = client.getAllSearched(search);
        table_planets.setItems(FXCollections.observableArrayList(planets));

    }

    @FXML
    private void updateTable(ActionEvent event) {

        table_planets.setItems(FXCollections.observableArrayList(client.getAll()));

    }

}
