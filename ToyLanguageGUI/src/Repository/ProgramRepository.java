package Repository;

import Model.Commands.Command;

import java.util.HashMap;
import java.util.Map;

public class ProgramRepository {
    private Map<String, Command> commands;

    public ProgramRepository(){ commands=new HashMap<>(); }

    public void addCommand(Command c){ commands.put(c.getKey(),c);}

    public Map<String, Command> getCommands(){
        return commands;
    }
}
