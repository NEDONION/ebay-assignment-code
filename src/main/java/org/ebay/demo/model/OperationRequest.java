package org.ebay.demo.model;

import lombok.Data;
import org.ebay.demo.enums.Operation;

@Data
public class OperationRequest {
	private String op;
	private Number num;
}
