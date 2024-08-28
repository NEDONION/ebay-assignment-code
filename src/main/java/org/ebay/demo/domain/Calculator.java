package org.ebay.demo.domain;

import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.strategy.OperationStrategy;
import org.ebay.demo.strategy.StrategyFactory;
import org.springframework.stereotype.Service;

/**
Basic Calculation Method: Implement a method calculate(Operation op, Number
num1, Number num2) in the Calculator class that performs a single operation between
two numbers and returns the result
 **/

@Slf4j
@Service
public class Calculator {

	public Number calculate(Operation operation, Number num1, Number num2) {
		log.info("received operation: {}, num1: {}, num2: {}", operation, num1, num2);
		OperationStrategy strategy = StrategyFactory.getStrategy(operation, num1, num2);

		if (strategy == null) {
			throw new UnsupportedOperationException("Operation not supported: " + operation);
		}

		log.info("executing strategy: {}", strategy.getClass().getSimpleName());
		return strategy.execute(num1, num2);
	}
}
