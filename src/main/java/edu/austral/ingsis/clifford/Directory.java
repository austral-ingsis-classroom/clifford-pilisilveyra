package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Directory(String name, List<FileSystem> nodes, Directory parent)
    implements FileSystem {

  public void add(FileSystem node) throws IllegalArgumentException {
    if (nodes.contains(node)) {
      throw new IllegalArgumentException("Node already exists.");
    }
    nodes.add(node);
  }

  public void removeFile(File file) throws IllegalArgumentException {
    if (!nodes.contains(file)) {
      throw new IllegalArgumentException("File does not exist.");
    }
    Optional<FileSystem> targetFile = find(file.name());
    targetFile.ifPresent(fs -> {
      if (fs instanceof File) {
        nodes.remove(file);
      } else {
        throw new IllegalArgumentException("There is no such file");
      }
    });
    if (targetFile.isPresent()) {
      if (targetFile.get() instanceof File) {
        nodes.remove(file);
      } else {
        throw new IllegalArgumentException("There is no such file");
      }
    }
  }

  public void removeDirectory(Directory directory) throws IllegalArgumentException {
    if (!nodes.contains(directory)) {
      throw new IllegalArgumentException("Directory does not exist.");
    }

    for (FileSystem node : new ArrayList<>(directory.nodes())) {
      if (node instanceof Directory) {
        directory.removeDirectory((Directory) node);
      } else {
        directory.removeFile((File) node);
      }
    }

    nodes.remove(directory);
  }

  public Optional<FileSystem> find(String name) {
    for (FileSystem node : nodes) {
      if (Objects.equals(node.name(), name)) {
        return Optional.of(node);
      }
    }
    return Optional.empty();
  }

  public Optional<Directory> getSubDirectory(String name) {
    for (FileSystem node : nodes) {
      if (Objects.equals(node.name(), name)) {
        if (node.isDirectory()) {
          return Optional.of((Directory) node);
        }
      }
    }
    return Optional.empty();
  }
}
