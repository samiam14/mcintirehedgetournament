package edu.virginia.mcintire.hedgetournament.securities;

import java.util.Date;

public class Option extends Security {
    public static enum Type {
	PUT, CALL
    }
    
    private static final double INVSQRT2PI = 0.398942280401432702863218082712;
    private static final double[] P = { 0.31938153, -0.356563782, 1.781477937, -1.821255978, 1.330274429 };

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

    @Override
    public double calculateDelta(double rate, Date today) {
	double d1, cd1, vol;
	vol = _underlier.getVolatility();
	
	int daysTilExpiration = (int)((_expiration.getTime() - today.getTime()) / 86400000);
	d1 = Math.log(_underlier.getMarketQuote().getMarkToMarket() / _strike);
	d1 += (rate + vol * vol / 2) * daysTilExpiration;
	d1 /= vol * Math.sqrt(daysTilExpiration);

	cd1 = cumulativeDistribution(d1);
	
	return (_type == Type.CALL) ? cd1 : cd1 - 1.0;
    }

    @Override
    public double calculateGamma(double rate, Date today) {
	double spot, d1, sd1, sqrtExpDays, vol;
	int daysTilExpiration = (int)((_expiration.getTime() - today.getTime()) / 86400000);
	
	vol = _underlier.getVolatility();
	spot = _underlier.getMarketQuote().getMarkToMarket();
	sqrtExpDays = Math.sqrt(daysTilExpiration);
	
	d1 = Math.log(spot / _strike);
	d1 += (rate + vol * vol / 2) * daysTilExpiration;
	d1 /= vol * sqrtExpDays;
	
	sd1 = standardDistribution(d1);
	
	return sd1 / (spot * vol * sqrtExpDays);
    }

    public Date getExpiration() {
	return _expiration;
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
    
    private double standardDistribution(double x) {
	return Math.exp(-0.5 * x * x) * INVSQRT2PI;
    }
    
    private double cumulativeDistribution(double x) {
	return cumulativeDistribution(x, standardDistribution(x));
    }

    private double cumulativeDistribution(double x, double sdx) {
	double t = 1.0 / (1.0 + 0.2316419 * Math.abs(x));
	double b = 0.0;
	for(int i = 0; i < P.length; i++) {
	    b += P[i] * t;
	    t *= t;
	}
	double cd = 1 - sdx * b;
	return (x < 0) ? 1 - cd : cd;
    }

}
