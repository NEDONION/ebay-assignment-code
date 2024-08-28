package org.ebay.demo.strategy.multiply;

import org.ebay.demo.strategy.OperationStrategy;

public class IntegerMultiplyOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		return num1.intValue() * num2.intValue();
	}
}