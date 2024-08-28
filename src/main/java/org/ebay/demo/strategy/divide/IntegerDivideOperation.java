package org.ebay.demo.strategy.divide;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class IntegerDivideOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		if (num2.intValue() == 0) {
			log.error("IntegerDivideOperation: Division by zero is not allowed");
			throw new ArithmeticException("Division by zero is not allowed");
		}
		return num1.intValue() / num2.intValue();
	}
}