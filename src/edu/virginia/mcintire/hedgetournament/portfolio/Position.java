package edu.virginia.mcintire.hedgetournament.portfolio;

import edu.virginia.mcintire.hedgetournament.securities.Security;
import edu.virginia.mcintire.hedgetournament.trading.Transaction;

public class Position {
    private Security _security;
    private int _quantity;
    
    public Position(Security sec, int initialQuantity) {
	_security = sec;
	_quantity = initialQuantity;
    }
    
    public void adjustPosition(Transaction trans) {
	if(trans.getSecurity().equals(_security)) {
	    _quantity += trans.getSize();
	}
    }
    
    public Security getSecurity() {
	return _security;
    }
    
    public int getQuantity() {
	return _quantity;
    }
    
    public double getMarkToMarketValue() {
	return _quantity * _security.getMarketQuote().getMarkToMarket();
    }
}
