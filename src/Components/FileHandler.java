package Components;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {
    private TaskList taskList;

    public FileHandler(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean write(String path) {
        create(path);
        try(PrintWriter pw = new PrintWriter(path)) {
            pw.println(taskList.formattedTasks());
            return true;
        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }

    public TaskList read(String path) {
        TaskList newTaskList = new TaskList();
        try {
            if (!exists(path)) {
                System.out.println("The file does not exist");
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
                    sb.append(lineSplit[i] + " ");
                }
                Task newTask = new Task(sb.toString().trim());
                newTask.setCompleted(Boolean.parseBoolean(lineSplit[lineSplit.length-1]));
                newTaskList.add(newTask);
            }
            return newTaskList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

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

    public boolean exists(String path) {
        File f = new File(path);
        if (f.isFile()) {
            return true;
        }
        return false;
    }
}
