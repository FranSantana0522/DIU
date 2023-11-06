package ch.makery.address.controller;

import ch.makery.address.MainApp;
import ch.makery.address.model.AgendaModelo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {


    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField birthdayField;
    @FXML
    private ProgressBar barrita;
    @FXML
    private Label progreso;
    private Stage dialogStage;
    private Person person;
    private AgendaModelo am;
    private IntegerProperty numPerson=new SimpleIntegerProperty();
    private boolean okClicked = false;



    public PersonEditDialogController() {
    }

    /**
     * Instanciamos la agendaModelo
     * @param am
     */
    public void setAm(AgendaModelo am) {
        this.am = am;
    }
    public void setBarrita(){
        this.numPerson.bind(am.numeroPersonasProperty());
        this.barrita.setProgress((double) numPerson.get() /50);
        this.progreso.setText(String.valueOf(numPerson.get()*100/50+"%"));
        numPerson.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                barrita.setProgress((double) numPerson.get() /50);
                progreso.setText(String.valueOf( numPerson.get()*100/50+"%"));
            }
        });
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Crea la persona y le añade los campos
     * @param person
     */
    public void setPerson(Person person) {
            this.person = person;
            firstNameField.setText(person.getFirstName());
            lastNameField.setText(person.getLastName());
            streetField.setText(person.getStreet());
            postalCodeField.setText(Integer.toString(person.getPostalCode()));
            cityField.setText(person.getCity());
            birthdayField.setText(DateUtil.format(person.getBirthday()));
            birthdayField.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (numPerson.getValue()<50) {
            if (isInputValid()) {
                person.setFirstName(firstNameField.getText());
                person.setLastName(lastNameField.getText());
                person.setStreet(streetField.getText());
                person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
                person.setCity(cityField.getText());
                person.setBirthday(DateUtil.parse(birthdayField.getText()));
                okClicked = true;
                dialogStage.close();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error al añadir una persona");
            alert.setTitle("Error al añadir");
            alert.setContentText("Se ha alcanzado el tamaño maximo de personas de la agenda");
            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0 ||
        postalCodeField.getText().length()!=5) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                     alerta.setTitle("Invalid Fields");
                    alerta.setHeaderText("Please correct invalid fields");
                    alerta.setContentText(errorMessage);
                    alerta.showAndWait();
            return false;
        }
    }

}
