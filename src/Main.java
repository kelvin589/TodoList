import Components.TaskList;
import UserInterface.GraphicalUI;
import UserInterface.TextUI;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        TextUI tui = new TextUI(taskList);
        GraphicalUI gui = new GraphicalUI();
        //tui.start();
        Application.launch(GraphicalUI.class, args);

    }
}
