package org.ebay.demo.strategy.add;


import java.math.BigDecimal;
import org.ebay.demo.strategy.OperationStrategy;

public class BigDecimalAddOperation implements OperationStrategy {

	@Override
	public Number execute(Number a, Number b) {
		return toBigDecimal(a).add(toBigDecimal(b));
	}

	private BigDecimal toBigDecimal(Number number) {
		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		} else {
			return new BigDecimal(number.toString());
		}
	}
}