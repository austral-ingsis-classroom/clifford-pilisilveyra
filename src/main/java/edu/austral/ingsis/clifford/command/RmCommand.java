package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemState;

import java.util.Optional;

public record RmCommand(String name, RemoveParameter parameter) implements Command {
  @Override
  public String executeCommand(FileSystemState state) throws IllegalArgumentException {
    Directory currentDirectory = state.getCurrentDirectory();
    if (parameter.equals(RemoveParameter.RECURSIVE)) {
      Optional<Directory> targetDirectory = currentDirectory.getSubDirectory(name);
      if (targetDirectory.isPresent()) {
        currentDirectory.removeDirectory(targetDirectory.get());
      } else {
        throw new IllegalArgumentException("There is no such directory");
      }
    } else {
      Optional<FileSystem> targetNode = currentDirectory.find(name);
      if (targetNode.isPresent()) {
        if (currentDirectory.getSubDirectory(name).isEmpty()) {
          currentDirectory.removeFile((File) targetNode.get());
        } else {
          throw new IllegalArgumentException("cannot remove 't-bone', is a directory");
        }
      }
    }
    return "'" + name + "' removed";
  }
}
