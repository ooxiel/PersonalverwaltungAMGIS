package model.personalakten;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PersonalakteEditModelTest {
    //Objekt der zu testenden Klasse erzeugen
    PersonalakteCreateModel pae = new PersonalakteCreateModel();
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