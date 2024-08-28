package org.ebay.demo.strategy.add;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class IntegerAddOperation implements OperationStrategy {

	@Override
	public Number execute(Number num1, Number num2) {
		if (num1 instanceof Integer && num2 instanceof Integer) {
			int a = num1.intValue();
			int b = num2.intValue();
			if ((a > 0 && b > 0 && a > Integer.MAX_VALUE - b) ||
					(a < 0 && b < 0 && a < Integer.MIN_VALUE - b)) {
				log.error("IntegerAddOperation: Integer overflow");
				throw new ArithmeticException("Integer overflow");
			}
			return a + b;
		}
		return null; // Fallback or other cases
	}
}