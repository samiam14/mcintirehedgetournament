package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.virginia.mcintire.hedgetournament.risk.RiskEngine;
import edu.virginia.mcintire.hedgetournament.risk.StockRiskEngine;
import edu.virginia.mcintire.hedgetournament.trading.Quote;

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

    public Map<Double, Option> getExpirationGroup(Date expiration) {
	return _optionChain.get(expiration);
    }

    public Map<Date, Map<Double, Option>> getOptionChain() {
	return _optionChain;
    }

    @Override
    public RiskEngine<Stock> getRiskEngine() {
	return StockRiskEngine.getInstance();
    }
    
    public double getVolatility() {
	return _volatility;
    }

}
