package edu.austral.ingsis.clifford.commandcreators;

import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.commands.Command;
import edu.austral.ingsis.clifford.commands.PwdCommand;

public class PwdCommandCreator implements CommandCreator {
  @Override
  public Command create(CommandParser.ParseCommand command) {
    return new PwdCommand();
  }
}
