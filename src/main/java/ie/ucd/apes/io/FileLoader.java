package ie.ucd.apes.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {
    public static List<String> getFileNames(String folderName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folderName);
        if (url == null) {
            throw new IOException();
        }
        String path = url.getPath();
        File[] files = new File(path).listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}
