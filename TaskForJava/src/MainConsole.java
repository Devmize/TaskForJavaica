import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainConsole {
    public static void main(String[] args) {

        try {
            TaskManager manager = new TaskManager();
            String command = null;
            boolean status = true;
            while (status) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                command = reader.readLine();
                int id;
                String tempInfo;
                switch (command) {
                    case "add" -> {
                        tempInfo = reader.readLine();
                        manager.addTask(tempInfo);
                    }
                    case "all" -> manager.printAllTask();
                    case "delete" -> {
                        id = Integer.parseInt(reader.readLine());
                        manager.deleteTask(id);
                    }
                    case "complete" -> {
                        id = Integer.parseInt(reader.readLine());
                        manager.completeTask(id);
                    }
                    case "completed" -> manager.completedTask();
                    case "save" -> {
                        tempInfo = reader.readLine();
                        manager.saveTasks(tempInfo);
                    }
                    case "load" -> {
                        tempInfo = reader.readLine();
                        manager.loadTasks(tempInfo);
                    }
                    case "exit" -> status = false;
                    default -> throw new Exception("Unknown command.");
                }
            }
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

    }
}
