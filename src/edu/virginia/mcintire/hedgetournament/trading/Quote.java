package edu.virginia.mcintire.hedgetournament.trading;

import java.util.Date;

public class Quote
{
    private double _bid, _ask;
    private Date _date;
    
    public Quote(double bid, double ask, Date date)
    {
	_bid = bid;
	_ask = ask;
	_date = date;
    }
    
    public double getBid()
    {
	return _bid;
    }
    
    public double getAsk()
    {
	return _ask;
    }
    
    public Date getDate()
    {
	return _date;
    }
    
    public double getMarkToMarket()
    {
	return (_bid + _ask) / 2;
    }

}
