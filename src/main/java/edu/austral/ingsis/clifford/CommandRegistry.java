package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.CdCommandCreator;
import edu.austral.ingsis.clifford.command.CommandCreator;
import edu.austral.ingsis.clifford.command.LsCommandCreator;
import edu.austral.ingsis.clifford.command.MkdirCommandCreator;
import edu.austral.ingsis.clifford.command.PwdCommandCreator;
import edu.austral.ingsis.clifford.command.RmCommandCreator;
import edu.austral.ingsis.clifford.command.TouchCommandCreator;

import java.util.Map;

public class CommandRegistry {

    public static Map<String, CommandCreator> createCommandMap() {
        return Map.of(
                "mkdir", new MkdirCommandCreator(),
                "touch", new TouchCommandCreator(),
                "ls", new LsCommandCreator(),
                "cd", new CdCommandCreator(),
                "pwd", new PwdCommandCreator(),
                "rm", new RmCommandCreator()
        );
    }
}
