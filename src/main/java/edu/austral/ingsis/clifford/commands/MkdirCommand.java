package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.SystemReconstruction;
import java.util.ArrayList;

public record MkdirCommand(String directoryName) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return "Invalid directory name.";
    }

    Directory current = session.getCurrentDirectory();
    Directory newDirectory = new Directory(directoryName, new ArrayList<>());
    Directory newCurrentVersion = current.add(newDirectory);
    Directory updatedRoot =
        SystemReconstruction.replaceInTree(
            session.getRoot(), session.getCurrentPathSegments(), newCurrentVersion);
    session.update(updatedRoot);

    return "'" + directoryName + "' directory created";
  }
}
