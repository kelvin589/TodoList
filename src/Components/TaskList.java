package Components;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TaskList} is used to keep track of {@link Task} objects using an ArrayList.
 */
public class TaskList {
    private List<Task> tasks;
    private ListProperty<Task> tasksProperty;

    /**
     * The constructor:
     * Takes no parameters. Initialises a new {@code ArrayList}. Initialises a new {@code SimpleListProperty},
     * for the GUI, using the {@code tasks} list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksProperty = new SimpleListProperty<>(FXCollections.observableArrayList(tasks));
    }

    /**
     * Add a new tasks to the {@code list}
     * @param task the {@link Task} to add to the list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove the {@link Task} from the list using the task number. Checks to make sure this number
     * is not negative and does not exceed the total number of tasks. The index for the task
     * removed is {@code taskNumber-1} because it is from the perspective of the ordered list in the UI
     * @param taskNumber the task number (ordered from 1 to ...)
     * @return true if task was removed or false
     */
    public boolean remove(int taskNumber) {
        if (taskNumber>0 && taskNumber<=tasks.size()) {
            tasks.remove(taskNumber-1);
            return true;
        }
        return false;
    }

    /**
     * Mark a {@link Task} as complete. Checks to make sure {@code taskNumber} is within the bounds
     * of the list before setting as complete.
     * @param taskNumber the tasks number (ordered from 1 to...)
     * @return true if tasks was successfully changed or false
     */
    public boolean complete(int taskNumber) {
        if (taskNumber>0 && taskNumber<=tasks.size()) {
            Task t = tasks.get(taskNumber-1);
            t.setCompleted(true);
            return true;
        }
        return false;
    }

    /**
     * Print the tasks of the list and include ordering for the tasks (from 1 to ...)
     */
    public void printTasks() {
        for (int i = 0; i<tasks.size(); i++) {
            System.out.println(i+1 + ": " + tasks.get(i).toString());
        }
    }

    /**
     * The tasks in the list are formatted ready to save into a file. Simple format: the task name followed
     * by true or false (representing completion). A final -1 is appended as an indication to the
     * stopping point of the file.
     * @return the {@link StringBuilder} for the formatted tasks
     */
    public StringBuilder formattedTasks() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.getTask()).append(" ").append(t.isCompleted());
            sb.append("\n");
        }
        sb.append("-1");
        return sb;
    }

    /**
     * Check if the list contains a certain {@link Task}
     * @param task the {@link Task} to check for in the list
     * @return true if the list contains the task or false
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    /**
     * Get the {@code tasksProperty} for use with the GUI. A new {@link ListProperty} is created
     * with the {@code tasks} list.
     * @return the {@link ListProperty}
     */
    public ListProperty<Task> tasksProperty() {
        this.tasksProperty = new SimpleListProperty<>(FXCollections.observableArrayList(tasks));
        return tasksProperty;
    }
}
