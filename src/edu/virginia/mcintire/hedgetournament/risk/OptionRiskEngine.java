package edu.virginia.mcintire.hedgetournament.risk;

import java.util.Date;

import edu.virginia.mcintire.hedgetournament.securities.Option;
import edu.virginia.mcintire.hedgetournament.securities.Option.Type;

public class OptionRiskEngine implements RiskEngine<Option> {

    private static final double INVSQRT2PI = 0.398942280401432702863218082712;
    private static final double[] P = { 0.31938153, -0.356563782, 1.781477937, -1.821255978, 1.330274429 };

    private static OptionRiskEngine _instance = new OptionRiskEngine();

    private OptionRiskEngine() {
    }

    @Override
    public double calculateDelta(Option security, double rate, Date today) {
	double d1, cd1, vol;
	vol = security.getUnderlier().getVolatility();
	
	int daysTilExpiration = (int)((security.getExpiration().getTime() - today.getTime()) / 86400000);
	d1 = Math.log(security.getUnderlier().getMarketQuote().getMarkToMarket() / security.getStrike());
	d1 += (rate + vol * vol / 2) * daysTilExpiration;
	d1 /= vol * Math.sqrt(daysTilExpiration);

	cd1 = cumulativeDistribution(d1);
	
	return (security.getType() == Type.CALL) ? cd1 : cd1 - 1.0;
    }

    @Override
    public double calculateGamma(Option security, double rate, Date today) {
	double spot, d1, sd1, sqrtExpDays, vol;
	int daysTilExpiration = (int)((security.getExpiration().getTime() - today.getTime()) / 86400000);
	
	vol = security.getUnderlier().getVolatility();
	spot = security.getUnderlier().getMarketQuote().getMarkToMarket();
	sqrtExpDays = Math.sqrt(daysTilExpiration);
	
	d1 = Math.log(spot / security.getStrike());
	d1 += (rate + vol * vol / 2) * daysTilExpiration;
	d1 /= vol * sqrtExpDays;
	
	sd1 = standardDistribution(d1);
	
	return sd1 / (spot * vol * sqrtExpDays);
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

    public static OptionRiskEngine getInstance() {
	return _instance;
    }

    private double standardDistribution(double x) {
	return Math.exp(-0.5 * x * x) * INVSQRT2PI;
    }

}
