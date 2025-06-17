package edu.austral.ingsis.clifford;

public record File(String name) implements FileSystemNode { // Leaf
    @Override
    public boolean isDirectory() {
        return false;
    }
}
