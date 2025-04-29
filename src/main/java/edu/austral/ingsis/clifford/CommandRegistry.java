package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.commandcreators.CdCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.CommandCreator;
import edu.austral.ingsis.clifford.commandcreators.LsCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.MkdirCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.PwdCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.RmCommandCreator;
import edu.austral.ingsis.clifford.commandcreators.TouchCommandCreator;

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
