package commands;

import client.DataReaderServer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class IfCommand extends ConditionCommands implements Command {

	DataReaderServer server;
	CommandHandler commandHandler;

	public IfCommand(DataReaderServer server, CommandHandler commandHandler)
	{
		super(server, commandHandler);
		this.server = server;
		this.commandHandler = commandHandler;
	}

	@Override
	public void doCommand(String[] str, HashMap<String, Double> symbolTable)
	{
		String variable = str[1];
		String operator = str[2];
		String value = str[3];
		StringBuilder stringBuilder = new StringBuilder();

		for (String s : str)
		{
			stringBuilder.append(s + " ");
		}

		List<String[]> listOfCommands;
		listOfCommands = ConvertArgumentsToList(stringBuilder.toString());

		if (Condition(variable, operator, value))
		{
			for (String[] strings : listOfCommands)
			{
				strings = Arrays.stream(strings).filter(value1 -> !value1.isEmpty()).toArray(String[]::new);
				Command c = commandHandler.commandHashMap.get(strings[0].replace(",", ""));
				c.doCommand(strings, symbolTable);
			}
		}
	}
}