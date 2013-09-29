package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.virginia.mcintire.hedgetournament.trading.Quote;

public abstract class Security
{
    protected String _symbol;
    protected Map<Date, Quote> _quotes;

    public Security(String symbol)
    {
	_quotes = new HashMap<Date, Quote>();
	_symbol = symbol;
    }

    public void addQuote(Date date, Quote quote)
    {
	_quotes.put(date, quote);
    }
    
    public abstract double calculateDelta();

    public abstract double calculateGamma();

    public Quote getQuote(Date date)
    {
	return _quotes.get(date);
    }
    
    public Map<Date, Quote> getQuotes()
    {
	return _quotes;
    }
    
    public String getSymbol()
    {
	return _symbol;
    }
}
