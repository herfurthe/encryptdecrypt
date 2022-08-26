package encryptdecrypt;

import encryptdecrypt.algorithms.ParameterI;
import encryptdecrypt.entity.*;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;


/**
 * {@link Configuration} defines the default configuration of the commandline tool.
 * If a user provides any parameter offer the commandline this class can parse them.
 */
public class Configuration implements ParameterI {
    public interface Cleaner {
        void clean();
    }

    //Default values;
    private Alg alg = Alg.SHIFT;
    private Mode mode = Mode.ENC;
    private OutputStream out = System.out;
    private String data = "";
    private int key = 0;
    private boolean toClear = false;

    /**
     * {@link #parseArgs} parses the parameter provided offer the commandline.
     * @param args provided parameter
     * @return {@link Cleaner} is used to close a {@link OutputStream}
     */
    public Cleaner parseArgs(String[] args) {
        for (int i = 0; i < args.length; ) {
            switch (args[i]) {
                case Flag.IN:
                    setIn(args[++i]);
                    break;

                case Flag.OUT:
                    setOut(args[++i]);
                    break;

                case Flag.ALG:
                    setAlg(args[++i]);
                    break;

                case Flag.KEY:
                    setKey(args[++i]);
                    break;

                case Flag.MODE:
                    setMode(args[++i]);
                    break;

                case Flag.DATA:
                    data = args[++i];
                    break;
            }
            i++;
        }

        return this::clean;
    }

    private void clean() {
        try {
            if (toClear)
                out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setMode(String mode) {
        try {
            this.mode = Mode.lowerOf(mode);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setKey(String key) {
        try {
            this.key = Integer.parseInt(key);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void setAlg(String alg) {
        try {
            this.alg = Alg.lowerOf(alg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setOut(String path) {
        try {
            out = new FileOutputStream(Path.of(path).toFile());
            toClear = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setIn(String p) {
        Path path = Path.of(p);
        if (! path.toFile().exists())
            return;
        try (Stream<String> stream = Files.lines(path)){
            data =  stream.collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int key() {
        return key;
    }

    public Alg alg() {
        return alg;
    }

    public Mode mode() {
        return mode;
    }

    public OutputStream out() {
        return out;
    }

    public String data() {
        return data;
    }

    public boolean toClear() {
        return toClear;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "alg=" + alg +
                ", mode=" + mode +
                ", out=" + out +
                ", data='" + data + '\'' +
                ", key=" + key +
                ", toClear=" + toClear +
                '}';
    }
}
