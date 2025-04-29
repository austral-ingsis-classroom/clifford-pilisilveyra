package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.File;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.SystemReconstruction;

public record TouchCommand(String name) implements Command {

  @Override
  public String executeCommand(FileSystemSession session) {
    if (name.contains("/") || name.contains(" ")) {
      return "Name cannot contain / neither spaces";
    }

    File newFile = new File(name);
    Directory currentDirectory = session.getCurrentDirectory();
    Directory updatedDirectory = currentDirectory.add(newFile);
    Directory newRoot = SystemReconstruction.replaceInTree(session.getRoot(), session.getCurrentPathSegments(), updatedDirectory);
    session.update(newRoot);

    return "'" + name + "' file created";
  }
}
