import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class FileLoaderTest {
    @Test
    public void testCharacterList() {
        try {
            System.out.println("Testing character list loading...");
            System.out.println(FileLoader.getFileNames(Constants.CHARACTER_FOLDER));
        } catch (IOException | URISyntaxException ioException) {
            Assertions.fail("Cannot load character images");
        }
    }
}
