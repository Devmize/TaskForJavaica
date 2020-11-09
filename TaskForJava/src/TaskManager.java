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

    public void addTask(String name, boolean status) {
        tasks.put(count, new Task(name));
        tasks.get(count).setStatus(status);
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
        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            if (!t.getValue().getStatus()) {
                writer.write(t.getValue().getName().getBytes());
                writer.write(" false".getBytes());
                writer.write('\n');
            }
        }

        for (Map.Entry<Integer, Task> t : tasks.entrySet()) {
            if (t.getValue().getStatus()) {
                writer.write(t.getValue().getName().getBytes());
                writer.write(" true".getBytes());
                writer.write('\n');
            }
        }
    }

    public void loadTasks(String file) throws Exception {

        if (!file.contains(".txt")) {
            throw new Exception("Wrong format file.");
        }
        tasks.clear();
        count = 1;

        var reader = new BufferedReader(new FileReader(file));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.isEmpty()) {
                continue;
            }

            var key = currentLine.substring(0, currentLine.indexOf(" "));
            var value = Boolean.parseBoolean(currentLine.substring(currentLine.indexOf(" ") + 1,
                    currentLine.length()));
            addTask(key, value);
        }

    }

}
