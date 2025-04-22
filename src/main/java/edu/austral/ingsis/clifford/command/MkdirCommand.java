package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemState;
import edu.austral.ingsis.clifford.command.Command;

import java.util.ArrayList;

public record MkdirCommand(String directoryName) implements Command {
  @Override
  public String executeCommand(FileSystemState state) {

    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return "Invalid directory name.";
    }
    Directory current = state.getCurrentDirectory();
    Directory newDirectory = new Directory(directoryName, new ArrayList<>(), current);
    current.add(newDirectory);
    return "'" + directoryName + "' directory created";
  }
}
