package server;

public class State<T>{
	private T sValue;
	private int sCost;
	private State<T> father;
	
	public State(T sValue)
	{
		this.sValue = sValue;
		this.sCost = Integer.MAX_VALUE;
		this.father = null;
	}
	
	public State<T> getFather()
	{
		return father;
	}

	public int getsCost()
	{
		return sCost;
	}

	public T getsValue()
	{
		return sValue;
	}
	
	public void setFather(State<T> father)
	{
		this.father = father;
	}

	public void setsCost(int sCost)
	{
		this.sCost = sCost;
	}

	public void setsValue(T sValue)
	{
		this.sValue = sValue;
	}
	
	public boolean equals(State<T> state)
	{
		return sValue.equals(state.sValue);
	}
	
	//Override
	@Override
	public int hashCode()
	{
		return (this.sValue + "").hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		State<T> stateObject = (State<T>) obj;
		return this.equals(stateObject);
	}
	
	@Override
	public String toString()
	{
		return this.sValue + " ";
	}
}
