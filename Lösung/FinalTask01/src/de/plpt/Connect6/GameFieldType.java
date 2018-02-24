package de.plpt.Connect6;

public enum GameFieldType {
    /**
     * Represents the 'standard' GameFieldType
     */
    STANDARD,
    /**
     * Represents the 'torus' GameFieldType
     */
    TORUS;

    //region getValueByString

    /**
     * Returns a GameFieldType Value by String
     *
     * @param typeString String representation of Enum value
     * @return Enum type equal to String
     */
    public static GameFieldType getValueByString(String typeString) {
        for (GameFieldType type : GameFieldType.values()) {
            if (typeString.equals(type.toString().toLowerCase())) {
                return type;
            }
        }
        return null;
    }
    //endregion
}
