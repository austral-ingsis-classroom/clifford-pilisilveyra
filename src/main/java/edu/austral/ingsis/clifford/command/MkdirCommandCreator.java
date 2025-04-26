package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class MkdirCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
        if (command.args().size() == 1) {
            return new MkdirCommand(command.args().getFirst());
        } else{
            throw new IllegalArgumentException("No directory name provided.");
        }
    }
}
