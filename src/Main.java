import Components.TaskList;
import UserInterface.GraphicalUI;
import UserInterface.TextUI;
import javafx.application.Application;

/**
 * The main entry point into the to-do list program. A new {@link TaskList} object is created
 * to keep a track of tasks for the text user interface. The text user interface is started
 * with {@link TextUI#start()}. The {@link GraphicalUI} is launched.
 */
public class Main {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        TextUI tui = new TextUI(taskList);
        //tui.start();
        Application.launch(GraphicalUI.class, args);

    }
}
