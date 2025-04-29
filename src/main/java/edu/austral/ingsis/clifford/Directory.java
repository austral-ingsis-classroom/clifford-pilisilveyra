package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public record Directory(String name, List<FileSystemNode> nodes)
        implements FileSystemNode {

  public Directory {
    nodes = List.copyOf(nodes);
  }

  public Directory add(FileSystemNode node) {
    if (nodes.contains(node)) {
      throw new IllegalArgumentException("Node already exists.");
    }

    List<FileSystemNode> newNodes = new ArrayList<>(nodes);
    newNodes.add(node);
    return new Directory(name, List.copyOf(newNodes));
  }

  public Directory remove(String dirName) {
    Optional<FileSystemNode> targetNodeOptional = find(dirName);
    if (targetNodeOptional.isEmpty()) {
      throw new IllegalArgumentException("Node does not exist: " + name);
    }

    List<FileSystemNode> newNodes = new ArrayList<>(nodes);
    newNodes.remove(targetNodeOptional.get());
    return new Directory(this.name, List.copyOf(newNodes));
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


  public Directory replace(FileSystemNode updatedNode) {
    List<FileSystemNode> newNodes = new ArrayList<>();
    for (FileSystemNode node : nodes) {
      if (node.name().equals(updatedNode.name())) {
        newNodes.add(updatedNode);
      } else {
        newNodes.add(node);
      }
    }
    return new Directory(name, newNodes);
  }


}
