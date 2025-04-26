package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FileSystemSession {

  private final Directory root;

  private Directory currentDirectory;

  public FileSystemSession() {
    this.root = new Directory("/", new ArrayList<>(), Optional.empty());
    this.currentDirectory = root;
  }

  public Directory getRoot() {
    return root;
  }

  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  public void setCurrentDirectory(Directory directory) {
    this.currentDirectory = directory;
  }

  public String getCurrentPath() {
    List<String> path = new ArrayList<>();
    Optional<Directory> current = Optional.ofNullable(currentDirectory);
    while (current.isPresent() && current.get() != root) {
      path.add(current.get().name());
      current = current.get().parent();
    }
    Collections.reverse(path);
    return "/" + String.join("/", path);
  }
}
