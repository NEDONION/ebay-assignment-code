package org.ebay.demo.strategy;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.strategy.add.BigDecimalAddOperation;
import org.ebay.demo.strategy.add.DoubleAddOperation;
import org.ebay.demo.strategy.add.IntegerAddOperation;
import org.ebay.demo.strategy.divide.BigDecimalDivideOperation;
import org.ebay.demo.strategy.divide.DoubleDivideOperation;
import org.ebay.demo.strategy.divide.IntegerDivideOperation;
import org.ebay.demo.strategy.multiply.BigDecimalMultiplyOperation;
import org.ebay.demo.strategy.multiply.DoubleMultiplyOperation;
import org.ebay.demo.strategy.multiply.IntegerMultiplyOperation;
import org.ebay.demo.strategy.subtract.BigDecimalSubtractOperation;
import org.ebay.demo.strategy.subtract.DoubleSubtractOperation;
import org.ebay.demo.strategy.subtract.IntegerSubtractOperation;

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
		} else if (operation == Operation.SUBTRACT) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalSubtractOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleSubtractOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerSubtractOperation();
			}
		} else if (operation == Operation.MULTIPLY) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalMultiplyOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleMultiplyOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerMultiplyOperation();
			}
		} else if (operation == Operation.DIVIDE) {
			if (num1 instanceof BigDecimal || num2 instanceof BigDecimal) {
				return new BigDecimalDivideOperation();
			} else if (num1 instanceof Double || num2 instanceof Double) {
				return new DoubleDivideOperation();
			} else if (num1 instanceof Integer && num2 instanceof Integer) {
				return new IntegerDivideOperation();
			}
		}

		log.info("Unsupported operation or number type, num1: {}, num2: {}", num1.getClass(), num2.getClass());
		throw new UnsupportedOperationException("Unsupported operation or number type");
	}
}