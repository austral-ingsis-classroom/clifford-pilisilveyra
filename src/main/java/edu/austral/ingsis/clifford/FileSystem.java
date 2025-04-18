package edu.austral.ingsis.clifford;

public sealed interface FileSystem permits File, Directory {
  String name();

  default boolean isDirectory() {
    return this instanceof Directory;
  }
}
