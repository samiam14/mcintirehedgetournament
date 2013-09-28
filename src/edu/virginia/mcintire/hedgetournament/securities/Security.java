package edu.virginia.mcintire.hedgetournament.securities;

public abstract class Security
{
    protected String _symbol;

    public Security(String symbol)
    {
	_symbol = symbol;
    }

    public abstract double calculateDelta();

    public abstract double calculateGamma();

    public String getSymbol()
    {
	return _symbol;
    }
}
