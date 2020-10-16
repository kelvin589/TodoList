package UserInterface;

import Components.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the dialog for removing items from the list
 */
public class RemoveController {
    @FXML private Label lblTask;
    private Task toRemove;
    private boolean confirmed = false;

    /**
     * The remove controller gets a {@link Task} so it is able to display the text of the {@link Task}
     * @param task {@link Task} to remove
     */
    public RemoveController(Task task) {
        this.toRemove = task;
    }

    /**
     * Initialise the remove dialog by setting the {@link Label} to the text of the task
     */
    @FXML
    public void initialize() {
        this.lblTask.setText(toRemove.toString());
    }

    /**
     * If confirm button has been pressed then set {@code confirmed} to true and close the dialog
     */
    @FXML
    public void confirmPressed() {
        confirmed = true;
        lblTask.getScene().getWindow().hide();
    }

    /**
     * Close the dialog if the cancel button is pressed
     */
    @FXML
    public void cancelPressed() {
        lblTask.getScene().getWindow().hide();
    }

    /**
     * Check if action has been confirmed
     * @return true if action has been confirmed or false
     */
    public boolean isConfirmed() {
        return confirmed;
    }
}
