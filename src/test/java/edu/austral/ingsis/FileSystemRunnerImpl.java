package edu.austral.ingsis;

import edu.austral.ingsis.clifford.Command;
import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.FileSystemState;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {
  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystemState state = new FileSystemState();
    List<String> results = new ArrayList<>();

    for (String line : commands) {
      try {
        Command command = CommandParser.parse(line);
        String output = command.executeCommand(state);
        results.add(output);
      } catch (Exception e) {
        results.add(e.getMessage());
      }
    }

    return results;
  }
}
