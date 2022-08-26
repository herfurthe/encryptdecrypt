package encryptdecrypt.algorithms;

import encryptdecrypt.entity.Mode;

/**
 * {@link AlgorithmsI} is the base class for following classes.
 * This classes transform data encoded as ascii code.
 * <ul>
 *     <li>{@link Shift}: Uses the caesar cipher for the lower alphabet</li>
 *     <li>{@link Unicode}: Uses the caesar cipher for all symbols </li>
 * </ul>
 */
public abstract class AlgorithmsI {
    protected final int start, end;

    public AlgorithmsI(int start, int end) {
        this.start = start;
        this.end = end;
    }

    protected int sign(int key) {
        if (key == 0)
            return 1;

        return (-1 * key) / key;
    }

    protected int normed(int key) {
        if (end - start == 0)
            return 0;

        return key % (end - start);
    }

    protected int setMode(Mode mode, int sign, int key) {
        if (Mode.DEC.equals(mode))
            sign *= -1;

        return sign * key;
    }

    protected boolean isLetter(int letter) {
        return (start <= letter) && (letter <= end);
    }

    /**
     * {@link #run} executes the algorithms white.
     * @param parameter provides all needed data.
     * @return the transformed data.
     */
    public abstract String run(ParameterI parameter);

}
