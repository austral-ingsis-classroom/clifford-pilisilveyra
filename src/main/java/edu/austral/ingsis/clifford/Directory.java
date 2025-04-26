package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Directory(String name, List<FileSystemNode> nodes, Optional<Directory> parent)
    implements FileSystemNode {

  public void add(FileSystemNode node) throws IllegalArgumentException {
    if (nodes.contains(node)) {
      throw new IllegalArgumentException("Node already exists.");
    }
    nodes.add(node);
  }

  public void removeFile(File file) throws IllegalArgumentException {
    if (!nodes.contains(file)) {
      throw new IllegalArgumentException("File does not exist.");
    }
    Optional<FileSystemNode> targetNode = find(file.name());
    targetNode.ifPresent(fs -> {
      if (!fs.isDirectory()) {
        nodes.remove(file);
      } else {
        throw new IllegalArgumentException("There is no such file");
      }
    });
  }

  public void removeDirectory(Directory directory) throws IllegalArgumentException {
    if (!nodes.contains(directory)) {
      throw new IllegalArgumentException("Directory does not exist.");
    }

    for (FileSystemNode node : new ArrayList<>(directory.nodes())) {
      if (node instanceof Directory) {
        directory.removeDirectory((Directory) node);
      } else {
        directory.removeFile((File) node);
      }
    }

    nodes.remove(directory);
  }

  public Optional<FileSystemNode> find(String name) {
    for (FileSystemNode node : nodes) {
      if (Objects.equals(node.name(), name)) {
        return Optional.of(node);
      }
    }
    return Optional.empty();
  }

  public Optional<Directory> getSubDirectory(String name) {
    Optional<FileSystemNode> maybeNode = find(name);
    if (maybeNode.isEmpty()) return Optional.empty();
    FileSystemNode node = maybeNode.get();
    if (!node.isDirectory()) return Optional.empty();
    return Optional.of((Directory) node);
  }


}
