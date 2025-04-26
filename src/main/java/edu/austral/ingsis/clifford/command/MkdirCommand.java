package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.ArrayList;
import java.util.Optional;

public record MkdirCommand(String directoryName) implements Command {
  @Override
  public String executeCommand(FileSystemSession state) {

    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return "Invalid directory name.";
    }
    Directory current = state.getCurrentDirectory();
    Directory newDirectory = new Directory(directoryName, new ArrayList<>(), Optional.of(current));
    current.add(newDirectory);
    return "'" + directoryName + "' directory created";
  }
}
