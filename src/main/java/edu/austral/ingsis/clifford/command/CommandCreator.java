package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public interface CommandCreator {

    Command create(CommandParser.ParseCommand command);
}
