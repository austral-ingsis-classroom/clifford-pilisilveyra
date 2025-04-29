package edu.austral.ingsis.clifford;

import java.util.List;
import java.util.Optional;

public class DirectoryNavigator {
    public static Optional<Directory> navigate(Directory root, List<String> pathSegments) {
        Directory current = root;
        for (String segment : pathSegments) {
            Optional<Directory> maybeSub = current.getSubDirectory(segment);
            if (maybeSub.isEmpty()) return Optional.empty();
            current = maybeSub.get();
        }
        return Optional.of(current);
    }
}