package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.SystemReconstructor;

public record TouchCommand(String name) implements Command {

  @Override
  public String executeCommand(FileSystemSession session) {
    if (name.contains("/") || name.contains(" ")) {
      return "Name cannot contain / neither spaces";
    }

    File newFile = new File(name);
    Directory currentDirectory = session.getCurrentDirectory();
    Directory updatedDirectory = currentDirectory.add(newFile);
    Directory newRoot = SystemReconstructor.replaceInTree(session.getRoot(), session.getCurrentPathSegments(), updatedDirectory);
    session.update(newRoot);

    return "'" + name + "' file created";
  }
}
