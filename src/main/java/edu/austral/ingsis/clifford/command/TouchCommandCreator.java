package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class TouchCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
        if (command.args().size() == 1) {
            return new TouchCommand(command.args().getFirst());
        }
        else{
            throw new IllegalArgumentException("No name provided");
        }
    }
}
