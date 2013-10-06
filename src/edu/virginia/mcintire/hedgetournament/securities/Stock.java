package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Stock extends Security {
    private Map<Date, Map<Double, Option>> _optionChain;

    public Stock(String symbol) {
	super(symbol);
	_optionChain = new HashMap<Date, Map<Double, Option>>();
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
	    throw new Exception();
	}
    }

    @Override
    public double calculateDelta() {
	return 1;
    }

    @Override
    public double calculateGamma() {
	return 0;
    }

    public Map<Double, Option> getExpirationGroup(Date expiration) {
	return _optionChain.get(expiration);
    }

    public Map<Date, Map<Double, Option>> getOptionChain() {
	return _optionChain;
    }

}
