package edu.austral.ingsis.clifford;

import java.util.Arrays;
import java.util.List;

public class CommandParser {

  public static Command parse(String input) {
    String[] parts = input.trim().split("\\s+");
    String command = parts[0];
    List<String> args = Arrays.asList(parts).subList(1, parts.length);

    switch (command) {
      case "mkdir":
        if (args.size() == 1) {
          return new MkdirCommand(args.get(0));
        }
        break;
      case "touch":
        if (args.size() == 1) {
          return new TouchCommand(args.get(0));
        }
        break;
      case "ls":
        if (args.isEmpty()) return new LsCommand(LsOrder.UNORDERED);
        if (args.size() == 1 && args.get(0).startsWith("--ord=")) {
          String order = args.get(0).substring("--ord=".length());
          return new LsCommand(parseOrder(order));
        }
        break;
      case "cd":
        if (args.size() == 1) return new CdCommand(args.get(0));
        break;
      case "pwd":
        return new PwdCommand();
      case "rm":
        if (args.size() == 1) return new RmCommand(args.get(0), RemoveParameter.UNRECURSIVE);
        if (args.size() == 2 && args.get(0).equals("--recursive")) {
          return new RmCommand(args.get(1), RemoveParameter.RECURSIVE);
        }
        break;
      default:
        throw new IllegalArgumentException("Comando desconocido: " + command);
    }
    throw new IllegalArgumentException("Sintaxis inválida para el comando: " + input);
  }

  private static LsOrder parseOrder(String order) {
    return switch (order) {
      case "asc" -> LsOrder.ASC;
      case "desc" -> LsOrder.DESC;
      default -> throw new IllegalArgumentException("Orden inválido: " + order);
    };
  }
}
