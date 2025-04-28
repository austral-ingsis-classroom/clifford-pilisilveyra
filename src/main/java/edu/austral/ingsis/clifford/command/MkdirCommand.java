package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.SystemReconstructor;

import java.util.ArrayList;
import java.util.Optional;

public record MkdirCommand(String directoryName) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    if (directoryName.contains("/") || directoryName.contains(" ")) {
      return "Invalid directory name.";
    }

    Directory current = session.getCurrentDirectory();
    Directory newDirectory = new Directory(directoryName, new ArrayList<>());
    Directory newCurrentVersion = current.add(newDirectory);
    Directory updatedRoot = SystemReconstructor.replaceInTree(session.getRoot(), session.getCurrentPathSegments(), newCurrentVersion);
    session.update(updatedRoot);

    return "'" + directoryName + "' directory created";
  }

}
