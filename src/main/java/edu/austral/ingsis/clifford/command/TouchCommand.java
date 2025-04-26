package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystemSession;

public record TouchCommand(String name) implements Command {

  @Override
  public String executeCommand(FileSystemSession state) throws IllegalArgumentException {
    if (name.contains("/") || name.contains(" ")) {
      throw new IllegalArgumentException("Name cannot contain / neither spaces");
    }
    Directory currentDirectory = state.getCurrentDirectory();
    currentDirectory.add(new File(name));
    return "'" + name + "' file created";
  }
}
