package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class RmCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
        if (command.args().size() == 1) return new RmCommand(command.args().get(0), RemoveParameter.UNRECURSIVE);
        if (command.args().size() == 2 && command.args().get(0).equals("--recursive")) {
            return new RmCommand(command.args().get(1), RemoveParameter.RECURSIVE);
        }
        else throw new IllegalArgumentException();
    }
}
