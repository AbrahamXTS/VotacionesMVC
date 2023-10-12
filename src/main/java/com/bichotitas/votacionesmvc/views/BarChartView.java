package com.bichotitas.votacionesmvc.views;

import com.bichotitas.votacionesmvc.repositories.FileProductsRepository;
import com.bichotitas.votacionesmvc.repositories.FileResultsRepository;
import com.bichotitas.votacionesmvc.repositories.ProductsRepository;
import com.bichotitas.votacionesmvc.repositories.ResultsRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartView implements Initializable {
    private final ProductsRepository productsRepository;
    private final ResultsRepository resultsRepository;

    @FXML
    public HBox barChartContainer;

    public BarChartView() {
        productsRepository = new FileProductsRepository("src/main/resources/products.txt");
        resultsRepository = new FileResultsRepository("src/main/resources/results.txt");
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        updateUI();
    }

    public void updateUI() {
        barChartContainer.getChildren().clear();

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Productos");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total de votos");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Votos");

        productsRepository.getAllProducts().forEach(productName ->
                data.getData().add(
                        new XYChart.Data<>(
                                productName,
                                resultsRepository.getResultsByProductName(productName).size()
                        )
                )
        );

        barChart.getData().add(data);
        barChartContainer.getChildren().add(barChart);
    }
}
