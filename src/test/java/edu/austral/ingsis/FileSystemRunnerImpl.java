package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.FileSystemState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileSystemRunnerImpl implements FileSystemRunner {

  private final CommandParser commandParser;

  public FileSystemRunnerImpl() {
    Map<String, CommandCreator> commandMap = new HashMap<>();
    commandMap.put("mkdir", new MkdirCommandCreator());
    commandMap.put("touch", new TouchCommandCreator());
    commandMap.put("ls", new LsCommandCreator());
    commandMap.put("cd", new CdCommandCreator());
    commandMap.put("pwd", new PwdCommandCreator());
    commandMap.put("rm", new RmCommandCreator());

    this.commandParser = new CommandParser(commandMap);
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    FileSystemState state = new FileSystemState();
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
