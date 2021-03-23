import ie.ucd.apes.entity.Constants;
import ie.ucd.apes.io.FileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FileLoaderTest {
    @Test
    public void testCharacterList() {
        try {
            Assertions.assertFalse(FileLoader.getFileNames(Constants.characterFolder).isEmpty());
        } catch (IOException ioException) {
            Assertions.fail("Cannot load character images");
        }
    }
}
