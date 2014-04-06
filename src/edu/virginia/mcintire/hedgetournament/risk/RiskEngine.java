package edu.virginia.mcintire.hedgetournament.risk;

import java.util.Date;

import edu.virginia.mcintire.hedgetournament.securities.Security;

public interface RiskEngine<T extends Security> {
    /**
     * Calculates the delta of the given security
     * 
     * @param security
     * @param rate
     * @param today
     * @return
     */
    public double calculateDelta(T security, double rate, Date today);

    /**
     * Calculates the gamma of the given security
     * 
     * @param security
     * @param rate
     * @param today
     * @return
     */
    public double calculateGamma(T security, double rate, Date today);
}
