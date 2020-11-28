package expression;

import commands.CommandHandler;

public class VarNumber implements Expression
{
	private String variable;
	CommandHandler commandHandler;

	public VarNumber(String value, CommandHandler commandHandler)
	{
		this.variable = value;
		this.commandHandler = commandHandler;
	}

	public void setValue(String value)
	{
		this.variable = value;
	}

	@Override
	public double calculate()
	{
		if ((commandHandler.symbltablBind.containsKey(variable)))
		{
			return commandHandler.simulatorVars.get((commandHandler.symbltablBind.get(variable)));
		}

		return commandHandler.symbolTable.get(variable);
	}
}