package edu.virginia.mcintire.hedgetournament.securities;

public class Stock extends Security
{
	public Stock(String symbol)
	{
		super(symbol);
	}

	@Override
	public double calculateDelta()
	{
		return 1;
	}

	@Override
	public double calculateGamma()
	{
		return 0;
	}

}