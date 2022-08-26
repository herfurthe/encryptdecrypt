package encryptdecrypt.algorithms;

import encryptdecrypt.entity.Alg;


/**
 * {@link AlgorithmsFactory} provides a factory method
 * to create a new instance of class {@link AlgorithmsI}.
 */
public class AlgorithmsFactory {

    public AlgorithmsI create(Alg alg) {
        switch (alg) {
            case SHIFT:
                return new Shift();
            case UNICODE:
                return new Unicode();

            default:
                throw new IllegalStateException("following algorithms is not supported " + alg.name());
        }
    }

}
