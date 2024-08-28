package org.ebay.demo.strategy.divide;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class DoubleDivideOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		if (num2.doubleValue() == 0.0) {
			log.error("DoubleDivideOperation: Division by zero is not allowed");
			throw new ArithmeticException("Division by zero is not allowed");
		}

		if (Double.isNaN(num1.doubleValue()) || Double.isNaN(num2.doubleValue())) {
			log.error("DoubleDivideOperation: NaN encountered in division");
			throw new ArithmeticException("Invalid operation: NaN encountered");
		}

		if (Double.isInfinite(num1.doubleValue()) || Double.isInfinite(num2.doubleValue())) {
			log.warn("DoubleDivideOperation: Infinity encountered in division, result may be infinite");
		}

		double result = num1.doubleValue() / num2.doubleValue();

		if (result == 0.0 && num1.doubleValue() != 0.0) {
			log.warn("DoubleDivideOperation: Result is very close to zero, possible underflow");
		}

		return result;
	}
}