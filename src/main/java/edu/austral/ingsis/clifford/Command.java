package edu.austral.ingsis.clifford;

public sealed interface Command
    permits MkdirCommand, CdCommand, LsCommand, RmCommand, TouchCommand, PwdCommand {

  String executeCommand(FileSystemState state);
}
