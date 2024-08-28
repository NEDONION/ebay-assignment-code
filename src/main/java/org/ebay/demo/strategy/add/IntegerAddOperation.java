package org.ebay.demo.strategy.add;

import org.ebay.demo.strategy.OperationStrategy;

public class IntegerAddOperation implements OperationStrategy {

	@Override
	public Number execute(Number a, Number b) {
		return a.intValue() + b.intValue();
	}
}