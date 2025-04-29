package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commands.Command;
import java.util.ArrayList;
import java.util.List;

public class CommandRunner implements FileSystemRunner {

  private final CommandParser commandParser;

  public CommandRunner() {
    this.commandParser = new CommandParser(CommandRegistry.createCommandMap());
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystemSession currentSession = new FileSystemSession();
    List<String> results = new ArrayList<>();

    for (String line : commands) {
      try {
        Command command = commandParser.parse(line);
        String result = command.executeCommand(currentSession);
        results.add(result);
      } catch (Exception e) {
        results.add("Error: " + e.getMessage());
      }
    }

    return results;
  }
}
