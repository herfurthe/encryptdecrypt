package encryptdecrypt.entity;

/**
 * Defines the names of an algorithms
 */
public enum Alg {
    UNICODE,
    SHIFT;


    public static Alg lowerOf(String value) throws IllegalAccessException {
        return Alg.valueOf(value.toUpperCase());
    }
}
