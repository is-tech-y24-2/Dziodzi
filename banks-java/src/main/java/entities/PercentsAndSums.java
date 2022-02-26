package entities;

import tools.MoneyException;
import tools.PercentException;

public class PercentsAndSums {

    private final Double percent;
    private final Double sum;

    public PercentsAndSums(Double percent, Double sum) throws PercentException, MoneyException {
        if (percent <= 0)
            throw new PercentException("Invalid percent in PercentsAndSums");
        if (sum <= 0)
            throw new MoneyException("Invalid money limit in PercentAndSums");
        this.percent = percent;
        this.sum = sum;
    }

    public Double getPercent(){
        return this.percent;
    }

    public Double getSum(){
        return this.sum;
    }
}
