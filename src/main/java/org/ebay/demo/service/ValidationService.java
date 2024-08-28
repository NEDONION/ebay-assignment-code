package org.ebay.demo.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.ebay.demo.enums.Operation;
import org.ebay.demo.exceptions.BadParameterException;
import org.ebay.demo.model.OperationRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ValidationService {

	public void validateOperation(Operation operation) {
		if (operation == null) {
			throw new BadParameterException("Invalid operation: operation cannot be null or operation not supported");
		}
	}

	public void validateInputs(Number num1, Number num2) {
		if (num1 == null || num2 == null) {
			throw new BadParameterException("Invalid input: Numbers cannot be null");
		}

		if (num2.doubleValue() == 0.0 && num2.doubleValue() == 0.0) {
			throw new BadParameterException("Invalid input: Divisor cannot be 0");
		}
	}

	public void validateInputs(Number initialValue, List<OperationRequest> operations) {
		if (initialValue == null) {
			throw new BadParameterException("Invalid initial value: Initial value cannot be null");
		}

		if (operations == null || operations.isEmpty()) {
			throw new BadParameterException("Invalid operation list: Operation list cannot be null or empty");
		}

		for (OperationRequest operationRequest : operations) {
			if (operationRequest.getOp() == null || operationRequest.getNum() == null) {
				throw new BadParameterException("Invalid operation request: Operation and number cannot be null");
			}
		}
	}
}
