package at.fhv.ohe.simplezip.kompressmethodes;

import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarInputStream;
import org.kamranzafar.jtar.TarOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Describes the Gzip Compress method.
 * <p>
 * Created by Oliver Heil on 31.05.2017.
 */
public class Gzip implements ICompressMethod {


    @Override
    public void compress(String extension, File outputFile) {
        TarOutputStream tarOutputStream = null;
        FileInputStream fileInputStream = null;

        try {
            tarOutputStream = new TarOutputStream(new GZIPOutputStream(new FileOutputStream(outputFile)));
            File directory = new File(System.getProperty("user.dir"));
            File[] listOfFile = directory.listFiles(file -> file.getName().endsWith(extension));

            for (File file : listOfFile) {
                fileInputStream = new FileInputStream(file);
                tarOutputStream.putNextEntry(new TarEntry(file, file.getName()));

                int len;
                byte[] buffer = new byte[2048];
                while ((len = fileInputStream.read(buffer, 0, buffer.length)) > 0) {
                    tarOutputStream.write(buffer, 0, len);
                }
                tarOutputStream.flush();
                fileInputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    tarOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (tarOutputStream != null) {
                try {
                    tarOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void uncompress(File path) {
        TarInputStream tarInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            tarInputStream = new TarInputStream(new GZIPInputStream(new FileInputStream(path)));
            TarEntry tarEntry;

            while ((tarEntry = tarInputStream.getNextEntry()) != null) {
                fileOutputStream = new FileOutputStream(new File(tarEntry.getName()));

                int len;
                byte[] buffer = new byte[2048];
                while ((len = tarInputStream.read(buffer, 0, buffer.length)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }

                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (tarInputStream != null) {
                try {
                    tarInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
