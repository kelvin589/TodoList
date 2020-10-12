package UserInterface;

import Components.FileHandler;
import Components.Task;
import Components.TaskList;

import java.util.Scanner;

public class TextUI {
    private TaskList taskList;
    private Scanner scanner;
    private FileHandler fileHandler;

    public TextUI(TaskList taskList) {
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
        this.fileHandler = new FileHandler(taskList);
    }

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

    private int numberChecker(String option) {
        try {
            return Integer.parseInt(option);
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    private void addTask(String task) {
        Task t = new Task(task);
        taskList.add(t);
        if (taskList.contains(t)) {
            System.out.println("Successfully added");
        } else {
            System.out.println("An error has occurred");
        }
    }

    private void removeTask(int taskNumber) {
        if (taskList.remove(taskNumber)) {
            System.out.println("Successfully removed");
        } else {
            System.out.println("An error has occurred");
        }
    }

    private void completeTask(int taskNumber) {
        if (taskList.complete(taskNumber)) {
            System.out.println("Successfully completed");
        } else {
            System.out.println("An error has occurred");
        }
    }

    private void saveFile(String path) {
        if (fileHandler.write(path)) {
            System.out.println("Successfully completed");
        } else {
            System.out.println("An error has occurred");
        }
    }

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
}
