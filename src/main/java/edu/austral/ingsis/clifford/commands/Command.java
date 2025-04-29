package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.FileSystemSession;

public interface Command {

  String executeCommand(FileSystemSession session);
}
