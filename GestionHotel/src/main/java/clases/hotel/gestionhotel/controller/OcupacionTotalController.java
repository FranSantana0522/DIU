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
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis xAxis;

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
        Set<Integer>[] occupiedRoomsByMonth = new Set[12];
        for (int i = 0; i < occupiedRoomsByMonth.length; i++) {
            occupiedRoomsByMonth[i] = new HashSet<>();
        }

        for (Reserva p : reservas) {
            int monthL = p.getFechaLlegada().getMonthValue() - 1;
            int room = p.numHabitacionProperty().get();
            occupiedRoomsByMonth[monthL].add(room);
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        for (int i = 0; i < monthCounterL.length; i++) {
            series.getData().add(new XYChart.Data<>(monthNames.get(i), occupiedRoomsByMonth[i].size()));
        }
        barChart.getData().add(series);
    }

}
