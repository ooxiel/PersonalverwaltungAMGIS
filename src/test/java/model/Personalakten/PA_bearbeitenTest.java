package model.Personalakten;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PA_bearbeitenTest {
    //Objekt der zu testenden Klasse erzeugen
    PA_erstellen pae = new PA_erstellen();
    @Test
        //Pending Ordner sollte Leer sein
        //AktenFiles Ordner sollte mindestens den Pending Ordner beinhalten -> also nicht Leer
    void test_isEmpty() {
        assertAll(
                () -> assertTrue(pae.isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))),
                () -> assertFalse(pae.isEmpty(Path.of("src/main/resources/AktenFiles")))
        );
    }
}