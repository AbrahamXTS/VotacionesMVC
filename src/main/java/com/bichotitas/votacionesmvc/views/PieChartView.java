package com.bichotitas.votacionesmvc.views;

import com.bichotitas.votacionesmvc.repositories.FileProductsRepository;
import com.bichotitas.votacionesmvc.repositories.FileResultsRepository;
import com.bichotitas.votacionesmvc.repositories.ProductsRepository;
import com.bichotitas.votacionesmvc.repositories.ResultsRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class PieChartView implements Initializable {
    private final ProductsRepository productsRepository;
    private final ResultsRepository resultsRepository;

    @FXML
    public HBox pieChartContainer;

    public PieChartView() {
        productsRepository = new FileProductsRepository("src/main/resources/products.txt");
        resultsRepository = new FileResultsRepository("src/main/resources/results.txt");
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        updateUI();
    }

    public void updateUI() {
        pieChartContainer.getChildren().clear();

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        productsRepository.getAllProducts().forEach(productName ->
                data.add(new PieChart.Data(
                                productName,
                                resultsRepository.getResultsByProductName(productName).size()
                        )
                )
        );

        PieChart pieChart = new PieChart(data);
        pieChartContainer.getChildren().add(pieChart);
    }
}
