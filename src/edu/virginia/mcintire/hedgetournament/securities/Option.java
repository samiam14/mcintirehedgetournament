package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;

import edu.virginia.mcintire.hedgetournament.risk.OptionRiskEngine;
import edu.virginia.mcintire.hedgetournament.risk.RiskEngine;

public class Option extends Security {
    public static enum Type {
	PUT, CALL
    }

    private double _strike;
    private Stock _underlier;
    private Date _expiration;
    private Type _type;

    public Option(String symbol, Stock underlier, Date expiration,
	    double strike, Type type) {
	super(symbol);
	_underlier = underlier;
	_strike = strike;
	_expiration = expiration;
	_type = type;
    }

    public Date getExpiration() {
	return _expiration;
    }

    @Override
    public RiskEngine<Option> getRiskEngine() {
	return OptionRiskEngine.getInstance();
    }

    public double getStrike() {
	return _strike;
    }

    public Type getType() {
	return _type;
    }

    public Stock getUnderlier() {
	return _underlier;
    }

}
