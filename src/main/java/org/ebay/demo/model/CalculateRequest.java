package org.ebay.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculateRequest {
	private String op;
	private Number num1;
	private Number num2;
}
