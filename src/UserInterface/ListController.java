package UserInterface;

import Components.FileHandler;
import Components.Task;
import Components.TaskList;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ListController {
    private ObservableList<Task> obsTaskList;
    private FileHandler fileHandler;
    private TaskList taskList;

    @FXML private Button btnSave;
    @FXML private Button btnLoad;
    @FXML private Button btnComplete;
    @FXML private Button btnRemove;
    @FXML private Button btnQuit;
    @FXML private Button btnAdd;
    @FXML private TextField txtBoxPath;
    @FXML private ListView<Task> listviewList;

    public ListController() {
        this.taskList = new TaskList();
        this.fileHandler = new FileHandler(taskList);
        this.obsTaskList = FXCollections.observableList(taskList.tasksProperty());
        listviewList = new ListView<>();
    }

    @FXML
    public void initialize() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String fileName = "Todo List.txt";
        System.out.println(desktopPath+fileName);
        txtBoxPath.setText(desktopPath+fileName);
    }

    @FXML
    public void saveFile() {
        TaskList tl = new TaskList();
        FileHandler fh = new FileHandler(tl);
        for (Task t:listviewList.getItems()) {
            tl.add(t);
        }
        fh.write(txtBoxPath.getText());
    }

    @FXML
    public void loadFile() {
        obsTaskList = fileHandler.read(txtBoxPath.getText()).tasksProperty();
        listviewList.setItems(obsTaskList);
    }

    @FXML
    public void taskComplete() {
        int index = listviewList.getSelectionModel().getSelectedIndex();
        Task task = listviewList.getSelectionModel().getSelectedItem();
        if (index > -1) {
            task.setCompleted(!task.isCompleted());
            System.out.println(listviewList.getSelectionModel().getSelectedItem());
        }
        listviewList.refresh();
    }

    @FXML
    public void removeTask() {
        int index = listviewList.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            Task task =  listviewList.getSelectionModel().getSelectedItem();
            try {
                final FXMLLoader l = new FXMLLoader();
                l.setLocation(getClass().getResource("RemoveDialog.fxml"));
                RemoveController r = new RemoveController(task);
                l.setController(r);
                final Parent root = l.load();

                Stage s = new Stage();
                s.initModality(Modality.APPLICATION_MODAL);
                final Scene scene = new Scene(root, 400, 130);
                s.setScene(scene);
                s.showAndWait();

                if (r.isConfirmed()) {
                    listviewList.getItems().remove(index);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    @FXML
    public void addTask() {
        try {
            final FXMLLoader l = new FXMLLoader();
            l.setLocation(getClass().getResource("AddDialog.fxml"));
            AddController a = new AddController();
            l.setController(a);
            final Parent root = l.load();

            Stage s = new Stage();
            s.initModality(Modality.APPLICATION_MODAL);
            final Scene scene = new Scene(root, 400, 130);
            s.setScene(scene);
            s.showAndWait();

            if (a.isConfirmed()) {
                listviewList.getItems().add(new Task(a.getTask()));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
