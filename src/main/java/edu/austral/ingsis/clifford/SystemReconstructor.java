package edu.austral.ingsis.clifford;

import java.util.List;
import java.util.Optional;

public class SystemReconstructor {

    public static Directory replaceInTree(Directory dir, List<String> path, Directory updatedSubtree) {
        if (path.isEmpty()) return updatedSubtree;
        String currentSegment = path.get(0);

        Optional<Directory> maybeNext = dir.getSubDirectory(currentSegment);
        if (maybeNext.isEmpty()) throw new IllegalStateException("Invalid path during update: " + currentSegment);

        Directory next = maybeNext.get();
        Directory updatedNext = replaceInTree(next, path.subList(1, path.size()), updatedSubtree);

        return dir.replace(updatedNext);
    }
}
