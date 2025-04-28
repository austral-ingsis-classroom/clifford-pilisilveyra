package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FileSystemSession {

  private Directory root;
  private List<String> currentPathSegments;

  public FileSystemSession() {
    this.root = new Directory("/", new ArrayList<>());
    this.currentPathSegments = List.of(); // estamos en "/"
  }

  public Directory getRoot() {
    return root;
  }

  public Directory getCurrentDirectory() {
    Directory current = root;
    for (String segment : currentPathSegments) {
      Optional<Directory> maybeSub = current.getSubDirectory(segment);
      if (maybeSub.isEmpty()) {
        throw new IllegalStateException("Invalid path segment: " + segment);
      }
      current = maybeSub.get();
    }
    return current;
  }

  public void setCurrentPath(List<String> newPathSegments) {
    this.currentPathSegments = List.copyOf(newPathSegments);
  }

  public String getCurrentPath() {
    if (currentPathSegments.isEmpty()) return "/";
    return "/" + String.join("/", currentPathSegments);
  }

  public List<String> getCurrentPathSegments() {
    return currentPathSegments;
  }

  public void update(Directory updatedRoot) {
    this.root = updatedRoot;
  }

}
