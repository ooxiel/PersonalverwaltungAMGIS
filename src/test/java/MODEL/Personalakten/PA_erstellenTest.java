package MODEL.Personalakten;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PA_erstellenTest {
    PA_erstellen pae = new PA_erstellen();
    @Test
    void test_isEmpty() throws IOException {
        assertAll(
                () -> assertTrue(pae.isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))),
                () -> assertFalse(pae.isEmpty(Path.of("src/main/resources/AktenFiles")))
        );
    }
}