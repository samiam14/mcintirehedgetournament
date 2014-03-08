package edu.virginia.mcintire.hedgetournament.booking;

import java.util.ArrayList;
import java.util.List;

import edu.virginia.mcintire.hedgetournament.trading.Trade;

/**
 * The Book class is an organizational class for Trades. It is expected this
 * class would be used to separate the initial positions to be hedged, the
 * automated hedging positions and any manual trades executed by the user.
 * 
 * @author Sam Eberspacher
 * 
 */

public class Book {
    private List<Trade> _trades;
    private String _name;

    public Book(String bookName) {
	_name = bookName;
	_trades = new ArrayList<Trade>();
    }

    public Book(String bookName, List<Trade> initialTrades) {
	_name = bookName;
	_trades = initialTrades;
    }
    
    public void addTrade(Trade tradeToAdd) {
	_trades.add(tradeToAdd);
    }

    public double getMarkToMarketValue() {
	double sum = 0.0;
	for (Trade trade : _trades) {
	    sum += trade.getMarkToMarketValue();
	}
	return sum;
    }

    public String getName() {
	return _name;
    }
    
    public List<Trade> getTrades() {
	return _trades;
    }
    
    public void removeTrade(Trade tradeToRemove) {
	
    }
}
