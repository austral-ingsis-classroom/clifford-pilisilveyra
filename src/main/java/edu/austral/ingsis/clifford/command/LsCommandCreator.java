package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandParser;

public class LsCommandCreator implements CommandCreator{
    @Override
    public Command create(CommandParser.ParseCommand command) throws IllegalArgumentException {
        if (command.args().isEmpty()) return new LsCommand(LsOrder.UNORDERED);
        else if (command.args().size() == 1 && command.args().get(0).startsWith("--ord=")) {
            String order = command.args().get(0).substring("--ord=".length());
            return new LsCommand(parseOrder(order));
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    private static LsOrder parseOrder(String order) {
        return switch (order) {
            case "asc" -> LsOrder.ASC;
            case "desc" -> LsOrder.DESC;
            default -> throw new IllegalArgumentException("Orden inválido: " + order);
        };
    }
}
