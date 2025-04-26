package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class CdCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
        if (command.args().size() == 1) {
            return new CdCommand(command.args().getFirst());
        }
        else {
            throw new IllegalArgumentException("Incorrect amount of arguments");
        }

    }
}
