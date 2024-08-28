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
		return num1.doubleValue() / num2.doubleValue();
	}
}