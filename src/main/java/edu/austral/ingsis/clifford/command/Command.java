package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.FileSystemState;

public sealed interface Command
    permits MkdirCommand, CdCommand, LsCommand, RmCommand, TouchCommand, PwdCommand {

  String executeCommand(FileSystemState state);
}
