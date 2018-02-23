package de.plpt.Connect6;

public enum GameFieldType {
    STANDARD, TORUS;

    public static GameFieldType getValueByString(String val) {
        for (GameFieldType type : GameFieldType.values()) {
            if (val.equals(type.toString().toLowerCase())) {
                return type;
            }
        }
        return null;
    }
}
