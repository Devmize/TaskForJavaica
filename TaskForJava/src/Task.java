public class Task {

    private String name = null;
    private int id;
    private boolean status = false;

    public Task(String name) {
        this.name = name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void didTask() {
        this.status = true;
    }

}
