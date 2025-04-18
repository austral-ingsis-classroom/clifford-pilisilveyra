package edu.austral.ingsis.clifford;

public record PwdCommand() implements Command {
  @Override
  public String executeCommand(FileSystemState state) {
    return state.getCurrentPath();
  }
}
