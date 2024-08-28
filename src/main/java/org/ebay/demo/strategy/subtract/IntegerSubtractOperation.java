package org.ebay.demo.strategy.subtract;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class IntegerSubtractOperation implements OperationStrategy {
	@Override
	public Number execute(Number num1, Number num2) {
		int n1 = num1.intValue();
		int n2 = num2.intValue();

		// check for subtraction overflow
		if (n1 == Integer.MIN_VALUE && n2 == 1) {
			log.error("IntegerSubtractOperation: Integer overflow");
			throw new ArithmeticException("Integer underflow");
		}

		// check for addition overflow
		if (n1 < 0 && n2 > 0 && n1 - n2 > 0) {
			log.error("IntegerSubtractOperation: Integer overflow");
			throw new ArithmeticException("Integer overflow");
		}

		// check for subtraction overflow
		if (n1 > 0 && n2 < 0 && n1 - n2 < 0) {
			log.error("IntegerSubtractOperation: Integer overflow");
			throw new ArithmeticException("Integer overflow");
		}

		return n1 - n2;
	}
}