package model.personalakten;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PersonalakteCreateModelTest {
    //Objekt der zu testenden Klasse erzeugen
    PersonalakteCreateModel pae = new PersonalakteCreateModel();
    @Test
    //Pending Ordner sollte Leer sein
    //AktenFiles Ordner sollte mindestens den Pending Ordner beinhalten -> also nicht Leer
    void test_isEmpty() throws IOException {
        assertAll(
                () -> assertTrue(pae.isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))),
                () -> assertFalse(pae.isEmpty(Path.of("src/main/resources/AktenFiles")))
        );
    }
}