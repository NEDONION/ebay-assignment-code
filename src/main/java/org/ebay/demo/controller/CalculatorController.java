package org.ebay.demo.controller;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.domain.Calculator;
import org.ebay.demo.domain.ChainedCalculator;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.model.OperationRequest;
import org.ebay.demo.model.Response;
import org.ebay.demo.service.CalculatorService;
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

	private final CalculatorService calculatorService;

	@Autowired
	public CalculatorController(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@GetMapping("/calculate")
	public Response calculate(
			@RequestParam Operation operation,
			@RequestParam Number num1,
			@RequestParam Number num2) {
		log.info("CalculatorController: received calculate request: {}, num1: {}, num2: {}", operation, num1, num2);
		Number result = calculatorService.calculate(operation, num1, num2);
		return Response.builder().code("200").message("Calculation successful").data(result).build();
	}

	@PostMapping("/chain")
	public Response chainOperations(
			@RequestParam(required = false) Number initialValue,
			@RequestBody List<OperationRequest> operations) {

		log.info("CalculatorController: received chain request: initialValue: {}, operations: {}", initialValue,
				operations);
		if (initialValue == null) {
			initialValue = 0;
		}

		log.info("CalculatorController: starting chained calculator with initial value: {}", initialValue);
		Number result = calculatorService.calculateChain(initialValue, operations);
		return Response.builder().code("200").message("Chained calculation successful").data(result).build();
	}
}