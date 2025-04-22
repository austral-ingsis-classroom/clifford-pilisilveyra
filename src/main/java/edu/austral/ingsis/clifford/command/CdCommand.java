package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemState;

import java.util.Optional;

public record CdCommand(String directoryInfo) implements Command {
  @Override
  public String executeCommand(FileSystemState state) {

    Directory targetDirectory = targetDirectory(state, directoryInfo);
    if (targetDirectory == null) {
      return "'" + directoryInfo + "' directory does not exist";
    }
    state.setCurrentDirectory(targetDirectory);
    return "moved to directory '" + (targetDirectory.name()) + "'";
  }

  private Directory targetDirectory(FileSystemState fileSystem, String directory) {

    Directory current =
        directory.startsWith("/") ? fileSystem.getRoot() : fileSystem.getCurrentDirectory();
    String[] parts = directory.split("/");

    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) {
        continue;
      } else if (part.equals("..")) {
        if (current.parent() != null) {
          current = current.parent();
        }
      } else {
        Optional<Directory> subDirectory = current.getSubDirectory(part);
        if (subDirectory.isPresent()) {
          current = subDirectory.get();
        } else {
          return null;
        }
      }
    }
    return current;
  }
}
