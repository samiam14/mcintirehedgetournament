package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.virginia.mcintire.hedgetournament.trading.Quote;

public abstract class Security {
    protected String _symbol;
    protected Map<Date, Quote> _quotes;
    protected Quote _marketQuote;

    public Security(String symbol) {
	_quotes = new HashMap<Date, Quote>();
	_symbol = symbol;
    }
    
    public Security(String symbol, List<Quote> historicalQuotes) {
	_symbol = symbol;
	_quotes = new HashMap<Date, Quote>();
	for(Quote q : historicalQuotes) {
	    _quotes.put(q.getDate(), q);
	}
    }

    public void addQuote(Quote quote) {
	if(quote.getDate().after(_marketQuote.getDate())) {
	    _marketQuote = quote;
	}
	_quotes.put(quote.getDate(), quote);
    }

    public abstract double calculateDelta(double rate, Date today);

    public abstract double calculateGamma(double rate, Date today);

    public Quote getQuote(Date date) {
	return _quotes.get(date);
    }
    
    public Quote getMarketQuote() {
	return _marketQuote;
    }

    public Map<Date, Quote> getQuotes() {
	return _quotes;
    }

    public String getSymbol() {
	return _symbol;
    }

    public boolean equals(Security sec) {
	return sec.getSymbol() == _symbol;
    }
}
