package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Controller class for the dialog to add items to the to-do list
 */
public class AddController {
    @FXML private TextField txtboxTask;
    private String task;
    private boolean confirmed = false;

    /**
     * Get the task that is enterted into the dialog
     * @return the string task
     */
    public String getTask() {
        return this.task;
    }

    @FXML
    public void initialize() {

    }

    /**
     * When the confirmed button is pressed, the text in the {@link TextField} is set to the {@code task}
     * and {@code confirmed} is set to true then the dialog is closed.
     */
    @FXML
    public void confirmPressed() {
        this.task = txtboxTask.getText();
        confirmed = true;
        txtboxTask.getScene().getWindow().hide();
    }

    /**
     * If the cancel button is pressed, the dialog is closed by hiding it.
     */
    @FXML
    public void cancelPressed() {
        txtboxTask.getScene().getWindow().hide();
    }

    /**
     * Check if dialog has been confirmed
     * @return true if the action has been confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}
