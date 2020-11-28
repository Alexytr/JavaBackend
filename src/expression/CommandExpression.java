package expression;

import commands.Command;

public class CommandExpression implements Expression
{
	Command command;

	public CommandExpression(Command command)
	{
		this.command = command;
	}

	@Override
	public double calculate()
	{
		return 0;
	}
}