package clases.hotel.gestionhotel.controller;

import clases.hotel.gestionhotel.modelo.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.text.DateFormatSymbols;
import java.util.*;

public class OcupacionTotalController {
    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis xAxis;

    private Integer max=120;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Get an array with the English month names.
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));

        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
    }

    public void setReservaData(List<Reserva> reservas) {
        int[] monthCounterL = new int[12];
        int[] occupiedRoomsByMonth = new int[12];
        for (Reserva p : reservas) {
            int monthL = p.getFechaLlegada().getMonthValue() - 1;
            int room = p.numHabitacionProperty().get();
            occupiedRoomsByMonth[monthL] = occupiedRoomsByMonth[monthL] + room;
            monthCounterL[monthL]++;
        }
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (int i = 0; i < monthCounterL.length; i++) {
            double porcentaje = ((double) occupiedRoomsByMonth[i] / max) * 100;
            series.getData().add(new XYChart.Data<>(monthNames.get(i), porcentaje));
        }
        barChart.getData().add(series);
    }

}
