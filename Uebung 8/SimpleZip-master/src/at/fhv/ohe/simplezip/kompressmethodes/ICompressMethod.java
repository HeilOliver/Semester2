package at.fhv.ohe.simplezip.kompressmethodes;

import java.io.File;

/**
 * Describes all Compress method.
 * <p>
 * Created by Oliver Heil on 31.05.2017.
 */
public interface ICompressMethod {

    /**
     * Compress all files with the given extension to the Outfile
     *
     * @param extension  - The extension for the file that should be compressed
     * @param outputFile - The compressed file
     */
    void compress(String extension, File outputFile);

    /**
     * Uncompress the given File
     *
     * @param path - The Path of the Compressed file
     */
    void uncompress(File path);
}
