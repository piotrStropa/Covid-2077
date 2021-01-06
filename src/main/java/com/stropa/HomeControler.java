package com.stropa;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class HomeControler {
    @FXML private Button showAll;
    @FXML private Button showCountrySpec;
    @FXML private ChoiceBox allDataChoice;
    @FXML private ChoiceBox countryChoice;
    @FXML private ChoiceBox timeChoice;
    @FXML private ChoiceBox countryDataChoice;
    @FXML private BarChart mainChart;

    @FXML void initialize() {
    }

    void updateChart(BarChart newChart){

    }

}
