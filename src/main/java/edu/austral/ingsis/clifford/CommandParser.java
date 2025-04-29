package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commandcreators.CommandCreator;
import edu.austral.ingsis.clifford.commands.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandParser {

  private final Map<String, CommandCreator> commandMap;

  public CommandParser(Map<String, CommandCreator> commandMap) {
    this.commandMap = Map.copyOf(commandMap);
  }

  public Command parse(String input) throws IllegalArgumentException {
    ParseCommand result = getParseCommand(input);
    CommandCreator creator = commandMap.get(result.command());

    if (creator != null) {
      return creator.create(result);
    }

    throw new IllegalArgumentException("Sintaxis inválida para el comando: " + input);
  }

  private static ParseCommand getParseCommand(String input) {
    String[] parts = getSplitWhitespace(input);
    String command = parts[0];
    List<String> args = Arrays.asList(parts).subList(1, parts.length);
    return new ParseCommand(command, args);
  }

  public record ParseCommand(String command, List<String> args) {
  }

  private static String[] getSplitWhitespace(String input) {
    return input.trim().split("\\s+");
  }


}
