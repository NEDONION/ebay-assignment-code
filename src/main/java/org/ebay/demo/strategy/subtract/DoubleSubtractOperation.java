package org.ebay.demo.strategy.subtract;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.strategy.OperationStrategy;

@Slf4j
public class DoubleSubtractOperation implements OperationStrategy {
	private static final double EPSILON = 1e-10; // For comparing floating point numbers close to zero

	@Override
	public Number execute(Number num1, Number num2) {
		double value1 = num1.doubleValue();
		double value2 = num2.doubleValue();
		double result = value1 - value2;

		if (Double.isInfinite(value1) || Double.isInfinite(value2)) {
			log.error("DoubleSubtractOperation: Operation results in infinity");
			throw new ArithmeticException("Operation results in infinity");
		}

		if (Double.isNaN(value1) || Double.isNaN(value2)) {
			log.error("DoubleSubtractOperation: Invalid operation with NaN");
			throw new ArithmeticException("Invalid operation with NaN");
		}

		if (Double.isInfinite(result) || Double.isNaN(result)) {
			log.error("DoubleSubtractOperation: Overflow or invalid operation result");
			throw new ArithmeticException("Overflow or invalid operation result");
		}

		if (Math.abs(result) < EPSILON) {
			return 0.0;
		}

		return result;
	}
}