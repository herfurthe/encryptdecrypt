package encryptdecrypt.entity;

/**
 * Defines the modes of an algorithms
 */
public enum Mode {
    DEC,
    ENC;

    public static Mode lowerOf(String string)throws IllegalAccessException {
        return Mode.valueOf(string.toUpperCase());
    }

}
