package edu.austral.ingsis.clifford.commandcreators;

import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.commands.CdCommand;
import edu.austral.ingsis.clifford.commands.Command;

public class CdCommandCreator implements CommandCreator {
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
