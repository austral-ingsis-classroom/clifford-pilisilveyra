package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandRunner;
import edu.austral.ingsis.clifford.FileSystemRunner;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {

  CommandRunner runner = new CommandRunner();

  @Override
  public List<String> executeCommands(List<String> commands) {
    return runner.executeCommands(commands);
  }
}
