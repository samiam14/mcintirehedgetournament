package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Stock extends Security {
    private Map<Date, Map<Double, Option>> _optionChain;
    // TODO - Implement market volatility
    private double _volatility;
    
    public Stock(String symbol, double vol) {
	super(symbol);
	_optionChain = new HashMap<Date, Map<Double, Option>>();
	_volatility = vol;
    }

    public void addOption(Option newOption) throws Exception {
	Map<Double, Option> expirationGroup;
	if (newOption.getUnderlier().getSymbol() == _symbol) {
	    if (!_optionChain.containsKey(newOption.getExpiration())) {
		expirationGroup = new HashMap<Double, Option>();
		_optionChain.put(newOption.getExpiration(), expirationGroup);
	    } else {
		expirationGroup = _optionChain.get(newOption.getExpiration());
	    }
	    expirationGroup.put(newOption.getStrike(), newOption);
	} else {
	    // TODO - Implement exception for invalid option
	    throw new Exception();
	}
    }

    @Override
    public double calculateDelta(double rate, Date today) {
	return 1;
    }

    @Override
    public double calculateGamma(double rate, Date today) {
	return 0;
    }

    public Map<Double, Option> getExpirationGroup(Date expiration) {
	return _optionChain.get(expiration);
    }

    public Map<Date, Map<Double, Option>> getOptionChain() {
	return _optionChain;
    }
    
    public double getVolatility() {
	return _volatility;
    }

}
