package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.DirectoryNavigator;
import edu.austral.ingsis.clifford.FileSystemSession;
import edu.austral.ingsis.clifford.PathResolver;
import java.util.List;
import java.util.Optional;

public record CdCommand(String directoryInfo) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    List<String> resolvedPath =
        PathResolver.resolve(session.getCurrentPathSegments(), directoryInfo);
    Optional<Directory> maybeDirectory =
        DirectoryNavigator.navigate(session.getRoot(), resolvedPath);

    if (maybeDirectory.isEmpty()) {
      return "'" + directoryInfo + "' directory does not exist";
    }

    session.setCurrentPath(resolvedPath);
    return "moved to directory '" + getLastSegment(resolvedPath) + "'";
  }

  private String getLastSegment(List<String> segments) {
    if (segments.isEmpty()) return "/";
    return segments.get(segments.size() - 1);
  }
}
