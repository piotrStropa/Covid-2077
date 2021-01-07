package com.stropa;

import com.stropa.clients.HttpClient;
import com.stropa.data.DataType;
import com.stropa.data.TimeInterval;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeControler {
    @FXML private Button showAll;
    @FXML private Button showCountrySpec;
    @FXML private ChoiceBox allDataChoice;
    @FXML private ChoiceBox countryChoice;
    @FXML private ChoiceBox timeChoice;
    @FXML private ChoiceBox dailyDataChoice;
    @FXML private BarChart mainChart;

    private static final List<String> timeChoiceList = List.of("Last month", "Last six months", "Since day one");
    private static final List<String> dailyDataChoiceList = List.of("Confirmed", "Deaths", "Recovered");
    private static final List<String> summaryDataChoiceList = List.of("Total Confirmed", "Total Deaths", "Total Recovered", "Mortality Ratio");
    private static final Map<String, String> countryMap = new HashMap<>();
    @FXML void initialize() {
        HttpClient.getInstance();
        GraphDataLoader.getInstance();
        countryChoice.setValue("Select country");
        timeChoice.setValue("Select time interval");
        allDataChoice.setValue("Select data type");
        dailyDataChoice.setValue("Select data type");

        try {
            List<String> countries = Files.readAllLines(
                    Path.of(getClass()
                    .getClassLoader()
                    .getResource("countries.txt")
                    .toURI()));

            countries.forEach(c -> countryMap.put(c.substring(0, c.indexOf("|")), c.substring(c.indexOf("|") + 1)));
            countries.forEach(c -> countryChoice.getItems().add(c.substring(0, c.indexOf("|"))));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        timeChoiceList.forEach(c -> timeChoice.getItems().add(c));
        dailyDataChoiceList.forEach(c -> dailyDataChoice.getItems().add(c));
        summaryDataChoiceList.forEach(c -> allDataChoice.getItems().add(c));

        mainChart.setLegendVisible(false);

        showAll.setOnMouseClicked(e -> {
            DataType type = GraphDataLoader.getInstance().toDataType((String) allDataChoice.getValue());
            GraphDataLoader.getInstance().loadSummaryData(type);
            mainChart.getData().clear();
            mainChart.layout();
            mainChart.getData().add(GraphDataLoader.getInstance().getChartData());
            mainChart.getData().sort(Comparator.comparingDouble(x -> -((XYChart.Data<String, Number>)x).getYValue().doubleValue()));
        });

        showCountrySpec.setOnMouseClicked(e -> {
            DataType type = GraphDataLoader.getInstance().toDataType((String) dailyDataChoice.getValue());
            TimeInterval interval = GraphDataLoader.getInstance().toTimeInterval((String)timeChoice.getValue());
            String country = countryMap.get(countryChoice.getValue());

            GraphDataLoader.getInstance().loadDailyData(country, type, interval);
            mainChart.getData().clear();
            mainChart.layout();
            mainChart.getData().add(GraphDataLoader.getInstance().getChartData());
        });
    }
}
