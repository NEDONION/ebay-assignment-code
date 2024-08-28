package org.ebay.demo.strategy.multiply;

import java.math.BigDecimal;
import org.ebay.demo.strategy.OperationStrategy;

public class BigDecimalMultiplyOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		BigDecimal bd1 = toBigDecimal(num1);
		BigDecimal bd2 = toBigDecimal(num2);
		return bd1.multiply(bd2);
	}

	private BigDecimal toBigDecimal(Number number) {
		if (number instanceof BigDecimal) {
			return (BigDecimal) number;
		} else {
			return new BigDecimal(number.toString());
		}
	}
}