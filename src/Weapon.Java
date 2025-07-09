public class Weapon {

    /** A description of this weapon. */
    private String description;

    /** This weapon's name. */
    private String name;

    /** This weapon's value. */
    private int value;

    /** This weapon's damage. */
    private int damage;
    /**
     * @param name
     *            This treasure's name.
     * @param description
     *            A description of this weapon.
     * @param damage
     *            Value of weapon
     */

    public Weapon(String name, int damage, String description) {
        this.name = name;
        this.damage = damage;
        this.description = description;
    }

    /** Returns a description of this Weapon. */
    public String getDescription() {
        return description;
    }

    /** Returns this monster's name. */
    public String getName() {
        return name;
    }

    public int getDamage() {return damage;}
}
