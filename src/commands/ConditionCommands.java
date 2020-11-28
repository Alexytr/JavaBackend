package commands;

import expression.ShuntingYard;
import client.DataReaderServer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ConditionCommands
{
	DataReaderServer server;
	ShuntingYard shuntingYard;
	CommandHandler commandHandler;

	public ConditionCommands(DataReaderServer server, CommandHandler commandHandler)
	{
		this.server = server;
		shuntingYard = new ShuntingYard();
		this.commandHandler = commandHandler;
	}

	protected List<String[]> ConvertArgumentsToList(String string)
	{
		List<String> listOfCommands = new LinkedList<>();
		List<String> listOfLines = Arrays.asList(string.split("/n"));
		listOfCommands = listOfLines.subList(1, listOfLines.size() - 2);

		return listOfCommands.
				stream().
				map(line -> line.split("((?<=[a-zA-Z0-9={}])\\s(?=[a-zA-Z0-9={}(]))|[\\n\\r]+|((?<=[{}])|(?=[{}]))|((?<=[=])|(?=[=]))")).collect(Collectors.toList());
	}

	protected boolean Condition(String variable, String operator, String value)
	{
		if ("<".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() < shuntingYard.calc(value, commandHandler).calculate();
		}
		else if (">".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() > shuntingYard.calc(value, commandHandler).calculate();
		}
		else if ("<=".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() <= shuntingYard.calc(value, commandHandler).calculate();
		}
		else if (">=".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() >= shuntingYard.calc(value, commandHandler).calculate();
		}
		else if ("==".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() == shuntingYard.calc(value, commandHandler).calculate();
		}
		else if ("!=".equals(operator))
		{
			return shuntingYard.calc(variable, commandHandler).calculate() != shuntingYard.calc(value, commandHandler).calculate();
		}

		return false;
	}
}