package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSystemState {

  private Directory root;

  private Directory currentDirectory;

  public FileSystemState() {
    this.root = new Directory("/", new ArrayList<>(), null);
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
    Directory dir = currentDirectory;
    while (dir != null && dir != root) {
      path.add(dir.name());
      dir = dir.parent();
    }
    Collections.reverse(path);
    return "/" + String.join("/", path);
  }
}
