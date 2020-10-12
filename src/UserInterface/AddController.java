package UserInterface;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddController {
    @FXML private TextField txtboxTask;
    private String task;
    private boolean confirmed = false;

    public String getTask() {
        return this.task;
    }

    @FXML
    public void initialize() {

    }

    @FXML
    public void confirmPressed() {
        this.task = txtboxTask.getText();
        confirmed = true;
        txtboxTask.getScene().getWindow().hide();
    }

    @FXML
    public void cancelPressed() {
        txtboxTask.getScene().getWindow().hide();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
