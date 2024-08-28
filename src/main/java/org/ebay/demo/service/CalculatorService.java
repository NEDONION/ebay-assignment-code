package org.ebay.demo.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.domain.Calculator;
import org.ebay.demo.domain.ChainedCalculator;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.exceptions.BadParameterException;
import org.ebay.demo.exceptions.CalculationException;
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

	public Number calculate(Operation operation, Number num1, Number num2) {
		validationService.validateOperation(operation);
		validationService.validateInputs(num1, num2);
		try {
			return calculator.calculate(operation, num1, num2);
		} catch (Exception e) {
			log.error("Error during single operation calculation: Operation={}, num1={}, num2={}", operation, num1,
					num2, e);
			throw new CalculationException("Failed to execute calculation", e);
		}
	}


	public Number calculateChain(Number initialValue, List<OperationRequest> operations) {
		validationService.validateInputs(initialValue, operations);
		try {
			chainedCalculator.start(initialValue);

			for (OperationRequest operationRequest : operations) {
				chainedCalculator.apply(operationRequest.getOp(), operationRequest.getNum());
			}

			return chainedCalculator.getResult();
		} catch (Exception e) {
			log.error("Error during chained calculation: initialValue={}, operations={}", initialValue, operations, e);
			throw new CalculationException("Failed to execute chained calculation", e);
		}
	}

}