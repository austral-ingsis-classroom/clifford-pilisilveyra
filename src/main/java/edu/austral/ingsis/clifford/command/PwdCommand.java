package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystemSession;

public record PwdCommand() implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    return session.getCurrentPath();
  }


}
