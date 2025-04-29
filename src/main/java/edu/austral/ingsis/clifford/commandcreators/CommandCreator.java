package edu.austral.ingsis.clifford.commandcreators;

import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.commands.Command;

public interface CommandCreator {

  Command create(CommandParser.ParseCommand command);
}
