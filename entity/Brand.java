package entity;
import java.util.List;


public class Brand {
    private int id;
    private String name;
    private List<Console> consoles;

    public Brand() {}

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
        this.consoles = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Console> getConsoles() {
        return consoles;
    }

    public void addConsole(Console console) {
        consoles.add(console);
    }

    public void removeConsole(Console console) {
        consoles.remove(console);
    }

    @Override
    public String toString() {
        return "Brand [id=" + id + ", name=" + name + ", consoles=" + consoles + "]";
    }
}
