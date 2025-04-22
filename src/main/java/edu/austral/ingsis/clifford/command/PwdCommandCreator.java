package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class PwdCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) {
        return new PwdCommand();
    }
}
