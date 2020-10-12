package UserInterface;

import Components.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicalUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            final TaskList taskList = new TaskList();
            final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TodoListUI.fxml"));
            fxmlLoader.setController(new ListController());
            final Parent root = fxmlLoader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("To-do List");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
