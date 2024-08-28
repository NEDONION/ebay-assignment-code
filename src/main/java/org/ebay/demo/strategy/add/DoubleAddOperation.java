package org.ebay.demo.strategy.add;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class DoubleAddOperation implements OperationStrategy {

	@Override
	public Number execute(Number num1, Number num2) {
		if (num1 instanceof Double && num2 instanceof Double) {
			double a = num1.doubleValue();
			double b = num2.doubleValue();
			double result = a + b;
			if (Double.isInfinite(result)) {
				log.error("DoubleAddOperation: Double overflow");
				throw new ArithmeticException("Double overflow");
			}
			return result;
		}
		return null; // Fallback or other cases
	}
}
