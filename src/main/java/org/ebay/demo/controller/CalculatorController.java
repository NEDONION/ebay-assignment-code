package org.ebay.demo.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.domain.Calculator;
import org.ebay.demo.domain.ChainedCalculator;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.model.OperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/calculator")
public class CalculatorController {

	private final Calculator calculator;
	private final ChainedCalculator chainedCalculator;

	@Autowired
	public CalculatorController(Calculator calculator, ChainedCalculator chainedCalculator) {
		this.calculator = calculator;
		this.chainedCalculator = chainedCalculator;
	}

	@GetMapping("/calculate")
	public Number calculate(
			@RequestParam Operation operation,
			@RequestParam Number num1,
			@RequestParam Number num2) {
		log.info("CalculatorController: received calculate request: {}, num1: {}, num2: {}", operation, num1, num2);
		return calculator.calculate(operation, num1, num2);
	}

	@PostMapping("/chain")
	public Number chainOperations(
			@RequestParam(required = false) Number initialValue,
			@RequestBody List<OperationRequest> operations) {

		log.info("CalculatorController: received chain request: initialValue: {}, operations: {}", initialValue, operations);
		if (initialValue == null) {
			initialValue = 0;
		}

		log.info("CalculatorController: starting chained calculator with initial value: {}", initialValue);
		chainedCalculator.start(initialValue);

		for (OperationRequest operationRequest : operations) {
			chainedCalculator.apply(operationRequest.getOp(), operationRequest.getNum());
		}

		return chainedCalculator.getResult();
	}
}