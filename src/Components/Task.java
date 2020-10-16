package Components;

/**
 * A simple class to store the information about the {@code task} and whether or not the
 * {@code task} is {@code completed}.
 */

public class Task {
    private String task;
    private boolean completed;

    /**
     * The constructor:
     * The {@code task} is set using the parameter and all tasks are set to not completed
     * by default
     * @param task the name of the task
     */
    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    /**
     * Get the task
     * @return the {@code task}
     */
    public String getTask() {
        return task;
    }

    /**
     * Change this task object
     * @param task is the new name of the task
     */
    public void renameTask(String task) {
        this.task = task;
    }

    /**
     * Check if the tasks is completed
     * @return {@code completed}
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Set the {@code completed} variable
     * @param completed new boolean to set {@code completed}
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * The string representation is formatted with {@code task} followed by a tick or x symbol
     * @return string representation of the task
     */
    public String toString() {
        if (completed) {
            return task + " ✓";
        }
        return task + " X";
    }
}
