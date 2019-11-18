package View;

import Model.Commands.Command;
import Model.Commands.ExitCommand;
import Model.ProgramException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class TextMenu {
    private Map<String, Command> commands;
    TextMenu(){ commands=new HashMap<>(); }
    void addCommand(Command c){ commands.put(c.getKey(),c);}
    private void printMenu(){
        for(Command com : commands.values()){
            String line=String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    void show() throws Exception{
        Scanner scanner=new Scanner(System.in);
        while(true){
            printMenu();
            System.out.printf("Input the option: ");
            String key=scanner.nextLine();
            Command com=commands.get(key);
            if (com==null)
                throw new Exception("Invalid command!");
            com.execute();
        }
    }
}