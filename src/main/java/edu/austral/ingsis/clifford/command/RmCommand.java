package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystemNode;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.Optional;

public record RmCommand(String name, RemoveParameter parameter) implements Command {
  @Override
  public String executeCommand(FileSystemSession state) throws IllegalArgumentException {
    Directory currentDirectory = state.getCurrentDirectory();
    if (parameter.equals(RemoveParameter.RECURSIVE)) {
      return removeRecursivelyDirectory(currentDirectory);
    }
    else {
      return removeFile(currentDirectory);
    }
  }

  private String removeFile(Directory currentDirectory) {
    Optional<FileSystemNode> targetNode = currentDirectory.find(name);
    if (targetNode.isEmpty()) {
      return "No such file ";
    }
    FileSystemNode node = targetNode.get();
    if (node.isDirectory()) {
      return "cannot remove '" + name + "', is a directory";
    }

    currentDirectory.removeFile((File) node);
    return "'" + name + "' removed";
  }

  private String removeRecursivelyDirectory(Directory currentDirectory) {
    Optional<Directory> targetDirectory = currentDirectory.getSubDirectory(name);

    if (targetDirectory.isEmpty()) {
      return "There is no such directory";
    }

    currentDirectory.removeDirectory(targetDirectory.get());
    return "'" + name + "' removed";

  }
}
