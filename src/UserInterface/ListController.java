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
import java.util.List;

/**
 * A controller for the main UI of the to-do list application.
 */
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

    /**
     * The constructor:
     * Creates a new {@link TaskList} to act as a temporary data store. A {@link FileHandler} to
     * handle saving and loading of files. A {@link FXCollections#observableList(List)} to track changes.
     */
    public ListController() {
        this.taskList = new TaskList();
        this.fileHandler = new FileHandler(taskList);
        this.obsTaskList = FXCollections.observableList(taskList.tasksProperty());
        listviewList = new ListView<>();
    }

    /**
     * Initialise the UI by setting the {@link TextField} to the users desktop
     */
    @FXML
    public void initialize() {
        String desktopPath = System.getProperty("user.home") + "/Desktop/";
        String fileName = "Todo List.txt";
        System.out.println(desktopPath+fileName);
        txtBoxPath.setText(desktopPath+fileName);
    }

    /**
     * Executes when save button is pressed. Creates a new {@link TaskList} to store the changes
     * and creates a new {@link FileHandler} to save the file to the path in the {@link TextField}.
     */
    @FXML
    public void saveFile() {
        TaskList tl = new TaskList();
        FileHandler fh = new FileHandler(tl);
        for (Task t:listviewList.getItems()) {
            tl.add(t);
        }
        fh.write(txtBoxPath.getText());
    }

    /**
     *  Uses the {@code fileHandler} to read the file at the path given by {@link TextField} and sets
     *  that to the {@code obsTaskList}. The items in the {@link ListView} are then set to this {@code obsTaskList}.
     */
    @FXML
    public void loadFile() {
        obsTaskList = fileHandler.read(txtBoxPath.getText()).tasksProperty();
        listviewList.setItems(obsTaskList);
    }

    /**
     * Executes when complete button is pressed to mark a task as complete or not complete depending on
     * the current state of the current state of the {@code task}. The index of the selected item is retrieved
     * and is checked to make sure it is greater than -1 otherwise a {@code NullPointerException} may occur when
     * nothing is selected. The item is retrieved and marked as the inverse of its {@link Task#isCompleted()}.
     * The {@link ListView} is then refreshed.
     */
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

    /**
     * To remove a task, a new dialog is opened. The modality is set to make sure the user has to
     * select some option in the dialog. If the user selects confirm
     * (checked by {@link RemoveController#isConfirmed()},
     * then the selected item
     * will be removed from the {@link ListView}.
     */
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

    /**
     *  Add task button displays a new dialog to add a task. The modality is set to make sure the user has to interact
     *  with the dialog. The user types their task into the {@link TextField} and either confirms the addition or cancels this.
     *  If the confirm button is pressed (checked by {@link AddController#isConfirmed()} then a new {@link Task}
     *  object is created and added to the {@link ListView}.
     */
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
