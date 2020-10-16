package Components;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the file handler for the to-do list program. It takes a {@link TaskList} whose data
 * is used to print to file or reads data from the file into a {@link TaskList}.
 */
public class FileHandler {
    private TaskList taskList;

    /**
     * The constructor:
     * Takes a {@link TaskList}
     * @param taskList the {@link TaskList} to set the {@code taskList} variable
     */
    public FileHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Write the data in {@link TaskList} to the file at the path. Call {@link FileHandler#create(String)}
     * to check if the file exists and create the file if necessary. Uses a {@link PrintWriter} to print
     * the formatted tasks to the file.
     * @param path the path to the file
     * @return true if writing to file was successful or false
     */
    public boolean write(String path) {
        create(path);
        try(PrintWriter pw = new PrintWriter(path)) {
            pw.println(taskList.formattedTasks());
            System.out.println("Successfully saved");
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Check if a file exists at the path, create a new file if it does not. Create a new {@link File} using the {@code path}
     * and use a {@link Scanner} to read through the lines in the file. Read through all the lines,
     * if -1 is reached then the end of the file has been reached. Each line is split based on spaces
     * and built back up into a line using {@link StringBuilder}. The last element of the {@code lineSplit}
     * array can be assumed to be true or false. A new {@link Task} is created using the string from
     * {@link StringBuilder} and {@link Task#setCompleted(boolean)} is called to set completion of
     * the {@link Task} (the boolean is retrieved from the last element of {@code lineSplit} array).
     * @param path the path to the file
     * @return a new {@link TaskList}
     */
    public TaskList read(String path) {
        TaskList newTaskList = new TaskList();
        try {
            if (!exists(path)) {
                System.out.println("The file does not exist");
                create(path);
            }
            File f = new File(path);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.equals("-1")) {
                    break;
                }

                String[] lineSplit = line.split(" ");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lineSplit.length-1; i++) {
                    sb.append(lineSplit[i]).append(" ");
                }

                Task newTask = new Task(sb.toString().trim());
                newTask.setCompleted(Boolean.parseBoolean(lineSplit[lineSplit.length-1]));
                newTaskList.add(newTask);
            }
            System.out.println("Successfully loaded");
            return newTaskList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Checks if the file exists at the path. If it does not exists create a new file.
     * @param path the path to the file
     * @return true if new file is created or false
     */
    private boolean create(String path) {
        try {
            if (!exists(path)) {
                File f = new File(path);
                if (f.createNewFile()) {
                    System.out.println("File created: " + f.getName());
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Check if the file exists at the {@code path}
     * @param path the path to the file
     * @return true if file exists or false
     */
    public boolean exists(String path) {
        File f = new File(path);
        return f.isFile();
    }
}
