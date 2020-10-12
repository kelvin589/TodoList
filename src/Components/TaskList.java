package Components;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private ListProperty<Task> tasksProperty;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksProperty = new SimpleListProperty<>(FXCollections.observableArrayList(tasks));
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public boolean remove(int taskNumber) {
        if (taskNumber>0 && taskNumber<=tasks.size()) {
            tasks.remove(taskNumber-1);
            return true;
        }
        return false;
    }

    public boolean complete(int taskNumber) {
        if (taskNumber>0 && taskNumber<=tasks.size()) {
            Task t = tasks.get(taskNumber-1);
            t.setCompleted(true);
            return t.isCompleted();
        }
        return false;
    }

    public void printTasks() {
        for (int i = 0; i<tasks.size(); i++) {
            System.out.println(i+1 + ": " + tasks.get(i).toString());
        }
    }

    public StringBuilder formattedTasks() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.getTask() + " " + t.isCompleted());
            sb.append("\n");
        }
        sb.append("-1");
        return sb;
    }

    public boolean contains(Task task) {
        return tasks.contains(task);
    }

    public ListProperty<Task> tasksProperty() {
        this.tasksProperty = new SimpleListProperty<>(FXCollections.observableArrayList(tasks));
        return tasksProperty;
    }
}
