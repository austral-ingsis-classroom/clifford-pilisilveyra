package edu.austral.ingsis.clifford;

public record TouchCommand(String name) implements Command {

  @Override
  public String executeCommand(FileSystemState state) throws IllegalArgumentException {
    if (name.contains("/") || name.contains(" ")) {
      throw new IllegalArgumentException("Name cannot contain / neither spaces");
    }
    Directory currentDirectory = state.getCurrentDirectory();
    currentDirectory.add(new File(name));
    return "'" + name + "' file created";
  }
}
