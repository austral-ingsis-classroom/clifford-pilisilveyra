package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandRegistry;
import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.CommandRunner;
import edu.austral.ingsis.clifford.FileSystemRunner;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {

  CommandRunner runner = new CommandRunner();

  @Override
  public List<String> executeCommands(List<String> commands) {
    return runner.executeCommands(commands);
  }
}
