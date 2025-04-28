package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystemSession;

public  interface Command {

  String executeCommand(FileSystemSession session);
}
