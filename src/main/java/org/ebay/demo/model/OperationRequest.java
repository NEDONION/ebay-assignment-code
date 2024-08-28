package org.ebay.demo.model;

import lombok.Data;
import org.ebay.demo.enums.Operation;

@Data
public class OperationRequest {
	private Operation op;
	private Number num;
}
