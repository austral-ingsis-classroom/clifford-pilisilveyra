package edu.austral.ingsis.clifford.commandcreators;

import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.LsCommand;
import edu.austral.ingsis.clifford.commands.LsOrder;

public class LsCommandCreator implements CommandCreator {
  @Override
  public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
    if (command.args().isEmpty()) {
      return new LsCommand(LsOrder.UNORDERED);
    } else if (command.args().size() == 1 && isValidOrder(command)) {
      String order = command.args().get(0).substring("--ord=".length());
      return new LsCommand(parseOrder(order));
    } else {
      throw new IllegalArgumentException();
    }
  }

  private static boolean isValidOrder(CommandParser.ParseCommand command) {
    return command.args().get(0).startsWith("--ord=");
  }

  private static LsOrder parseOrder(String order) {
    return switch (order) {
      case "asc" -> LsOrder.ASC;
      case "desc" -> LsOrder.DESC;
      default -> throw new IllegalArgumentException("Orden inválido: " + order);
    };
  }
}
