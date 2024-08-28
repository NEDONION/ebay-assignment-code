package org.ebay.demo.strategy.subtract;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class IntegerSubtractOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		if (num1.intValue() == Integer.MIN_VALUE && num2.intValue() < 0) {
			log.error("Integer overflow");
			throw new ArithmeticException("Integer overflow");
		}
		return num1.intValue() - num2.intValue();
	}
}