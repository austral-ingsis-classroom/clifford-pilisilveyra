package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record CdCommand(String directoryInfo) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {
    Optional<List<String>> maybeNewPath = resolvePath(session, directoryInfo);

    if (maybeNewPath.isEmpty()) {
      return "'" + directoryInfo + "' directory does not exist";
    }

    session.setCurrentPath(maybeNewPath.get());
    return "moved to directory '" + getLastSegment(maybeNewPath.get()) + "'";
  }

  private Optional<List<String>> resolvePath(FileSystemSession state, String path) {
    List<String> base = path.startsWith("/") ? new ArrayList<>() : new ArrayList<>(state.getCurrentPathSegments());
    String[] parts = path.split("/");

    Directory current = state.getRoot();
    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) continue;
      if (part.equals("..")) {
        if (!base.isEmpty()) base.remove(base.size() - 1); // sube un nivel
        continue;
      }

      base.add(part);
      Optional<Directory> maybeSub = findDirectoryBySegments(state.getRoot(), base);
      if (maybeSub.isEmpty()) return Optional.empty();
      current = maybeSub.get();
    }

    return Optional.of(base);
  }

  private Optional<Directory> findDirectoryBySegments(Directory root, List<String> segments) {
    Directory current = root;
    for (String segment : segments) {
      Optional<Directory> maybeSub = current.getSubDirectory(segment);
      if (maybeSub.isEmpty()) return Optional.empty();
      current = maybeSub.get();
    }
    return Optional.of(current);
  }

  private String getLastSegment(List<String> segments) {
    if (segments.isEmpty()) return "/";
    return segments.get(segments.size() - 1);
  }
}
