package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemNode;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.Optional;

public record CdCommand(String directoryInfo) implements Command {
  @Override
  public String executeCommand(FileSystemSession state) {
    Optional<Directory> maybeTarget = targetDirectory(state, directoryInfo);

    if (maybeTarget.isEmpty()) {
      return "'" + directoryInfo + "' directory does not exist";
    }

    Directory target = maybeTarget.get();
    state.setCurrentDirectory(target);
    return "moved to directory '" + target.name() + "'";
  }

  private Optional<Directory> targetDirectory(FileSystemSession state, String directory) {
    Directory current = startingDirectory(state, directory);
    String[] parts = getParts(directory);

    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) {
        continue;
      }

      if (part.equals("..")) {
        current = current.parent().orElse(current);
        continue;
      }

      Optional<Directory> maybeSub = current.getSubDirectory(part);
      if (maybeSub.isEmpty()) return Optional.empty();

      current = maybeSub.get();
    }

    return Optional.of(current);
  }

  private static String[] getParts(String directory) {
    return directory.split("/");
  }

  private static Directory startingDirectory(FileSystemSession state, String directory) {
        return directory.startsWith("/") ? state.getRoot() : state.getCurrentDirectory();
  }
}
