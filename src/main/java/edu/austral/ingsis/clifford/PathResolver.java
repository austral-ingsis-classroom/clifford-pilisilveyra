package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class PathResolver {
    public static List<String> resolve(List<String> basePath, String inputPath) {
        List<String> resolved = inputPath.startsWith("/") ? new ArrayList<>() : new ArrayList<>(basePath);
        String[] parts = inputPath.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) continue;
            if (part.equals("..")) {
                if (!resolved.isEmpty()) resolved.removeLast();
            } else {
                resolved.add(part);
            }
        }

        return resolved;
    }
}
