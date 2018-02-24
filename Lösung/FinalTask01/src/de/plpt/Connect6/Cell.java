package de.plpt.Connect6;

import java.util.Objects;

public class Cell {

    //region varDef
    private Player player = Player.NONE;
    private CellPosition position;
    //endregion

    //region cell

    /**
     * Initializes a new Cell Instance with it's position
     *
     * @param rowIndex    rowIndex of Cell
     * @param columnIndex columnIndex of Cell
     */
    public Cell(int rowIndex, int columnIndex) {
        position = new CellPosition(rowIndex, columnIndex);
    }
    //endregion

    //region getPlayer

    /**
     * Returns the player who 'owned' the cell
     *
     * @return owner Player of cell
     */
    public Player getPlayer() {
        return player;
    }
    //endregion

    //region setPlayer

    /**
     * Sets the Player owner of the cell
     *
     * @param player Player to set as owner
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    //endregion

    //region isEmpty

    /**
     * Checks whether the cell is empty --> has no owner
     *
     * @return true if the cell has no owner, false otherwise
     */
    public boolean isEmpty() {
        return player.equals(Player.NONE);
    }
    //endregion

    //region getPosition

    /**
     * Returns the current cell position
     *
     * @return Position of cell
     */
    public CellPosition getPosition() {
        return position;
    }
    //endregion

    //region equals

    /**
     * Check if another object is equal to this Cell
     *
     * @param o object to check quality
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return this.position.equals(cell.getPosition());
    }
    //endregion

    //region hashCode
    /*
    Generates a new hasCode for a Cell
     */
    @Override
    public int hashCode() {

        return Objects.hash(position);
    }
    //endregion
}
