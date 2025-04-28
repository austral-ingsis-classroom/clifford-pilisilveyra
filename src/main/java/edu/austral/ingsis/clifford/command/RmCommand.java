package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemNode;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.SystemReconstructor;

import java.util.Optional;

public record RmCommand(String name, RemoveParameter parameter) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) throws IllegalArgumentException {
    Directory currentDirectory = session.getCurrentDirectory();
    Optional<FileSystemNode> targetNode = currentDirectory.find(name);

    if (targetNode.isEmpty()) {
      return "No such file or directory";
    }

    if (targetNode.get().isDirectory() && !parameter.equals(RemoveParameter.RECURSIVE)) {
      return "cannot remove '" + name + "', is a directory";
    }

    Directory updatedDirectory = currentDirectory.remove(name);
    Directory newRoot = SystemReconstructor.replaceInTree(session.getRoot(), session.getCurrentPathSegments(), updatedDirectory);
    session.update(newRoot);
    session.setCurrentPath(session.getCurrentPathSegments());

    return "'" + name + "' removed";
  }
}
