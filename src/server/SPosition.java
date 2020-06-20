package server;

public class SPosition {

	public int row;
	public int col;
	
	public SPosition(int row,int col) {
		this.row = row;
		this.col =col;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;

		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		return this.equals((SPosition)obj);
	}
	
	public boolean equals(SPosition s)
	{
		return (this.row == s.row && this.col == s.col) ? true : false;
	}

	public int getRow()
	{
		return row;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getCol()
	{
		return col;
	}

	public void setCol(int col)
	{
		this.col = col;
	}
	
	
	
}
