public class Monster {

    private String description;

    private String name;

    private int armor;

    /**
     * @param name
     *            This monster's name.
     * @param armor
     *            The power of this monster.
     * @param description
     *            A description of this monster.
     */
    public Monster(String name, int armor, String description) {
        this.name = name;
        this.armor = armor;
        this.description = description;
    }

    /** Returns a description of this monster. */
    public String getDescription() {
        return description;
    }

    /** Returns this monster's name. */
    public String getName() {
        return name;
    }

    /** Returns the value of this monster. */
    public int getPower() {
        return armor;
    }

    /** Returns the monster's armor. */
    public int getArmor(){
        return armor;}
}
