package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandParser;
import edu.austral.ingsis.clifford.commandcreators.CdCommandCreator;

import edu.austral.ingsis.clifford.commandcreators.CommandCreator;
import edu.austral.ingsis.clifford.commandcreators.LsCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.MkdirCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.PwdCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.RmCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.TouchCommandCreator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommandSyntaxisVerificationTest {

    @Test
    void testCdCommandCorrect() {
        CommandCreator creator = new CdCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("cd", List.of("folder"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testCdCommandIncorrect() {
        CommandCreator creator = new CdCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("cd", List.of());
        assertThrows(IllegalArgumentException.class, () -> creator.create(parseCommand));
    }

    @Test
    void testLsCommandCorrectUnordered() {
        CommandCreator creator = new LsCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("ls", List.of());
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testLsCommandCorrectOrderedAsc() {
        CommandCreator creator = new LsCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("ls", List.of("--ord=asc"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testLsCommandIncorrect() {
        CommandCreator creator = new LsCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("ls", List.of("--sort=name"));
        assertThrows(IllegalArgumentException.class, () -> creator.create(parseCommand));
    }

    @Test
    void testMkdirCommandCorrect() {
        CommandCreator creator = new MkdirCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("mkdir", List.of("newfolder"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testMkdirCommandIncorrect() {
        CommandCreator creator = new MkdirCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("mkdir", List.of());
        assertThrows(IllegalArgumentException.class, () -> creator.create(parseCommand));
    }

    @Test
    void testPwdCommandAlwaysCorrect() {
        CommandCreator creator = new PwdCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("pwd", List.of());
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testRmCommandCorrectUnrecursive() {
        CommandCreator creator = new RmCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("rm", List.of("file.txt"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testRmCommandCorrectRecursive() {
        CommandCreator creator = new RmCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("rm", List.of("--recursive", "folder"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testRmCommandIncorrect() {
        CommandCreator creator = new RmCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("rm", List.of("folder", "--recursive"));
        assertThrows(IllegalArgumentException.class, () -> creator.create(parseCommand));
    }

    @Test
    void testTouchCommandCorrect() {
        CommandCreator creator = new TouchCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("touch", List.of("file.txt"));
        assertDoesNotThrow(() -> creator.create(parseCommand));
    }

    @Test
    void testTouchCommandIncorrect() {
        CommandCreator creator = new TouchCommandCreator();
        CommandParser.ParseCommand parseCommand = new CommandParser.ParseCommand("touch", List.of());
        assertThrows(IllegalArgumentException.class, () -> creator.create(parseCommand));
    }
}
