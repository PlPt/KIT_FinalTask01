package de.plpt.Connect6;

import java.util.Objects;

public class Player {

    //region varDef
    public static final Player NONE = new Player("**");
    private String name;

    //endregion

    //region constuctor
    public Player(String name) {
        this.name = name;
    }
    //endregion

    //region toString
    @Override
    public String toString() {
        return name;
    }
    //endregion

    //region equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }
    //endregion

    //region hashCode
    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
    //endregion
}
