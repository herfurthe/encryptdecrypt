package encryptdecrypt;

import encryptdecrypt.algorithms.*;
import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * {@link Main} is a commandline tool that can transform date encoded with ascii.
 *  The caesar cipher is used to transform the data.
 *  Parameter:
 *  <ul>
 *      <li>-mode: Defines the mode of the cipher. Possible values enc = encrypted or dec = decrypted</li>
 *      <li>-key: Defines how far a symbol is shifted</li>
 *      <li>-alg: Defines the used cipher. Possible values shift = shifts only letters or unicode = shifts all symbols  </li>
 *      <li>-data: Defines the data that gets transformed</li>
 *      <li>-in: Defines the location a file. The content of this file gets transformed.</li>
 *      <li>-out: Defines where the transformed data gets written. Default is Standard.Out</li>
 *  </ul>
 */
public class Main {
    private final AlgorithmsFactory factory = new AlgorithmsFactory();
    private final Configuration conf;

    private Main(String[] args) {
        conf = new Configuration();
        Configuration.Cleaner cleaner = conf.parseArgs(args);
        AlgorithmsI alg = factory.create(conf.alg());
        String data = alg.run(conf);
        write(conf.out(), data);
        cleaner.clean();
    }

    private static void write(OutputStream stream, String data) {
        try (BufferedOutputStream writer = new BufferedOutputStream(stream)) {
            writer.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main(args);
    }

}
