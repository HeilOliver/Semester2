package at.fhv.ohe.simplezip;

import at.fhv.ohe.simplezip.kompressmethodes.Gzip;
import at.fhv.ohe.simplezip.kompressmethodes.ICompressMethod;
import at.fhv.ohe.simplezip.kompressmethodes.Zip;

import java.io.File;

/**
 * Describes the entry point of the SimpleZip.
 * It parse also the commandline commands.
 * <p>
 * Created by Oliver Heil on 31.05.2017.
 */
public class RunMe {

    private static void printHelp() {
        System.out.println("Help Simple Zip");
        System.out.println("\n-f Format for Pack or Unpack");
        System.out.println("\t <zip>");
        System.out.println("\t <gzip>");
        System.out.println("\n-p Pack with the given Format");
        System.out.println("\t <extension>");
        System.out.println("\t <archivename>");
        System.out.println("\n-u Unpack with the given Format");
        System.out.println("\t <archivename>");
        System.out.println("\n\nExample");
        System.out.println("\t SimpleZip -f zip -p txt example.zip");
    }

    public static void main(String[] args) {
        ICompressMethod compressMethod = null;

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "--FORMAT":
                case "-f":
                    args[i + 1] = args[i + 1].toLowerCase();

                    switch (args[i + 1]) {
                        case "zip":
                            compressMethod = new Zip();
                            break;

                        case "gzip":
                            compressMethod = new Gzip();
                            break;

                        default:
                            System.err.println("Wrong Format");
                            return;
                    }
                    i += 1;
                    break;

                case "--HELP":
                case "-h":
                    printHelp();
                    return;

                case "--PACK":
                case "-p":
                    if (compressMethod != null) {
                        if (!args[i + 1].startsWith(".")) {
                            args[i + 1] = "." + args[i + 1];
                        }
                        compressMethod.compress(args[i + 1], new File(args[i + 2]));
                        System.out.println("Done");
                        return;
                    }

                case "--UNPACK":
                case "-u":
                    if (compressMethod != null) {
                        compressMethod.uncompress(new File(args[i + 1]));
                        System.out.println("Done");
                        return;
                    }

                default:
                    System.err.println("Wrong Parameter");
                    return;
            }

        }
        System.err.println("You need Arguments :(");
    }
}
