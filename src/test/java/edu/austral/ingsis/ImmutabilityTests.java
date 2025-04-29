package edu.austral.ingsis;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImmutabilityTests {

    @Test
    public void immutableDirectoriesForAddition(){
        Directory directoryTest = new Directory("prueba", new ArrayList<>());
        Directory newDirectory = directoryTest.add(new File("testFile"));
        assertEquals(0,directoryTest.nodes().size(), "El directorio original debería permanecer sin cambios.");

        assertEquals(1, newDirectory.nodes().size(), "El nuevo directorio debería contener un archivo.");
        assertEquals("testFile", newDirectory.nodes().getFirst().name(), "El archivo debería llamarse 'testFile'.");
    }

    @Test
    public void immutableDirectoriesForRemoval(){
        Directory directoryTest = new Directory("prueba", new ArrayList<>());
        Directory newDirectory = directoryTest.add(new File("testFile"));
        Directory lastVersionesDirectory = newDirectory.remove("testFile");
        assertEquals(1,newDirectory.nodes().size(), "El directorio deberia seguir teniendo el nodo");

        assertEquals(0, lastVersionesDirectory.nodes().size(), "El nuevo directorio debería no contener el file");
    }


}
