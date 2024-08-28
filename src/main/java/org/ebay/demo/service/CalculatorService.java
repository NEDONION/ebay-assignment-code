package org.ebay.demo.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.domain.Calculator;
import org.ebay.demo.domain.ChainedCalculator;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.exceptions.BadParameterException;
import org.ebay.demo.exceptions.CalculationException;
import org.ebay.demo.model.CalculateRequest;
import org.ebay.demo.model.ChainRequest;
import org.ebay.demo.model.OperationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculatorService {

	private final Calculator calculator;
	private final ChainedCalculator chainedCalculator;
	private final ValidationService validationService;

	@Autowired
	public CalculatorService(Calculator calculator, ChainedCalculator chainedCalculator,
			ValidationService validationService) {
		this.calculator = calculator;
		this.chainedCalculator = chainedCalculator;
		this.validationService = validationService;
	}

	public Number calculate(CalculateRequest calculateRequest) {
		validationService.validateCalculateRequest(calculateRequest);
		Operation operation = validationService.validateAndParseOperation(calculateRequest.getOp());

		Number result;
		Number num1 = null;
		Number num2 = null;
		try {
			num1 = calculateRequest.getNum1();
			num2 = calculateRequest.getNum2();
			result = calculator.calculate(operation, num1, num2);
		} catch (Exception e) {
			log.error("CalculatorService： Error during calculate: Operation={}, num1={}, num2={}", operation, num1,
					num2, e);
			throw new CalculationException("Failed to execute calculation", e);
		}

		if (result == null) {
			log.error("CalculatorService： Error during calculate: Operation={}, num1={}, num2={}", operation, num1,
					num2);
			throw new CalculationException("Failed to execute calculation");
		}
		return result;
	}


	public Number calculateChain(ChainRequest chainRequest) {
		validationService.validateChainRequest(chainRequest);
		Number initialValue = null;
		List<OperationRequest> operations = null;

		try {
			initialValue = chainRequest.getInitialValue() != null ? chainRequest.getInitialValue() : 0;
			operations = chainRequest.getOperations();

			chainedCalculator.start(initialValue);
			for (OperationRequest operationRequest : operations) {
				Operation operation = validationService.validateAndParseOperation(operationRequest.getOp());
				chainedCalculator.apply(operation, operationRequest.getNum());
			}
			return chainedCalculator.getResult();
		} catch (Exception e) {
			log.error("CalculatorService： Error during calculateChain: initialValue={}, operations={}", initialValue,
					operations, e);
			throw new CalculationException("Failed to execute chained calculation", e);
		}
	}
}