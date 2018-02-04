package at.fhv.ohe.simplezip.kompressmethodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Describes the Zip Compress method.
 * <p>
 * Created by Oliver Heil on 31.05.2017.
 */
public class Zip implements ICompressMethod {


    @Override
    public void compress(String extension, File outputFile) {
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;

        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(outputFile));
            File directory = new File(System.getProperty("user.dir"));
            File[] listOfFile = directory.listFiles(file -> file.getName().endsWith(extension));

            for (File file : listOfFile) {
                fileInputStream = new FileInputStream(file);
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));

                int len;
                byte[] buffer = new byte[2048];
                while ((len = fileInputStream.read(buffer, 0, buffer.length)) > 0) {
                    zipOutputStream.write(buffer, 0, len);
                }
                fileInputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void uncompress(File path) {
        ZipInputStream zipInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            zipInputStream = new ZipInputStream(new FileInputStream(path));
            ZipEntry zipEntry;

            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                fileOutputStream = new FileOutputStream(new File(zipEntry.getName()));
                if (zipEntry.isDirectory()) {
                    zipInputStream.closeEntry();
                    break;
                }

                int len;
                byte[] buffer = new byte[2048];
                while ((len = zipInputStream.read(buffer, 0, buffer.length)) > 0) {
                    fileOutputStream.write(buffer, 0, len);
                }
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    zipInputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
