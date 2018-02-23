package de.plpt.Connect6;

import java.util.Objects;

public class Cell {

    //region varDef
    private Player player = Player.NONE;
    private int columnIndex;
    private int rowIndex;
    //endregion

    //region cell
    public Cell(int columnIndex, int rowIndex) {
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
    }
    //endregion

    //region getPlayer
    public Player getPlayer() {
        return player;
    }
    //endregion

    //region setPlayer
    public void setPlayer(Player player) {
        this.player = player;
    }
    //endregion

    //region isEmpty
    public boolean isEmpty() {
        return player.equals(Player.NONE);
    }
    //endregion

    //region getPosition
    public String getPosition() {
        return String.format("(r: %s; c: %s)", rowIndex, columnIndex);
    }
    //endregion

    //region equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return columnIndex == cell.columnIndex &&
                rowIndex == cell.rowIndex;
    }
    //endregion

    //region hashCode
    @Override
    public int hashCode() {

        return Objects.hash(columnIndex, rowIndex);
    }
    //endregion
}
