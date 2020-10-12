package UserInterface;

import Components.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RemoveController {
    @FXML private Label lblTask;
    private Task toRemove;
    private boolean confirmed = false;

    public RemoveController(Task task) {
        this.toRemove = task;
    }

    @FXML
    public void initialize() {
        this.lblTask.setText(toRemove.toString());
    }

    @FXML
    public void confirmPressed() {
        confirmed = true;
        lblTask.getScene().getWindow().hide();
    }

    @FXML
    public void cancelPressed() {
        lblTask.getScene().getWindow().hide();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
