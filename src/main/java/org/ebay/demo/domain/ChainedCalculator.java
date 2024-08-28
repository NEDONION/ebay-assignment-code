package org.ebay.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.springframework.stereotype.Service;

/**
 * The chaining method should allow users to start with an initial value
 * and apply multiple operations in sequence. For example, starting with 5, the user should be
 * able to add 3, then multiply by 2, and retrieve the final result.
 */

@Service
@Slf4j
public class ChainedCalculator {
	private final Calculator calculator;
	private Number currentValue;

	public ChainedCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public ChainedCalculator start(Number initialValue) {
		this.currentValue = initialValue;
		return this;
	}

	public ChainedCalculator apply(Operation operation, Number operand) {
		log.info("ChainedCalculator: applying operation: {}, operand: {}", operation, operand);
		currentValue = calculator.calculate(operation, currentValue, operand);
		return this;
	}

	public Number getResult() {
		return currentValue;
	}
}