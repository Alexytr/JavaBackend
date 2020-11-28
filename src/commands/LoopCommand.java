package commands;

import client.DataReaderServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LoopCommand extends ConditionCommands implements Command
{
	DataReaderServer server;
	CommandHandler commandHandler;

	public LoopCommand(DataReaderServer server, CommandHandler commandHandler1) {
		
		super(server, commandHandler1);
		this.server = server;
		this.commandHandler = commandHandler1;
	}

	@Override
	public void doCommand(String[] stringsArray, HashMap<String, Double> symbolTable) {

		String[] tempArr = stringsArray[1].split(" ");
		String variable = tempArr[0];
		String operator = tempArr[1];
		String value = tempArr[2];
		StringBuilder stringBuilder = new StringBuilder();

		for (String s : stringsArray)
		{
			stringBuilder.append(s + " ");
		}

		List<String[]> listOfCommands;
		listOfCommands = ConvertArgumentsToList(stringBuilder.toString());

		while (Condition(variable, operator, value))
		{
			for (String[] strings : listOfCommands)
			{
				strings = Arrays.
						stream(strings).
						filter(value1 -> !value1.isEmpty()).toArray(String[]::new);

				Command command = commandHandler.commandHashMap.get(strings[0].trim());

				if (command == null)
				{
					command = commandHandler.commandHashMap.get("assignment");
				}

				command.doCommand(strings, symbolTable);
			}
		}
	}
}