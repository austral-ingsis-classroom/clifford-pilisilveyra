package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystemState;
import edu.austral.ingsis.clifford.command.Command;

public record PwdCommand() implements Command {
  @Override
  public String executeCommand(FileSystemState state) {
    return state.getCurrentPath();
  }
}
