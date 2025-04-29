package edu.austral.ingsis.clifford.commands;

import edu.austral.ingsis.clifford.Directory;
import edu.austral.ingsis.clifford.FileSystemNode;
import edu.austral.ingsis.clifford.FileSystemSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record LsCommand(LsOrder order) implements Command {
  @Override
  public String executeCommand(FileSystemSession session) {

    Directory currentDirectory = session.getCurrentDirectory();
    List<String> names = new ArrayList<>();
    for (FileSystemNode node : currentDirectory.nodes()) {
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
