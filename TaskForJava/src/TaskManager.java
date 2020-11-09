import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TaskManager {

    private HashMap<Integer, Task> tasks = new HashMap<>();

    int count = 1;
    public void addTask(String name) {
        tasks.put(count, new Task(name));
        count++;
    }

    public void printAllTask() {
        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            System.out.println(t.getKey());
            System.out.println(t.getValue().getName());
        }
        System.out.println("");
    }

    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void completeTask(int id) {
        tasks.get(id).didTask();
    }

    public void completedTask() {
        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            if (t.getValue().getStatus()) {
                System.out.println(t.getValue().getName());
            }
        }
        System.out.println("");
    }

    public void saveTasks(String file) throws Exception {

        if (!file.contains(".txt")) {
            throw new Exception("You can't write information to the file.");
        }

        var writer = new FileOutputStream(file);
        writer.write("Невыполненные:\n".getBytes());
        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            if (!t.getValue().getStatus()) {
                writer.write(t.getValue().getName().getBytes());
                writer.write('\n');
            }
        }

        writer.write("\nВыполненные:\n".getBytes());
        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            if (t.getValue().getStatus()) {
                writer.write(t.getValue().getName().getBytes());
                writer.write('\n');
            }
        }
    }

    public void loadTasks(String file) throws Exception {

        if (!file.contains(".txt")) {
            throw new Exception("Wrong format file.");
        }

        var reader = new BufferedReader(new FileReader(file));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.isEmpty()) {
                continue;
            }
            addTask(currentLine);
        }

    }

}
