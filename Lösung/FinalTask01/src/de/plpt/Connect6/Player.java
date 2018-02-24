package de.plpt.Connect6;

import java.util.Objects;

public class Player {

    //region varDef
    /**
     * Defines a 'default' Player as Placeholder when a Cell is empty
     */
    public static final Player NONE = new Player("**");
    private String name;

    //endregion

    //region constuctor

    /**
     * Initializes a new Player with it's PlayerName
     *
     * @param name Name of Player
     */
    public Player(String name) {
        this.name = name;
    }
    //endregion

    //region toString

    /**
     * Returns String representation of Player (Name)
     *
     * @return String representation of Player
     */
    @Override
    public String toString() {
        return name;
    }
    //endregion

    //region equals

    /**
     * Indicates whether a Player object is equal to this one
     *
     * @param o Object to check Player equality
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
    //endregion

    //region hashCode

    /**
     * Returns a hashCode for a Player object
     *
     * @return hasCode of object
     */
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
    //endregion
}
