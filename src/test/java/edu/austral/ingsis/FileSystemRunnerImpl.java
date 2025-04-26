package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandRegistry;
import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemRunnerImpl implements FileSystemRunner {

  private final CommandParser commandParser;

  public FileSystemRunnerImpl() {
    this.commandParser = new CommandParser(CommandRegistry.createCommandMap());
  }


  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystemSession state = new FileSystemSession();
    List<String> results = new ArrayList<>();

    for (String line : commands) {
      try {
        Command command = commandParser.parse(line);
        String output = command.executeCommand(state);
        results.add(output);
      } catch (Exception e) {
        results.add(e.getMessage());
      }
    }

    return results;
  }
}
