package MODEL.Personalakten;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PA_bearbeitenTest {
    PA_erstellen pae = new PA_erstellen();
    @Test
    void test_isEmpty() {
        assertAll(
                () -> assertTrue(pae.isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))),
                () -> assertFalse(pae.isEmpty(Path.of("src/main/resources/AktenFiles")))
        );
    }
}