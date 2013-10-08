package edu.virginia.mcintire.hedgetournament.trading;

import edu.virginia.mcintire.hedgetournament.securities.Option;
import edu.virginia.mcintire.hedgetournament.securities.Security;

public class Transaction {
    public static enum Type {
	BUY, SELL, SELL_SHORT, EXERCISE, CASH_DIVIDEND
    }

    private int _size;
    private Security _security;
    private Type _type;
    private double _price;

    public Transaction(Security security, int size, Type type) {
	_security = security;
	_size = size;
	_type = type;
	setPrice(security.getMarketQuote());
    }

    public double getCost() {
	return _size * getPrice();
    }

    public double getPrice() {
	return _price;
    }

    public Security getSecurity() {
	return _security;
    }

    public int getSize() {
	return _size;
    }

    public Type getType() {
	return _type;
    }
    
    private void setPrice(Quote quote) {
	switch (_type) {
	case BUY:
	    _price = quote.getBid();
	    break;
	case SELL:
	case SELL_SHORT:
	    _price = quote.getAsk();
	    break;
	case EXERCISE:
	    Option option = (Option) _security;
	    _price = (option.getType() == Option.Type.PUT) ? quote.getAsk()
		    : quote.getBid();
	    break;
	case CASH_DIVIDEND:
	    // TODO Implement dividend schedule
	    _price = 0.0;
	    break;
	}
    }

}
