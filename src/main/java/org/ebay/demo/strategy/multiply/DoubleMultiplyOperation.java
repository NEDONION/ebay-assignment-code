package org.ebay.demo.strategy.multiply;

import org.ebay.demo.strategy.OperationStrategy;

public class DoubleMultiplyOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		return num1.doubleValue() * num2.doubleValue();
	}
}