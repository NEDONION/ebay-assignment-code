package org.ebay.demo.strategy;

import static org.junit.jupiter.api.Assertions.*;
import org.ebay.demo.strategy.add.DoubleAddOperation;
import org.ebay.demo.strategy.subtract.DoubleSubtractOperation;
import org.ebay.demo.strategy.multiply.DoubleMultiplyOperation;
import org.ebay.demo.strategy.divide.DoubleDivideOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoubleOperationStrategyTest {

	private OperationStrategy addOperation;
	private OperationStrategy subtractOperation;
	private OperationStrategy multiplyOperation;
	private OperationStrategy divideOperation;

	@BeforeEach
	public void setUp() {
		addOperation = new DoubleAddOperation();
		subtractOperation = new DoubleSubtractOperation();
		multiplyOperation = new DoubleMultiplyOperation();
		divideOperation = new DoubleDivideOperation();
	}

	@Test
	public void testAddOperation() {
		assertEquals(5.0, addOperation.execute(2.0, 3.0));
		assertEquals(0.0, addOperation.execute(-2.0, 2.0));
		assertEquals(3.5, addOperation.execute(1.5, 2.0));
	}

	@Test
	public void testSubtractOperation() {
		assertEquals(2.0, subtractOperation.execute(5.0, 3.0));
		assertEquals(-4.0, subtractOperation.execute(-2.0, 2.0));
		assertEquals(-0.5, subtractOperation.execute(1.5, 2.0));
	}

	@Test
	public void testMultiplyOperation() {
		assertEquals(6.0, multiplyOperation.execute(2.0, 3.0));
		assertEquals(-4.0, multiplyOperation.execute(-2.0, 2.0));
		assertEquals(3.0, multiplyOperation.execute(1.5, 2.0));
		assertThrows(ArithmeticException.class, () -> multiplyOperation.execute(1e308, 1e308));
	}

	@Test
	public void testDivideOperation() {
		assertEquals(2.0, divideOperation.execute(6.0, 3.0));
		assertEquals(-1.0, divideOperation.execute(-2.0, 2.0));
		assertEquals(0.75, divideOperation.execute(1.5, 2.0));
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(1.0, 0.0));
	}
}
