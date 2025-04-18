package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record LsCommand(LsOrder order) implements Command {
  @Override
  public String executeCommand(FileSystemState state) {

    Directory currentDirectory = state.getCurrentDirectory();
    List<String> names = new ArrayList<>();
    for (FileSystem node : currentDirectory.nodes()) {
      names.add(node.name());
    }
    switch (order) {
      case ASC -> Collections.sort(names);
      case DESC -> Collections.sort(names, Collections.reverseOrder());
      case UNORDERED -> {}
    }
    return String.join(" ", names);
  }
}
