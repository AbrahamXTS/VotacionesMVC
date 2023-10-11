package com.bichotitas.votacionesmvc.views;

import com.bichotitas.votacionesmvc.Main;
import com.bichotitas.votacionesmvc.controllers.VotesController;
import com.bichotitas.votacionesmvc.repositories.FileProductsRepository;
import com.bichotitas.votacionesmvc.repositories.FileResultsRepository;
import com.bichotitas.votacionesmvc.repositories.ProductsRepository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VotesCountView implements Initializable {
    private final ProductsRepository productsRepository;
    private final FileResultsRepository fileResultsRepository;
    private final VotesController votesController;
    @FXML
    public HBox buttonsContainer;

    public VotesCountView() {
        productsRepository = new FileProductsRepository("src/main/resources/products.txt");
        fileResultsRepository = new FileResultsRepository("src/main/resources/results.txt");

        votesController = new VotesController(fileResultsRepository);
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        if (location.toString().contains("votes-count.fxml")) {
            updateVotesCountUI();
        }
    }

    public void updateVotesCountUI() {
        buttonsContainer.getChildren().clear();

        productsRepository.getAllProducts().forEach(productName -> {
            VBox container = new VBox();
            container.setSpacing(10);
            container.setAlignment(Pos.CENTER);

            Text productNameLabel = new Text(productName);

            Button addVoteButton = new Button("Votar");
            addVoteButton.setOnAction(event -> {
                votesController.addVote(productName);
                updateVotesCountUI();
            });

            int votesCount = fileResultsRepository.getResultsByProductName(productName).size();
            Text votesCountLabel = new Text("Votos: " + votesCount);

            container.getChildren()
                    .addAll(productNameLabel, addVoteButton, votesCountLabel);

            buttonsContainer.getChildren().add(container);
        });
    }

    public void openNewWindow(String path, String windowTitle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                Main.class.getResource(path)
        );

        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    public void openBarChart() throws IOException {
        this.openNewWindow("views/bar-chart.fxml", "Gráfica de barras");
    }

    public void openPieChart() throws IOException {
        this.openNewWindow("views/pie-chart.fxml", "Gráfica de pastel");
    }
}