package de.plpt.Connect6;

import java.util.Objects;

public class CellPosition {

    //region varDef
    private int rowIndex;
    private int cellIndex;
    //endregion

    //region constructor

    /**
     * Initializes a new CellPosition object with its coordinates
     *
     * @param rowIndex  Index of Row
     * @param cellIndex Index of Column
     */
    public CellPosition(int rowIndex, int cellIndex) {
        this.rowIndex = rowIndex;
        this.cellIndex = cellIndex;
    }
    //endregion

    //region isAcceeptedPosition

    /**
     * Checks whether the current Position is acceptable in current Mode with current gameSize
     *
     * @param type     current GameType (standard/torus)
     * @param gameSize size of current game Matrix
     * @return true if Position is accepted, false otherwise
     */
    public boolean isAcceptedPosition(GameFieldType type, int gameSize) {

        switch (type) {
            case STANDARD:
                return rowIndex < gameSize && rowIndex >= 0 && cellIndex >= 0 && cellIndex < gameSize;

            case TORUS:
                return true;

            default:
                return false;
        }
    }
    //endregion

    //region convertToNormal

    /**
     * Converts a Position outside of gameField to a Position inside gameField.
     * Must be used for convert tours coordinates to acceptable Coordinates
     *
     * @param gameSize Site of gameField
     * @return new CellPosition which represents same position but is inside GameField
     */
    public CellPosition convertToNormal(int gameSize) {

        int tmpRowIndex = rowIndex % gameSize;
        int tmpCellIndex = cellIndex % gameSize;

        if (tmpRowIndex < 0) {
            tmpRowIndex = gameSize + tmpRowIndex;
        }
        if (tmpCellIndex < 0) {
            tmpCellIndex = gameSize + tmpCellIndex;
        }

        return new CellPosition(tmpRowIndex, tmpCellIndex);
    }
    //endregion

    //region getRowIndex

    /**
     * Retuns the rowIndex fro Position
     *
     * @return rowIndex of Position
     */
    public int getRowIndex() {
        return rowIndex;
    }
    //endregion

    //region getCellIndex

    /**
     * Returns the cellIndex of Position
     *
     * @return cellIndex of position
     */
    public int getCellIndex() {
        return cellIndex;
    }
    //endregion

    //region equals

    /**
     * Indicates whether a CellPosition object is "equal to" this one.
     *
     * @param o Object to check equality
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex
                && cellIndex == that.cellIndex;
    }
    //endregion

    //region hashCode

    /**
     * Returns hashCode for this object
     *
     * @return HashCode for CellPosition
     */
    @Override
    public int hashCode() {

        return Objects.hash(rowIndex, cellIndex);
    }
    //endregion

    //region toString

    /**
     * Returns a String representation of CellPosition
     *
     * @return String repr. of CellPosition
     */
    @Override
    public String toString() {
        return String.format("(r: %s; c: %s)", rowIndex, cellIndex);
    }
    //endregion

}
