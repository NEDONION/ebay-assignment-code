package org.ebay.demo.strategy;

import java.math.BigDecimal;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.strategy.add.BigDecimalAddOperation;
import org.ebay.demo.strategy.add.DoubleAddOperation;
import org.ebay.demo.strategy.add.IntegerAddOperation;

@Slf4j
public class StrategyFactory {

	public static OperationStrategy getStrategy(Operation operation, Number num1, Number num2) {
		if (operation == Operation.ADD) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalAddOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleAddOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerAddOperation();
			}
		}

		log.info("Unsupported operation or number type, num1: {}, num2: {}", num1.getClass(), num2.getClass());
		throw new UnsupportedOperationException("Unsupported operation or number type");
	}
}