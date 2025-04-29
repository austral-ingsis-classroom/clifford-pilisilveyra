package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystemSession;

public record PwdCommand() implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    return session.getCurrentPath();
  }


}
