package edu.virginia.mcintire.hedgetournament.trading;

import java.util.Collection;
import java.util.HashSet;

import edu.virginia.mcintire.hedgetournament.booking.Position;

/**
 * The Trade class is designed to group various trading strategies under a logical
 * group. Pair trades, options spreads, and other strategies may be implemented
 * through extensions of this class.
 * 
 * @author Sam Eberspacher
 * 
 */

public class Trade {
    private static int _globalTradeId = 0;
    
    private Collection<Position> _positions;
    private int _tradeId;
    
    public Trade() {
	_positions = new HashSet<Position>();
	_tradeId = _globalTradeId++;
    }
    
    public Trade(Collection<Position> initialPositions) {
	_positions = new HashSet<Position>(initialPositions);
	_tradeId = _globalTradeId++;
    }
    
    public double getMarkToMarketValue() {
	double totalValue = 0.0;
	for(Position pos : _positions) {
	    totalValue += pos.getMarkToMarketValue();
	}
	return totalValue;
    }
    
    public int getTradeId() {
	return _tradeId;
    }
}
