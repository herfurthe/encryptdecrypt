package encryptdecrypt.algorithms;


import encryptdecrypt.entity.Mode;

class Unicode extends AlgorithmsI {

    public Unicode() {
        super(0, 126);
    }


    @Override
    public String run(ParameterI parameter) {
        final StringBuilder builder = new StringBuilder();
        final Mode mode = parameter.mode();
        final String data = parameter.data();
        int key = parameter.key();
        int sign = sign(key);

        key = setMode(mode, sign, normed(sign * key));

        for (char letter : data.toCharArray()) {
            if (isLetter(letter)) {
                letter += key;
                if (letter < start) {
                    letter = (char) (end - (start - letter) + 1);
                } else if (end < letter) {
                    letter = (char) (start + (letter - end) - 1);
                }
            }
            builder.append(letter);
        }

        return builder.toString();
    }
}
