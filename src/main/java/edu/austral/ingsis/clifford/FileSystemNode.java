package edu.austral.ingsis.clifford;

public sealed interface FileSystemNode permits File, Directory {
  String name();

  boolean isDirectory();
}
