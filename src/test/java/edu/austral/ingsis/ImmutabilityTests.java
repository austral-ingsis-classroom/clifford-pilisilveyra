package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class ImmutabilityTests {

  @Test
  public void immutableDirectoriesForAddition() {
    Directory directoryTest = new Directory("prueba", new ArrayList<>());
    Directory newDirectory = directoryTest.add(new File("testFile"));
    assertEquals(
        0, directoryTest.nodes().size(), "El directorio original debería permanecer sin cambios.");

    assertEquals(
        1, newDirectory.nodes().size(), "El nuevo directorio debería contener un archivo.");
    assertEquals(
        "testFile", newDirectory.nodes().get(0).name(), "El archivo debería llamarse 'testFile'.");
  }

  @Test
  public void immutableDirectoriesForRemoval() {
    Directory directoryTest = new Directory("prueba", new ArrayList<>());
    Directory newDirectory = directoryTest.add(new File("testFile"));
    Directory lastVersionesDirectory = newDirectory.remove("testFile");
    assertEquals(1, newDirectory.nodes().size(), "El directorio deberia seguir teniendo el nodo");

    assertEquals(
        0,
        lastVersionesDirectory.nodes().size(),
        "El nuevo directorio debería no contener el file");
  }
}
