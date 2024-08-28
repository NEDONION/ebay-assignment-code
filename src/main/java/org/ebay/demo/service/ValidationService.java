package org.ebay.demo.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.exceptions.BadParameterException;
import org.ebay.demo.model.CalculateRequest;
import org.ebay.demo.model.ChainRequest;
import org.ebay.demo.model.OperationRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationService {

	public void validateCalculateRequest(CalculateRequest calculateRequest) {
		Operation operation = validateAndParseOperation(calculateRequest.getOp());
		Number num1 = calculateRequest.getNum1();
		Number num2 = calculateRequest.getNum2();

		validateOperation(operation);
		validateInputs(num1, num2);
	}

	public void validateChainRequest(ChainRequest chainRequest) {
		List<OperationRequest> operations = chainRequest.getOperations();
		validateInputs(operations);
	}

	public void validateOperation(Operation operation) {
		if (operation == null) {
			log.error("Invalid operation: operation cannot be null or operation not supported");
			throw new BadParameterException("Invalid operation: operation cannot be null or operation not supported");
		}
	}

	public void validateInputs(Number num1, Number num2) {
		if (num1 == null || num2 == null) {
			log.error("Invalid input: Numbers cannot be null");
			throw new BadParameterException("Invalid input: Numbers cannot be null");
		}
		if (num2.doubleValue() == 0.0 && num2.doubleValue() == 0.0) {
			log.error("Invalid input: Divisor cannot be 0");
			throw new BadParameterException("Invalid input: Divisor cannot be 0");
		}
	}

	public void validateInputs(List<OperationRequest> operations) {
		if (operations == null || operations.isEmpty()) {
			log.error("Invalid operation list: Operation list cannot be null or empty");
			throw new BadParameterException("Invalid operation list: Operation list cannot be null or empty");
		}
		for (OperationRequest operationRequest : operations) {
			validateAndParseOperation(operationRequest.getOp());
			if (operationRequest.getNum() == null) {
				log.error("Invalid operation request: Operation and number cannot be null");
				throw new BadParameterException("Invalid operation request: Operation and number cannot be null");
			}
		}
	}

	public Operation validateAndParseOperation(String operationStr) {
		try {
			return Operation.valueOf(operationStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			log.error("Invalid operation: " + operationStr + ". Supported operations are: ADD, SUBTRACT, MULTIPLY, DIVIDE.");
			throw new BadParameterException("Invalid operation: " + operationStr + ". Supported operations are: ADD, SUBTRACT, MULTIPLY, DIVIDE.");
		}
	}
}
