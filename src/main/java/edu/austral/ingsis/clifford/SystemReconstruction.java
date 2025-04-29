package edu.austral.ingsis.clifford;

import java.util.List;
import java.util.Optional;

public class SystemReconstruction {

    public static Directory replaceInTree(Directory directory, List<String> path, Directory updatedSubtree) throws IllegalArgumentException {
        if (path.isEmpty()) return updatedSubtree;
        String currentSegment = path.getFirst();

        Optional<Directory> maybeNext = directory.getSubDirectory(currentSegment);
        if (maybeNext.isEmpty()) throw new IllegalStateException("Invalid path during update: " + currentSegment);

        Directory next = maybeNext.get();
        Directory updatedNext = replaceInTree(next, path.subList(1, path.size()), updatedSubtree);

        return directory.replace(updatedNext);
    }
}
