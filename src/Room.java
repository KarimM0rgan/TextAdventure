import java.util.ArrayList;

public class Room {

    // Returns room name
    private String name;

    // Returns room description
    private String description;

    // List of what is the room
    private ArrayList<String> exits;

    // List of directions to other rooms
    private ArrayList<Room> neighbors;

    // The available treasure
    private Treasure treasure;

    // The available weapon
    private Weapon weapon;

    // The available Monster
    private Monster monster;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.neighbors = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

    public void addNeighbor(String direction, Room neighbor){
        exits.add(direction);
        neighbors.add(neighbor);
    }

    public String exitList() {
        return "[" + String.join(", ", exits) + "]";
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public Monster getMonster(){
        return monster;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    public Treasure getTreasure(){
        return treasure;
    }

    public Room getNeighbor(String direction){
        int index = exits.indexOf(direction);
        if (index != -1) {
            return neighbors.get(index);
        }
        return null; //
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }
}
