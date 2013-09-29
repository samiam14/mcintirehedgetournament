package edu.virginia.mcintire.hedgetournament.trading;

public class Quote
{
    private double _bid, _ask;
    
    public Quote(double bid, double ask)
    {
	_bid = bid;
	_ask = ask;
    }
    
    public double getBid()
    {
	return _bid;
    }
    
    public double getAsk()
    {
	return _ask;
    }

}
