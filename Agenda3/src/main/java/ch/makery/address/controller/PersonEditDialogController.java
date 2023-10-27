package ch.makery.address.controller;

import ch.makery.address.model.AgendaModelo;
import ch.makery.address.model.ExcepcionPerson;
import ch.makery.address.model.PersonVO;
import ch.makery.address.util.ConversionVO_Person;
import javafx.beans.property.IntegerProperty;
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
    private Integer id;

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
    private IntegerProperty numPerson;
    @FXML
    private ProgressBar barrita;
    @FXML
    private Label progreso;

    private AgendaModelo am;
    private ConversionVO_Person cvp;
    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    public PersonEditDialogController() {
    }

    public IntegerProperty getNumPerson() {
        return numPerson;
    }
    public IntegerProperty numPersonProperty() {
        return numPerson;
    }
    public void setNumPerson(IntegerProperty numPersonI) {
        this.numPerson=numPersonI;
    }
    public void setProgreso(IntegerProperty numPerson){
        this.progreso.setText(numPerson.getValue()+"/50");
    }
    public void setBarrita(IntegerProperty numPerson){
        this.barrita.setProgress(numPerson.doubleValue()/50);
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
     * Sets the person to be edited in the dialog.
     *
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
        setBarrita(getNumPerson());
        setProgreso(getNumPerson());
    }
    public void CrearPersonAPersonVO(Person person) throws ExcepcionPerson {
        cvp=new ConversionVO_Person();
        PersonVO personVO=new PersonVO();
        personVO=cvp.convertirPersonaVO(person);
        am.crearPersonVO(personVO);
    }
    public void editarPersonAPersonVO(Person person) throws ExcepcionPerson {
        cvp=new ConversionVO_Person();
        PersonVO personVO=new PersonVO();
        personVO=cvp.convertirPersonaVO(person);
        am.editarPersonVO(personVO);
    }
    public void setModelo(AgendaModelo am) {
        this.am=am;
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
        if (isInputValid()) {
            boolean crear=false;
            if(id==null){
                crear=true;
            }
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));
            if(crear){
                try{
                    person.setIdentificador(am.lastId()+1);
                    CrearPersonAPersonVO(person);
                    okClicked = true;
                }catch(ExcepcionPerson e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al añadir la persona");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para añadir la persona");
                    alert.showAndWait();
                    handleCancel();
                }
            }else{
                try{
                    editarPersonAPersonVO(person);
                    okClicked = true;
                }catch(ExcepcionPerson e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error al editar la persona");
                    alert.setTitle("Error con la base de datos");
                    alert.setContentText("No se puede conectar con la base de datos para editar la persona");
                    alert.showAndWait();
                    handleCancel();
                }
            }
            dialogStage.close();
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

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
