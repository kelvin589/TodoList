package UserInterface;

import Components.FileHandler;
import Components.Task;
import Components.TaskList;

import java.util.Scanner;

/**
 * A text based user interface for the to-do list application
 */
public class TextUI {
    private TaskList taskList;
    private Scanner scanner;
    private FileHandler fileHandler;

    /**
     * The constructor:
     * Takes a {@link TaskList} to read or write for keeping track of {@link Task} objects. A {@link Scanner}
     * is created to take user input through the console. A {@link FileHandler} is created to read and write to and from files
     * @param taskList the {@link TaskList} to load
     */
    public TextUI(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
        this.fileHandler = new FileHandler(taskList);
    }

    /**
     * The method to start the text UI. Prints the options available to the user at the start. Looping
     * condition that checks the input from the console. A switch statement with cases relating to the
     * options displayed at the start. If the option entered does not match any of the cases,
     * then the options will be printed again.
     */
    public void start() {
        printOptions();
        while (true) {
            System.out.println("Enter a number to select an option:");
            String line = scanner.nextLine();
            int option = numberChecker(line);
            switch (option) {
                case 1:
                    System.out.println("Enter the task:");
                    addTask(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Enter the task number to remove:");
                    removeTask(numberChecker(scanner.nextLine()));
                    break;
                case 3:
                    System.out.println("Enter the task to mark as complete:");
                    completeTask(numberChecker(scanner.nextLine()));
                    break;
                case 4:
                    taskList.printTasks();
                    break;
                case 5:
                    System.out.println("Enter the path to the file:");
                    saveFile(scanner.nextLine());
                    break;
                case 6:
                    System.out.println("Enter the path to the file:");
                    readFile(scanner.nextLine());
                    break;
                case 7:
                    System.out.println("Goodbye");
                    return;
                default:
                    printOptions();
            }
        }
    }

    /**
     * Print the options available for the text user interface
     */
    private void printOptions() {
        System.out.println("Options:");
        System.out.println("1 - Add a task to the list");
        System.out.println("2 - Remove a task from the list");
        System.out.println("3 - Mark a task as complete");
        System.out.println("4 - Print the list");
        System.out.println("5 - Save list to file");
        System.out.println("6 - Read from saved file");
        System.out.println("7 - Quit");
    }

    //********************
    // Methods to execute available options
    //********************

    /**
     * Add task to the {@code taskList} using {@link TaskList#contains(Task)}
     * @param task the task to add
     */
    private void addTask(String task) {
        Task t = new Task(task);
        taskList.add(t);
        if (taskList.contains(t)) {
            System.out.println("Successfully added");
        } else {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Remove task from the {@code taskList} using the relative {@code taskNumber} (1 to ...) using
     * {@link TaskList#remove(int)}
     * @param taskNumber the number of the task
     */
    private void removeTask(int taskNumber) {
        if (taskList.remove(taskNumber)) {
            System.out.println("Successfully removed");
        } else {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Mark a task in the {@code taskList} as complete using the {@code taskNumber} with {@link TaskList#complete(int)}
     * @param taskNumber the number of the task
     */
    private void completeTask(int taskNumber) {
        if (taskList.complete(taskNumber)) {
            System.out.println("Successfully completed");
        } else {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Save the file to the {@code path} specified using {@link FileHandler#write(String)}
     * @param path the path to the file
     */
    private void saveFile(String path) {
        if (fileHandler.write(path)) {
            System.out.println("Successfully completed");
        } else {
            System.out.println("An error has occurred");
        }
    }

    /**
     * Read the file from the {@code path} specified using {@link FileHandler#read(String)}
     * @param path the path to the file
     */
    private void readFile(String path) {
        TaskList newTaskList = fileHandler.read(path);
        if (newTaskList != null) {
            this.taskList = newTaskList;
            this.fileHandler = new FileHandler(newTaskList);
            newTaskList.printTasks();
        } else {
            System.out.println("An error has occurred");
        }
    }

    //********************
    // Checking methods
    //********************

    /**
     * Tries to parse a string into an int
     * @param option the string to parse
     * @return the converted int
     */
    private int numberChecker(String option) {
        try {
            return Integer.parseInt(option);
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
}
