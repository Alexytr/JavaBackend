package interpreter;

import java.util.Arrays;
import commands.AssignmentCommand;
import commands.Command;
import commands.CommandHandler;
import client.Client;

public class Parser {
    CommandHandler commandHandler;
    Client client;
    boolean loopCommand;
    boolean ifCommand;

    public Parser(CommandHandler commandHandler, Client client) {
        this.commandHandler = commandHandler;
        this.client = client;
        loopCommand = false;
        ifCommand = false;
    }

    public void Parser(String[] input) {

        String[] stringArray = Arrays.
                stream(input).
                filter(value -> !value.isEmpty()).
                filter((string) -> !string.equals(" ")).
                toArray(String[]::new);

        Command command = commandHandler.commandHashMap.get(stringArray[0]);

        if (command == null && stringArray[1].equals("="))
        {
            command = new AssignmentCommand(commandHandler, client);
        }
        else if ((command.getClass().getSimpleName() + "").equals("DisconnectCommand"))
        {
            command.doCommand(stringArray, commandHandler.symbolTable);
            return;
        }

        switch ((command.getClass().getSimpleName() + ""))
        {
            case "LoopCommand":
                loopCommand = true;
                break;

            case "IfCommand":
                ifCommand = true;
                break;
        }

        if (stringArray[stringArray.length - 2].contains("}") && loopCommand == true)
        {
            loopCommand = false;
            command = commandHandler.commandHashMap.get("while");
        }
        else if (stringArray[stringArray.length - 2].contains("}") && ifCommand == true)
        {
            ifCommand = false;
            command = commandHandler.commandHashMap.get("if");
        }

        if (loopCommand == false && ifCommand == false)
        {
            command.doCommand(stringArray, commandHandler.symbolTable);
        }
    }
}