package edu.virginia.mcintire.hedgetournament.risk;

import java.util.Date;

import edu.virginia.mcintire.hedgetournament.securities.Stock;

public class StockRiskEngine implements RiskEngine<Stock> {

    private static StockRiskEngine _instance = new StockRiskEngine();

    private StockRiskEngine() {
    }

    @Override
    public double calculateDelta(Stock security, double rate, Date today) {
	return 1;
    }

    @Override
    public double calculateGamma(Stock security, double rate, Date today) {
	return 0;
    }

    public static StockRiskEngine getInstance() {
	return _instance;
    }

}
