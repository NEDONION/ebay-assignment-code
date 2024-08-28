package org.ebay.demo.strategy;

import org.ebay.demo.strategy.add.IntegerAddOperation;
import org.ebay.demo.strategy.divide.IntegerDivideOperation;
import org.ebay.demo.strategy.multiply.IntegerMultiplyOperation;
import org.ebay.demo.strategy.subtract.IntegerSubtractOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerOperationStrategyTest {

	private OperationStrategy addOperation;
	private OperationStrategy subtractOperation;
	private OperationStrategy multiplyOperation;
	private OperationStrategy divideOperation;

	@BeforeEach
	public void setUp() {
		addOperation = new IntegerAddOperation();
		subtractOperation = new IntegerSubtractOperation();
		multiplyOperation = new IntegerMultiplyOperation();
		divideOperation = new IntegerDivideOperation();
	}

	@Test
	public void testAddOperation() {
		assertEquals(5, addOperation.execute(2, 3));
		assertEquals(0, addOperation.execute(-2, 2));
		assertEquals(-5, addOperation.execute(-2, -3));
		assertEquals(Integer.MAX_VALUE, addOperation.execute(Integer.MAX_VALUE, 0));
		assertThrows(ArithmeticException.class, () -> addOperation.execute(Integer.MAX_VALUE, 1));
	}

	@Test
	public void testSubtractOperation() {
		assertEquals(2, subtractOperation.execute(5, 3));
		assertEquals(-4, subtractOperation.execute(-2, 2));
		assertEquals(1, subtractOperation.execute(-2, -3));
		assertEquals(Integer.MIN_VALUE, subtractOperation.execute(Integer.MIN_VALUE, 0));
		assertThrows(ArithmeticException.class, () -> subtractOperation.execute(Integer.MIN_VALUE, 1));
	}

	@Test
	public void testMultiplyOperation() {
		assertEquals(6, multiplyOperation.execute(2, 3));
		assertEquals(-4, multiplyOperation.execute(-2, 2));
		assertEquals(6, multiplyOperation.execute(-2, -3));
		assertEquals(0, multiplyOperation.execute(0, Integer.MAX_VALUE));
		assertThrows(ArithmeticException.class, () -> multiplyOperation.execute(Integer.MAX_VALUE, 2));
	}

	@Test
	public void testDivideOperation() {
		assertEquals(2.0, divideOperation.execute(6, 3));
		assertEquals(-1.0, divideOperation.execute(-2, 2));
		assertEquals(1.0, divideOperation.execute(-3, -3));
		assertThrows(ArithmeticException.class, () -> divideOperation.execute(1, 0));
		assertEquals((double) Integer.MIN_VALUE, divideOperation.execute(Integer.MIN_VALUE, 1));
	}
}
