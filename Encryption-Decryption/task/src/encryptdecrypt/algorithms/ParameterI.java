package encryptdecrypt.algorithms;

import encryptdecrypt.entity.Mode;

/**
 * {@link ParameterI} Describes the input of methode {@link AlgorithmsI#run}
 */
public interface ParameterI {

    Mode mode();

    String data();

    int key();

}
